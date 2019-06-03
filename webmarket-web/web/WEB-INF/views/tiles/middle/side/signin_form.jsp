<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>

<div id="sign-in-form" class="functional-block">
    <div id="_sign-in-form-error" style="display: none">
        <span>Your login attempt was not successful, try again.</span>
    </div>
    <form class="auth-form">
        <div class="auth_field">
            <input type="text" id="_sign-in-form-username" class="auth-text-box" placeholder="Логин">
        </div>
        <div class="auth_field">
            <input type="password" id="_sign-in-form-password" class="auth-text-box" placeholder="Пароль">
        </div>
        <div class="auth_field">
            <input id="_sign-in-form-submit" type="button" class="auth-button" value="Войти">
        </div>
        <div class="auth_field" id="singin-to-signup-switch-button">
            <span class="auth-label">РЕГИСТРАЦИЯ</span>
        </div>
    </form>
</div>