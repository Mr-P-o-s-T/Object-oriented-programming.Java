<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Clients</title>
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
            Clients
        </h2>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>First name</td>
                <td>Last name</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="clients" type="java.util.List"--%>
            <c:forEach var="client" items="${clients}">
                <tr>
                    <td>${client.getId()}</td>
                    <td>${client.getFirstname()}</td>
                    <td>${client.getLastname()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/client"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="firstname">First name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="firstname" type="text" name="firstname"
                           placeholder="First name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="lastname">Last name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="lastname" type="text" name="lastname"
                           placeholder="Last name...">
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
