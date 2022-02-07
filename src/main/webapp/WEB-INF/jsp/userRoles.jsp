<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>User roles page</title>
        <%@ include file="headers.jsp" %>


    </head>
    <body>

        <%@ include file="navigation.jsp" %>

        <% ua.goit.springproject.dto.UserDto user = (ua.goit.springproject.dto.UserDto) request.getAttribute("user"); %>


        <div class="container">
            <div class="row">
                <h2>User ID=<c:out value = '${user.id}'/> roles page</h2>
            </div>

            <div class="row mb-2">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/users" type="button" class="btn btn-success">Back to users</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div>
                    <div class="form-floating mb-1">

                        <select class="form-select" id="roleId">

                            <option selected disabled>Select role</option>

                            <c:forEach var="role" items="${roles}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>

                        <label for="roleId">Add role</label>
                    </div>

                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group me-2" role="group" aria-label="Second group">
                            <button onclick="addRole()" type="button" class="btn btn-primary">Add</button>
                        </div>
                    </div>
                </div>


                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Role Id</th>
                        <th scope="col">Role Name</th>
                        <th scope="col">Action</th>
                    </tr>

                    </thead>
                        <tbody>
                            <c:forEach var="role" items="${userRoles}">
                                <tr>
                                    <td><c:out value = "${role.id}"/></td>
                                    <td><c:out value = "${role.name}"/></td>

                                    <td>
                                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                                <button onclick="doDelete('/users/roles/<c:out value = '${user.id}'/>?roleId=<c:out value = '${role.id}'/>')" type="button" class="btn btn-danger">Remove</button>
                                            </div>
                                        </div>
                                    </td>
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

            function addRole() {
                let roleId = document.getElementById('roleId').value;
                let userId = '<c:out value = '${user.id}'/>';

                let url = '/users/roles/' + userId + '?roleId=' + roleId;

                fetch(url, {
                    method: 'PUT'
                })
                .then(() => {
                        window.location.reload();
                    }
                );
            }
        </script>
    </body>
</html>