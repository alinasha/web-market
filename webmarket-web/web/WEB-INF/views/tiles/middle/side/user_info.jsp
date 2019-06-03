<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>

<div id="user-info" class="functional-block">
    <table id="user-info-table">
        <tr>
            <td>
                <div id="user-name">Username: <span id="user-name-label"></span></div>
            </td>
            <td>
                <div id="user-logout"><input id="_user-info-logout" type="button" value="Logout"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div id="user-button-open-history"><input id="_user-button-open-history" type="button" value="Your recent history"></div>
            </td>
            <td></td>
        </tr>
    </table>
</div>