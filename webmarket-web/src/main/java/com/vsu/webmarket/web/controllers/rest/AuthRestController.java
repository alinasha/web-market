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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final Gson json = new Gson();
    private AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserLoginRequestData userLoginRequestData,
                        HttpServletResponse response) {
        Optional<User> userByCredentials = authService.getUserByCredentials(
                userLoginRequestData.getUsername(), userLoginRequestData.getPassword());
        if (userByCredentials.isPresent()) {
            User found = userByCredentials.get();
            response.setStatus(200);
            setCookies(found, response);
            return json.toJson(new UserResponseData(found.getLogin()));
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return json.toJson(new ErrorData("user credentials is not right"));
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserRegisterRequestData userRegisterRequestData,
                           HttpServletResponse response) {
        User user = new User();
        user.setEmail(userRegisterRequestData.getEmail());
        user.setLogin(userRegisterRequestData.getUsername());
        user.setPassword(userRegisterRequestData.getPassword());
        try {
            authService.registerUser(user);
        } catch (NullPointerException | AuthException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return json.toJson(new ErrorData(e.getMessage()));
        }
        response.setStatus(200);
        setCookies(user, response);
        return json.toJson(new UserResponseData(user.getLogin()));
    }

    private void setCookies(User user, HttpServletResponse response) {
        if (user.getToken() == null || user.getToken().isEmpty()) {
            authService.setNewTokenToUser(user);
        }
        response.addCookie(new Cookie("token", user.getToken()));
    }
}
