<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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


<%--<div id="tooplate_body_wrapper">--%>
<div id="tooplate_wrapper1">
    <div>
        <sec:authorize access="isAnonymous()">
            <a style="
        float: right;
        width: 80px;
        border-left: 10px solid #444443;
        padding-left: 15px;
        line-height: 30px;


        " href="/loginpage">
                <button style="font-size: 12pt" type="submit">Увійти</button>
            </a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <form:form method="post" action="/logout">
                <button type="submit"
                        style="
        float: right;
        width: 80px;
        border-left: 1px solid #444443;
        padding-left: 15px;
        /*line-height: 30px;*/
        font-size: 12pt

        ">Вийти
                </button>
            </form:form>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <a style="
        float: right;
        width: 80px;
        border-left: 0px solid #444443;
        /*padding-left: 30px;*/
        line-height: 30px;


        " href="/registration">
                <button style="font-size: 12pt" type="submit">Реєстрація</button>
            </a>
        </sec:authorize>
    </div>


    <div id="tooplate_header">
        <div id="site_title">
            <h1><a href="/"></a></h1>
        </div>
        <div id="tooplate_menu">
            <ul>

                <li><a href="/allAProduct">Продукти</a></li>
                <sec:authorize access="hasRole('USER')">
                    <li><a href="/allKitchen">Моя кухня</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('USER')">
                    <li><a href="/endKitchen">HOT!!! </a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('USER')">
                    <li><a href="/allMyEat">Рецепти </a></li>
                </sec:authorize>

                <li><a href="/allRecipe">Всі Рецепти </a></li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="/newRecipe">Новий рецепт </a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="/newAProduct">Додати продукт </a></li>
                </sec:authorize>

            </ul>
        </div> <!-- end of tooplate_menu -->
    </div> <!-- end of forever header -->


    <%--</div>--%>

</div>
