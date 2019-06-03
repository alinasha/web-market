<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page pageEncoding="UTF-8" %>
Product detailed:
<div id="detailed-view-item">
    <table id="detailed-view-item-table-head">
        <tr>
            <td>
                <!-- main image -->
                <a id="_product-img-a">
                    <img id="_product-img"/>
                </a>
            </td>
            <td class="align-top">
                <table id="_detailed-view-table-head-content">
                    <tr>
                        <td>
                            <!-- title + url, price -->
                            <a id="_product-web-url">
                                <h3 id="_product-title"></h3>
                            </a>
                            <div id="_product-price"></div>
                        </td>
                    </tr>
                    <tr>
                        <td class="align-bottom">
                            <!-- images -->
                            <div id="_product-images"></div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <div class="td-right">
        <input id="_detailed-view-button-back" type="button" value="Back">
    </div>
    <!-- parameters -->
    <div id="_product-parameters"></div>
    <!-- description -->
    <div id="_product-description"></div>
</div>