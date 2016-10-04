<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 100%">
    <div id="tooplate_wrapper4" >
        <form action="/productEdit?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">

            <table cellpadding="5px">
                <tr>
                    <td align="right">
                        Id:
                    </td>
                    <td align="left">
                        <input type="text" name="id_p" value="${product.id_p}">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Назва:
                    </td>
                    <td align="left">
                        <input type="text" name="name" value="${product.name }">
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
                    <td align="right">
                        Зображення:
                    </td>
                    <td align="left">
                        <input type="file" name="image" value="${product.image}">
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