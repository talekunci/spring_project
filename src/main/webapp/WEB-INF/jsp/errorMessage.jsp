<% String errorMessage = (String) request.getAttribute("errorMessage"); %>

<% if (errorMessage != null) {%>
    <div class="alert alert-danger mt-2 mb-2">
        <span>
            <c:out value = '${errorMessage}'/>
        </span>
    </div>
<% } %>