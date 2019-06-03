<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>
<div class="functional-block">
    <form class="search-form">
        <input type="text" class="search-text-box" placeholder="Введите название товара">
        <div id="search-button" class="search-button">
            Search
        </div>
    </form>
    <div id="search-result-list"></div>
    <div id="search-detailed-view" style="display: none"></div>
    <div id="history-detailed-view" style="display: none"></div>
</div>