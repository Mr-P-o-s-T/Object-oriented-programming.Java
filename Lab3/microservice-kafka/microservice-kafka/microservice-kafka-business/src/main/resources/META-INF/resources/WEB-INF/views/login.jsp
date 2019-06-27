<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.4/css/uikit.css" type="text/css" />
    <script src="js/uikit.js"></script>
    <script src="js/uikit-icons.js"></script>
</head>
<body>
<section class="uk-section">
    <div class="uk-container">
        <h2 class="uk-heading-medium uk-text-center">Administrator login</h2>
    </div>
</section>


<div class="uk-container">
    <form action="<c:url value="/login"/>" class="uk-form-horizontal uk-position-center" method="POST">

        <div class="uk-margin">
            <div class="uk-inline">
                <%--suppress HtmlFormInputWithoutLabel --%>
                <input class="uk-input" type="text" name="login" placeholder="Login...">
            </div>
        </div>

        <div class="uk-margin">
            <div class="uk-inline">
                <%--suppress HtmlFormInputWithoutLabel --%>
                <input class="uk-input" type="password" name="password" placeholder="Password...">
            </div>
        </div>

        <button class="uk-button uk-button-default" type="submit">Submit</button>
    </form>
</div>

</body>
</html>
