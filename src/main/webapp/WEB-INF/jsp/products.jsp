<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Products page</title>
        <%@ include file="headers.jsp" %>


    </head>
<body>

<%@ include file="navigation.jsp" %>


    <div class="container">
        <div class="row">
            <h2>Products page</h2>
        </div>

        <div class="row">
            <sec:authorize access="hasAuthority('Admin')">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/products/new" type="button" class="btn btn-primary">New</a>
                    </div>
                </div>
            </sec:authorize>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Producer Name</th>
                    <th scope="col">Cost</th>

                    <sec:authorize access="hasAuthority('Admin')">
                        <th scope="col">Action</th>
                    </sec:authorize>

                </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td><c:out value = "${product.id}"/></td>
                            <td><c:out value = "${product.name}"/></td>
                            <td><c:out value = "${product.producer.name}"/></td>
                            <td><c:out value = "${product.cost}"/></td>

                            <sec:authorize access="hasAuthority('Admin')">
                                <td>
                                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                        <div class="btn-group me-2" role="group" aria-label="Second group">
                                            <a href="/products/<c:out value = '${product.id}'/>" type="button" class="btn btn-warning">Edit</a>
                                            <button onclick="doDelete('/products/<c:out value = '${product.id}'/>')" type="button" class="btn btn-danger">Remove</button>
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