<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>View user</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
    <body>
        <% ua.goit.springproject.dto.UserDto user = (ua.goit.springproject.dto.UserDto) request.getAttribute("user"); %>

        <div class="container">
            <form>
                <div class="row">
                    <h2>Registration page</h2>
                </div>

                <div class="row">
                    <div class="mb-3">
                        <label for="email" class="form-label">e-Mail(login)<span style="color:red;">*</span></label>
                        <input type="text" class="form-control"
                        value="${user.email}"
                        id="email" placeholder="User email"
                        required autofocus>
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
                    <div class="mb-3">
                        <label for="password" class="form-label">Password<span style="color:red;">*</span></label>
                        <input type="password" class="form-control"
                               id="password" placeholder="User Password"
                               required>
                    </div>

                    <button onclick="register()" type="button" class="btn btn-lg btn-primary btn-block">Register</button>
                </div>
            </form>
        </div>

        <script>
            let email = document.getElementById('email');
            let firstName = document.getElementById('firstName');
            let lastName = document.getElementById('lastName');
            let password = document.getElementById('password');
            let roleId = ${defaultRoleId};

            function register() {
                let body = {
                    password: password.value,
                    email: email.value,
                    firstName: firstName.value,
                    lastName: lastName.value,
                    roleId: roleId
                }
                let url = '/registration';
                fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    body: JSON.stringify(body)
                })
                .then( _ => {
                    window.location.href = '/login';
                }
                );
            }
        </script>
    </body>
</html>