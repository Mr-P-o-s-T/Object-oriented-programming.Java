<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Horses</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.4/css/uikit.css" type="text/css"/>
    <script src="js/uikit.min.js"></script>
    <script src="js/uikit-icons.min.js"></script>
</head>
<body>

<section class="uk-section">
    <div class="uk-container">
        <h2 class="uk-heading-medium uk-text-center">
            Horses
        </h2>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Horse Nickname</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="horses" type="java.util.List"--%>
            <c:forEach var="client" items="${horses}">
                <tr>
                    <td>${client.getId()}</td>
                    <td>${client.getHorseNickname()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/horse"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="horse_nickname">Horse Nickname:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="horse_nickname" type="text" name="horse_nickname"
                           placeholder="Horse Nickname...">
                </div>
            </div>

            <div class="uk-margin">
                <button class="uk-button uk-button-default" type="submit">Submit</button>
            </div>
        </form>
    </div>

    <div class="uk-container">
        <a href="index.jsp">Home</a>
    </div>
</section>
</body>
</html>
