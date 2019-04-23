<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>
<%--<div>--%>
    <%--<form>--%>
        <%--<label>--%>
            <%--Введите имя пользователя--%>
            <%--<input type="text" placeholder="имя пользователя">--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--Введите пароль--%>
            <%--<input type="password" placeholder="пароль">--%>
        <%--</label>--%>
        <%--<button type="submit">Войти</button>--%>
    <%--</form>--%>
    <%--<p style="color: lightblue" id="singin-to-signup-switch-button">Зарегистрироваться</p>--%>
<%--</div>--%>

<div id="sign-in-form" class="functional-block">
    <form class="auth-form">
        <div class="auth_field">
            <input type="text" class="auth-text-box" placeholder="Логин">
        </div>
        <div class="auth_field">
            <input type="password" class="auth-text-box" placeholder="Пароль">
        </div>
        <div class="auth_field">
            <input type="submit" class="auth-button" value="Войти">
        </div>
        <div class="auth_field" id="singin-to-signup-switch-button">
            <span class="auth-label">РЕГИСТРАЦИЯ</span>
        </div>
    </form>
</div>