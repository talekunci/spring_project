<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Users page</title>
        <%@ include file="headers.jsp" %>


    </head>
<body>

<%@ include file="navigation.jsp" %>


    <div class="container">
        <div class="row">
            <h2>Users page</h2>
        </div>

        <div class="row">
            <sec:authorize access="hasAuthority('Admin')">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/users/new" type="button" class="btn btn-primary">New</a>
                    </div>
                </div>
            </sec:authorize>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>

                    <sec:authorize access="hasAuthority('Admin')">
                        <th scope="col">Action</th>
                    </sec:authorize>

                </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><c:out value = "${user.id}"/></td>
                            <td><c:out value = "${user.email}"/></td>
                            <td><c:out value = "${user.firstName}"/></td>
                            <td><c:out value = "${user.lastName}"/></td>

                            <sec:authorize access="hasAuthority('Admin')">
                                <td>
                                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                        <div class="btn-group me-2" role="group" aria-label="Second group">
                                            <a href="/users/roles/<c:out value = '${user.id}'/>" type="button" class="btn btn-info">Roles</a>
                                            <a href="/users/<c:out value = '${user.id}'/>" type="button" class="btn btn-warning">Edit</a>
                                            <button onclick="doDelete('/users/<c:out value = '${user.id}'/>')" type="button" class="btn btn-danger">Remove</button>
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