<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 100%">
    <div id="tooplate_wrapper3" >
        <form action="/kitchenEdit?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">

            <table cellpadding="5px">
                <tr>
                    <td align="left">
                        <input type="hidden" name="id_p" value="${product.id_p}">
                    </td>
                </tr>

                <tr>
                    <td align="right">
                        Дійсний до
                    </td>
                    <td align="left">
                        <input type="date" name="date" value="${product.date}">

                    </td>
                </tr>

                <tr>
                    <td>

                    </td>
                    <td align="left">
                        <button type="submit">Редагувати</button>
                    </td>
                </tr>
            </table>


        </form>
    </div>
</div>