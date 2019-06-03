package com.vsu.webmarket.web.controllers.rest;

import com.google.gson.Gson;
import com.vsu.webmarket.exceptions.AuthException;
import com.vsu.webmarket.model.User;
import com.vsu.webmarket.web.controllers.rest.restmodel.ErrorData;
import com.vsu.webmarket.web.controllers.rest.restmodel.UserLoginRequestData;
import com.vsu.webmarket.web.controllers.rest.restmodel.UserRegisterRequestData;
import com.vsu.webmarket.web.controllers.rest.restmodel.UserResponseData;
import com.vsu.webmarket.logic.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthRestController {
    private final Gson gson = new Gson();
    private AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Проверка клиентом статуса авторизации
     * @return вернет имя пользователя в качестве ответа
     */
    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String ping(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie != null && cookie.getName() != null && cookie.getName().equalsIgnoreCase("token")) {
                Optional<String> usernameByToken = authService.getUsernameByToken(cookie.getValue());
                if (usernameByToken.isPresent()) {
                    String username = usernameByToken.get();

                    response.setStatus(200);
                    return gson.toJson(new UserResponseData(username));
                }
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return gson.toJson(new ErrorData("user is not authorized."));
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String login(@ModelAttribute UserLoginRequestData userLoginRequestData, HttpServletResponse response) {
        Optional<User> userByCredentials = authService.getUserByCredentials(
                userLoginRequestData.getUsername(), userLoginRequestData.getPassword());
        if (userByCredentials.isPresent()) {
            //response head
            User found = userByCredentials.get();
            response.setStatus(200);
            setCookies(found, response);

            //вернуть в JSON-ответе > username
            return gson.toJson(new UserResponseData(found.getLogin()));
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return gson.toJson(new ErrorData("user credentials is not right"));
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie != null && cookie.getName() != null) {
                cookie.setMaxAge(0);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
        }
        response.setStatus(200);
        return "redirect:/";
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String register(@ModelAttribute UserRegisterRequestData userRegisterRequestData,
                           HttpServletResponse response) {
        //check password repeat
        if (!userRegisterRequestData.getUsername().isEmpty()) {
            String password = userRegisterRequestData.getPassword();
            String passwordRepeat = userRegisterRequestData.getPasswordRepeat();
            boolean isPasswordRepeat = password != null && password.equals(passwordRepeat);
            if (!isPasswordRepeat) {
                response.setStatus(HttpStatus.OK.value());
                return gson.toJson(new ErrorData("Passwords do not match."));
            }
        }

        User user = new User();
        user.setEmail(userRegisterRequestData.getEmail());
        user.setLogin(userRegisterRequestData.getUsername());
        user.setPassword(userRegisterRequestData.getPassword());
        try {
            authService.registerUser(user);
        } catch (NullPointerException | AuthException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.OK.value());
            return gson.toJson(new ErrorData(e.getMessage()));
        }

        //ok
        response.setStatus(200);
        //setCookies(user, response); //не сохраняем в куки после регистрации, даем возможность пользователю самим войти
        return gson.toJson(new UserResponseData(user.getLogin()));
    }

    private void setCookies(User user, HttpServletResponse response) {
        if (user.getToken() == null || user.getToken().isEmpty()) {
            authService.setNewTokenToUser(user);
        }
        Cookie cookie = new Cookie("token", user.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
