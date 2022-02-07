<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Producers page</title>
        <%@ include file="headers.jsp" %>


    </head>
<body>

<%@ include file="navigation.jsp" %>


    <div class="container">
        <div class="row">
            <h2>Producers page</h2>
        </div>

        <div class="row">

            <sec:authorize access="hasAuthority('Admin')">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/producers/new" type="button" class="btn btn-primary">New</a>
                    </div>
                </div>
            </sec:authorize>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>

                    <sec:authorize access="hasAuthority('Admin')">
                        <th scope="col">Action</th>
                    </sec:authorize>

                </tr>
                </thead>
                <tbody>
                    <c:forEach var="producer" items="${producers}">
                        <tr>
                            <td><c:out value = "${producer.id}"/></td>
                            <td><c:out value = "${producer.name}"/></td>

                            <sec:authorize access="hasAuthority('Admin')">
                                <td>
                                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                        <div class="btn-group me-2" role="group" aria-label="Second group">
                                            <a href="/producers/<c:out value = '${producer.id}'/>" type="button" class="btn btn-warning">Edit</a>
                                            <button onclick="doDelete('/producers/<c:out value = '${producer.id}'/>')" type="button" class="btn btn-danger">Remove</button>
                                        </div>
                                    </div>
                                </td>
                            </sec:authorize>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function doDelete(url) {
            fetch(url, {
                method: 'DELETE'
            })
            .then(() => {
                    window.location.reload();
                }
            );
        }
    </script>
</body>
</html>