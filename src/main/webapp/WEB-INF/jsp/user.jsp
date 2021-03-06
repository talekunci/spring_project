<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>View user</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
    <body>

        <%@ include file="navigation.jsp" %>
        <% ua.goit.springproject.dto.UserDto user = (ua.goit.springproject.dto.UserDto) request.getAttribute("user"); %>
        <% boolean creatingPage = request.getAttribute("javax.servlet.forward.request_uri").toString().endsWith("/new"); %>

        <div class="container">
                <div class="row mb-3">
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group me-2" role="group" aria-label="Second group">
                            <a href="/users" type="button" class="btn btn-success">Back to users</a>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="mb-3">
                        <label for="id" class="form-label">ID</label>
                        <input type="text" disabled class="form-control"
                               value="${user.id}"
                               id="id" placeholder="Id">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">e-Mail</label>
                        <input type="text" class="form-control"
                               value="${user.email}"
                               id="email" placeholder="User email">
                    </div>
                    <div class="mb-3">
                        <label for="firstName" class="form-label">First Name</label>
                        <input type="text" class="form-control"
                               value="${user.firstName}"
                               id="firstName" placeholder="User firstName">
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Last Name</label>
                        <input type="text" class="form-control"
                               value="${user.lastName}"
                               id="lastName" placeholder="User lastName">
                    </div>

                    <% if (creatingPage) { %>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control"
                                   id="password" placeholder="User Password">
                        </div>
                    <% } %>
                </div>

                <div class="row">
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group me-2" role="group" aria-label="Second group">
                            <button onclick="save()" type="button" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </div>
        </div>

            <script>
                let id = document.getElementById('id');
                let email = document.getElementById('email');
                let firstName = document.getElementById('firstName');
                let lastName = document.getElementById('lastName');

                <% if (creatingPage) { %>
                let password = document.getElementById('password');
                <% } %>

                function save() {
                 let body = {
                 <% if(user.getId() != null) {%>
                    id: id.value,
                  <% } %>
                  <% if (creatingPage) { %>
                    password: password.value,
                  <% } %>
                    email: email.value,
                    firstName: firstName.value,
                    lastName: lastName.value
                  }
                  <% if(user.getId() == null) {%>
                     let url = '/users';
                     let method = 'POST';
                  <% } else { %>
                     let url = '/users/<%= user.getId() %>';
                     let method = 'PUT';
                  <% } %>
                    fetch(url, {
                        method: method,
                        headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                        },
                        body: JSON.stringify(body)
                    })
                    .then(() => {
                            window.location.href = '/users';
                        }
                    );
                }
            </script>
    </body>
</html>