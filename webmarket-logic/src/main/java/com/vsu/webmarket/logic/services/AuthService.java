package com.vsu.webmarket.logic.services;

import com.vsu.webmarket.data.repositories.UserRepository;
import com.vsu.webmarket.exceptions.AuthException;
import com.vsu.webmarket.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthService {
    private final EncryptService encryptService;
    private final UserRepository userRepository;

    private static final Pattern USERNAME_RE_PATTERN = Pattern.compile("^[A-Za-z0-9]{5,16}$");
    private static final Pattern PASSWORD_RE_PATTERN = Pattern.compile("^[A-Za-z0-9]{8,32}$");
    private static final Pattern PASSWORD_RE_UPPERCASE_PATTERN = Pattern.compile("(.*[A-Z].*)");
    private static final Pattern PASSWORD_RE_LOWERCASE_PATTERN = Pattern.compile("(.*[a-z].*)");
    private static final Pattern PASSWORD_RE_DIGITS_PATTERN = Pattern.compile("(.*[0-9].*)");
    private static final Pattern EMAIL_RE_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");

    public AuthService(EncryptService encryptService, UserRepository userRepository) {
        this.encryptService = encryptService;
        this.userRepository = userRepository;
    }

    public void registerUser(User user) throws AuthException, NullPointerException {
        Objects.requireNonNull(user, "user is not set");

        String username = user.getLogin();
        Objects.requireNonNull(username, "username is not set");
        checkUsername(username);

        String password = user.getPassword();
        Objects.requireNonNull(password, "password is not set");
        checkPassword(password);

        String email = user.getEmail();
        Objects.requireNonNull(email, "email is not set");
        checkEmail(email);

        user.setPassword(encryptService.encryptString(user.getPassword()));

        userRepository.save(user);
        setNewTokenToUser(user);
    }

    public Optional<String> getUsernameByToken(String token) {
        Optional<User> userOptional = userRepository.findByToken(token);
        return userOptional.map(User::getLogin);
    }

    public Optional<User> getUserByCredentials(String username, String password) {
        String encryptedPassword = encryptService.encryptString(password);
        return userRepository.findByLoginIgnoreCaseAndPassword(username, encryptedPassword);
    }

    public void setNewTokenToUser(User user) {
        String randomString = RandomStringUtils.randomAlphanumeric(64);
        String hash = encryptService.encryptString(randomString);
        user.setToken(hash);
        userRepository.save(user);
    }

    private void checkUsername(String username) throws AuthException {
        if (username.length() < 5) {
            throw new AuthException("username is too short: it must be in range [5:16]");
        }
        if (username.length() > 16) {
            throw new AuthException("username is too long: it must be in range [8:16]");
        }
        if (!USERNAME_RE_PATTERN.matcher(username).matches()) {
            throw new AuthException("username has inappropriate symbols");
        }
        if (userRepository.existsByLoginIgnoreCase(username)) {
            throw new AuthException("username is already in use");
        }
    }

    private void checkPassword(String password) throws AuthException {
        if (password.length() < 8) {
            throw new AuthException("password is too short: it must be in range [8:32]");
        }
        if (password.length() > 16) {
            throw new AuthException("password is too long: it must be in range [8:32]");
        }
        if (!PASSWORD_RE_UPPERCASE_PATTERN.matcher(password).matches()) {
            throw new AuthException("password must contain uppercase letters");
        }
        if (!PASSWORD_RE_LOWERCASE_PATTERN.matcher(password).matches()) {
            throw new AuthException("password must contain lowercase letters");
        }
        if (!PASSWORD_RE_DIGITS_PATTERN.matcher(password).matches()) {
            throw new AuthException("password must contain digits");
        }
        if (!PASSWORD_RE_PATTERN.matcher(password).matches()) {
            throw new AuthException("password has inappropriate symbols");
        }
    }

    private void checkEmail(String email) throws AuthException {
        if (!EMAIL_RE_PATTERN.matcher(email.toUpperCase()).matches()) {
            throw new AuthException("email is inappropriate");
        }
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new AuthException("email is already in use");
        }
    }
}
