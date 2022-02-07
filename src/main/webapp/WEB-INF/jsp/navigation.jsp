<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
      <a class="navbar-brand" href="/products">Products</a>



        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/producers">Producers</a>
                </li>
                <sec:authorize access="hasAuthority('Admin')">
                    <li class="nav-item">
                        <a class="nav-link" href="/users">Users</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>

        <form action="/logout">
            <input class="btn btn-danger" type="submit" value="Logout"/>
        </form>
  </div>
</nav>