<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 100%">
    <div id="tooplate_wrapper4" >
        <form action="/recipeEdit?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">

            <table cellpadding="5px">
                <tr>
                    <td align="right">
                        Id:
                    </td>
                    <td align="left">
                        <input type="text" name="id_r" value="${recipe.id_r}">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Назва:
                    </td>
                    <td align="left">
                        <input type="text" name="name" value="${recipe.name }">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Категорія:
                    </td>
                    <td align="left">
                        <input type="text" name="category" value="${recipe.category }">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Опис:
                    </td>
                    <td align="left">
                        <input type="text" name="description" value="${recipe.description }">
                    </td>
                </tr>


                <tr>
                    <td align="right">
                        Зображення:
                    </td>
                    <td align="left">
                        <input type="file" name="image" value="${recipe.image}">
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