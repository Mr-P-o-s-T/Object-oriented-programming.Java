<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Race cast</title>
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
            Race cast
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
                <td>Horse nickname</td>
                <td>Jockey first name</td>
                <td>Jockey last name</td>
                <td>Coefficient</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="raceCasts" type="java.util.List"--%>
            <c:forEach var="raceCast" items="${raceCasts}">
                <tr>
                    <td>${raceCast.getId()}</td>
                    <td>${raceCast.getRace().getRacecourse()}</td>
                    <td>${raceCast.getHorse().getHorseNickname()}</td>
                    <td>${raceCast.getJockeyFirstname()}</td>
                    <td>${raceCast.getJockeyLastname()}</td>
                    <td>${raceCast.getCoefficient()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/raceCast"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="race_id">Race ID:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="race_id" type="text" name="race_id"
                           placeholder="Race ID...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="horse_id">Horse ID:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="horse_id" type="text" name="horse_id"
                           placeholder="Horse ID...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="jockey_firstname">Jockey first name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="jockey_firstname" type="text" name="jockey_firstname"
                           placeholder="Jockey first name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="jockey_lastname">Jockey last name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="jockey_lastname" type="text" name="jockey_lastname"
                           placeholder="Jockey last name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="coefficient">Coefficient:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="coefficient" type="text" name="coefficient"
                           placeholder="Coefficient...">
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
