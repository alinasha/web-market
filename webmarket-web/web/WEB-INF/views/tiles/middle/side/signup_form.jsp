<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>
<div id="sign-in-form" class="functional-block">
    <form class="auth-form">
        <div class="auth_field">
            <input type="text" class="auth-text-box" placeholder="Логин">
        </div>
        <div class="auth_field">
            <input type="email" class="auth-text-box" placeholder="Email">
        </div>
        <div class="auth_field">
            <input type="password" class="auth-text-box" placeholder="Пароль">
        </div>
        <div class="auth_field">
            <input type="password" class="auth-text-box" placeholder="Повторите пароль">
        </div>
        <div class="auth_field">
            <input type="submit" class="auth-button" value="ЗАРЕГИСТРИРОВАТЬСЯ">
        </div>
        <div class="auth_field" id="singup-to-signin-switch-button">
            <span class="auth-label">ВХОД</span>
        </div>
    </form>
</div>