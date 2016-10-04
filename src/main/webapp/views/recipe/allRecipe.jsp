<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 17.08.2016
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/resources/resources1/css/templatemo_style.css" rel="stylesheet" type="text/css">
<link href="/resources/resources1/css/ddsmoothmenu.css" rel="stylesheet" type="text/css">
<link href="/resources/resources1/css/jquery.dualSlider.0.2.css" rel="stylesheet" type="text/css">
<link href="/resources/resources1/css/slimbox2.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/resources/resources1/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="/resources/resources1/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/resources/resources1/js/jquery.dualSlider.0.3.js"></script>
<script type="text/javascript" src="/resources/resources1/js/jquery.dualSlider.0.3.min.js"></script>
<script type="text/javascript" src="/resources/resources1/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/resources/resources1/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="/resources/resources1/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/resources1/js/slimbox2.js"></script>
<div id="tooplate_wrapper" style="overflow: auto;height:200%">
    <div class="clear"></div>

    <c:forEach items="${recipe}" var="a">

        <div class="col one_third gallery_box">
            <div class="img_frame img_frame_13 img_nom"><span></span>
                <a href="data:image/jpeg;base64,${a.image}" rel="lightbox[portfolio]"> <img
                        src="data:image/jpeg;base64,${a.image}" alt="Image 03"></a>
            </div>
            <p>${a.name}</p>
            <%--<p>${a.category}</p>--%>
            <p>${a.description}</p>

                <sec:authorize access="hasRole('ADMIN')">
            <a href="/recipeEdit/${a.id_r}">
                <button type="submit">Модифікувати</button>
            </a>
                </sec:authorize>
            <p>
                    <sec:authorize access="hasRole('ADMIN')">
                <form:form action="/recipe/delete/${a.id_r}" method="post">
                    <button type="submit">Видалити</button>
                </form:form>
                    </sec:authorize>
            </p>

        </div>

    </c:forEach>
    <div class="clear"></div>

</div>
