<!DOCTYPE html>
<html>
<head>
    <title>View producer</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
    <body>

    <%@ include file="navigation.jsp" %>
    <% ua.goit.springproject.dto.ProducerDto producer = (ua.goit.springproject.dto.ProducerDto) request.getAttribute("producer"); %>

    <div class="container">
            <div class="row">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/producers" type="button" class="btn btn-success">Back to producers</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="mb-3">
                    <label for="id" class="form-label">ID</label>
                    <input type="text" disabled class="form-control"
                           value="${producer.id}"
                           id="id" placeholder="Id">
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control"
                           value="${producer.name}"
                           id="name" placeholder="Producer name">
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

            function save() {
             let body = {
             <% if(producer.getId() != null) {%>
                 id: id.value,
              <% } %>
                name: name.value
              }
              <% if(producer.getId() == null) {%>
                 let url = '/producers';
                 let method = 'POST';
              <% } else { %>
                 let url = '/producers/<%= producer.getId() %>';
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
                    window.location.href = '/producers';
                }
                );
            }
        </script>
    </body>
</html>