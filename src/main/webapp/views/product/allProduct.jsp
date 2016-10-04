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
<div id="tooplate_wrapper" style="height: auto">
    <div class="clear"></div>

    <c:forEach items="${product}" var="a">

        <div class="col one_third gallery_box">
            <div class="img_frame img_frame_13 img_nom"><span></span>
                <a href="data:image/jpeg;base64,${a.image}" rel="lightbox[portfolio]"> <img
                        src="data:image/jpeg;base64,${a.image}" alt="Image 03"></a>
            </div>
            <p>${a.name}</p>
            <p>Дійсний до ${a.date}</p>

                <%--<sec:authorize access="hasRole('ADMIN')">--%>
            <a href="/productEdit/${a.id_p}">
                <button type="submit">Модифікувати</button>
            </a>
                <%--</sec:authorize>--%>
            <p>
                    <%--<sec:authorize access="hasRole('ADMIN')">--%>
                <form:form action="/product/delete/${a.id_p}" method="post">
                    <button type="submit">Видалити</button>
                </form:form>
                    <%--</sec:authorize>--%>
            </p>
                <%--<sec:authorize access="hasRole('USER')">--%>
            <p>
                <a href="/product/${a.id_p}">Додати в замовлення</a>
            </p>
                <%--</sec:authorize>--%>

        </div>

    </c:forEach>
    <div class="clear"></div>

</div>
