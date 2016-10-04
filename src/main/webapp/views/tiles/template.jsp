<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 31.08.2016
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>


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


<div id="tooplate_body_wrapper">


    <div><tiles:insertAttribute name="header"/></div>
    <div><tiles:insertAttribute name="body"/></div>
    <div><tiles:insertAttribute name="footer"/></div>
</div>

</html>
