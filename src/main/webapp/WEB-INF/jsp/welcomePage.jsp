<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Users page</title>
        <%@ include file="headers.jsp" %>
    </head>

    <body>
        <div class="container">
            <div class="row">
                <h1 style="text-align:center;">Welcome to home page.</h1>
                <p style="font-size:180%;text-align:center;">Select what you want to do.</p>
            </div>

            <div role="toolbar" aria-label="Toolbar with button groups">
                <ul class="navbar-nav">
                    <c:forEach var="btn" items="${buttons}">
                        <form class="row" action="${btn.key}">
                            <input class="btn btn-lg btn-primary btn-block mb-2" type="submit" value="${btn.value}"/>
                        </form>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>