<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Races</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.4/css/uikit.css" type="text/css"/>
    <script src="js/uikit.js"></script>
    <script src="js/uikit-icons.js"></script>
</head>
<body>
<section class="uk-section">
    <div class="uk-container">
        <h2 class="uk-heading-medium uk-text-center">
            Races
        </h2>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Racecourse</td>
                <td>Date</td>

            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="races" type="java.util.List"--%>
            <c:forEach var="client" items="${races}">
                <tr>
                    <td>${client.getId()}</td>
                    <td>${client.getRacecourse()}</td>
                    <td>${client.getDate()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/race"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="racecourse">Racecourse:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="racecourse" type="text" name="racecourse"
                           placeholder="Racecourse...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="date">Date:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="date" type="date" name="date"
                           placeholder="Date...">
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
