<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>View product</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
    <body>

    <%@ include file="navigation.jsp" %>
    <% ua.goit.springproject.dto.ProductDto product = (ua.goit.springproject.dto.ProductDto) request.getAttribute("product"); %>

    <div class="container">
            <div class="row">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/products" type="button" class="btn btn-success">Back to products</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="mb-3">
                    <label for="id" class="form-label">ID</label>
                    <input type="number" disabled class="form-control"
                           value="${product.id}"
                           id="id" placeholder="Id">
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control"
                           value="${product.name}"
                           id="name" placeholder="Product name">
                </div>
                <div class="mb-3">
                    <label for="cost" class="form-label">Cost</label>
                    <input type="number" class="form-control"
                           value="${product.cost}"
                           id="cost" placeholder="Product cost">
                </div>

                <div class="form-floating">

                    <select class="form-select" id="producerId">

                        <% if (product.getProducer() == null){ %>
                            <option selected disabled>Select producer</option>
                        <% }else{ %>
                            <option selected disabled value="${product.producer.id}">${product.producer.name}</option>
                        <% } %>

                        <c:forEach var="producer" items="${producers}">
                            <option value="${producer.id}">${producer.name}</option>
                        </c:forEach>
                    </select>

                    <label for="producerId">Producer</label>
                </div>
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
            let name = document.getElementById('name');
            let cost = document.getElementById('cost');
            let producerId = document.getElementById('producerId');

            function save() {
             let body = {
             <% if(product.getId() != null) {%>
                 id: id.value,
              <% } %>
                name: name.value,
                cost: cost.value,
                producerId: producerId.value
              }
              <% if(product.getId() == null) {%>
                 let url = '/products';
                 let method = 'POST';
              <% } else { %>
                 let url = '/products/<%= product.getId() %>';
                 let method = 'PUT';
              <% } %>
                fetch(url, {
                    method: method,
                    headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                    },
                    body: JSON.stringify(body)
                })
                .then( _ => {
                    window.location.href = '/products';
                }
                );
            }
        </script>
    </body>
</html>