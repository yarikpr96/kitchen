<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 19.09.2016
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/resources/css/tooplate_style.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" href="/resources/css/nivo-slider.css" type="text/css" media="screen"/>
<script src="/resources/js/jquery.min.js" type="text/javascript"></script>
<script src="/resources/js/jquery.nivo.slider.js" type="text/javascript"></script>

<script type="text/javascript">
    $(window).load(function () {
        $('#slider').nivoSlider({
            effect: 'random',
            slices: 15,
            animSpeed: 500,
            pauseTime: 3000,
            startSlide: 0, //Set starting Slide (0 index)
            directionNav: false,
            directionNavHide: false, //Only show on hover
            controlNav: false, //1,2,3...
            controlNavThumbs: false, //Use thumbnails for Control Nav
            pauseOnHover: true, //Stop animation while hovering
            manualAdvance: false, //Force manual transitions
            captionOpacity: 0.8, //Universal caption opacity
            beforeChange: function () {
            },
            afterChange: function () {
            },
            slideshowEnd: function () {
            } //Triggers after all slides have been shown
        });
    });
</script>


<div style="height: 100%">
    <div id="tooplate_wrapper4">
        <form action="${pageContext.request.contextPath}/newRecipe?${_csrf.parameterName}=${_csrf.token}"
              method="post" enctype="multipart/form-data">
            <p>Ім'я</p>
            <a><input type="text" name="name"></a>
            <p>Категорія</p>
            <a><input type="text" name="category"></a>
            <p>Опис</p>
            <a><input type="text" name="description"></a>
            <p>Фото</p>
            <a><input type="file" name="image"></a>
            <p></p>
            <c:forEach items="${product}" var="a">
                <div>
                    <a style="font-size: 20px;color: #101010;">${a.name} </a>
                </div>
            </c:forEach>
            <p></p>
            <button type="submit">Додати!</button>
        </form>
    </div>
</div>