<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>

<div id="sign-in-form" class="functional-block">
    <div id="_sign-up-form-info" style="display: none">
        <span id="_sign-up-form-info-message">Your registration attempt failed, try again.</span>
    </div>
    <form class="auth-form">
        <div class="auth_field">
            <input type="text" id="_sign-up-form-username" class="auth-text-box" placeholder="Логин">
        </div>
        <div class="auth_field">
            <input type="email" id="_sign-up-form-email" class="auth-text-box" placeholder="Email">
        </div>
        <div class="auth_field">
            <input type="password" id="_sign-up-form-password" class="auth-text-box" placeholder="Пароль">
        </div>
        <div class="auth_field">
            <input type="password" id="_sign-up-form-password-repeat" class="auth-text-box" placeholder="Повторите пароль">
        </div>
        <div class="auth_field">
            <input type="button" id="_sign-up-form-submit" class="auth-button" value="ЗАРЕГИСТРИРОВАТЬСЯ">
        </div>
        <div class="auth_field" id="singup-to-signin-switch-button">
            <span class="auth-label">ВХОД</span>
        </div>
    </form>
</div>