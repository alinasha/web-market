<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Some title</title>
    <tiles:insertAttribute name="css_block"/>
    <tiles:insertAttribute name="before_script_block"/>
</head>
<body>
<div id="top-view">
    <tiles:insertAttribute name="top"/>
</div>
<div id="middle">
    <div id="content-view">
        <tiles:insertAttribute name="content"/>
    </div>
    <div id="side-view">
        <tiles:insertAttribute name="side"/>
    </div>
</div>
<tiles:insertAttribute name="after_script_block"/>
</body>
</html>