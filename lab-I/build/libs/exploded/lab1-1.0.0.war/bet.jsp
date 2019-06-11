<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bets</title>
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
            Bets
        </h2>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Client Id</td>
                <td>Race Id</td>
                <td>Horse Id</td>
                <td>Bet</td>
                <td>Bet type</td>
                <td>Second horse Id</td>
                <td>Third horse Id</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="bets" type="java.util.List"--%>
            <c:forEach var="bet" items="${bets}">
                <tr>
                    <td>${bet.getId()}</td>
                    <td>${bet.getClientId()}</td>
                    <td>${bet.getRaceId()}</td>
                    <td>${bet.getHorseId()}</td>
                    <td>${bet.getBet()}</td>
                    <td>${bet.getBetType()}</td>
                    <td>${bet.getScndHorseId()}</td>
                    <td>${bet.getThrdHorseId()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/bet"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="client_id">Client ID:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="client_id" type="text" name="client_id"
                           placeholder="Client ID...">
                </div>
            </div>

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
                <label class="uk-form-label" for="bet">Bet:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="bet" type="text" name="bet"
                           placeholder="Bet...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="bet_type">Bet type:</label>
                <div class="uk-form-controls">
                    <select class="uk-select" id="bet_type" type="text" name="bet_type">
                        <option value="Win">Win</option>
                        <option value="Each-way">Each-way</option>
                        <option value="Forecast">Forecast</option>
                        <option value="Tricast">Tricast</option>
                        <option value="Head-to-head">Head-to-head</option>
                    </select>
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="scnd_horse_id">Second horse ID:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="scnd_horse_id" type="text" name="scnd_horse_id"
                           placeholder="Second horse ID...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="thrd_horse_id">Third horse ID:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="thrd_horse_id" type="text" name="thrd_horse_id"
                           placeholder="Third horse ID...">
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
