<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <sec:authorize access="isAnonymous()">
        <s:url value="/" var="rootUrl" />
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
        <s:url value="/dashboard" var="rootUrl" />
      </sec:authorize>
      <a class="navbar-brand" href="${rootUrl}">Ecclesia</a>
    </div>
    
    <!-- User menu -->
    <sec:authorize access="isAuthenticated()">
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                
        <ul class="nav navbar-nav">
          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" aria-expanded="false">
                Administration <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="<s:url value="/admin/organization" />">Organizations</a></li>
                  <li><a href="<s:url value="/admin/user" />">Users</a></li>
                </ul>
            </li>
          </sec:authorize>
        </ul>
        
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
               role="button" aria-haspopup="true" aria-expanded="false"> 
              <sec:authentication property="principal.username" /> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li>
                  <s:url value="/logout" var="logoutUrl" />
                  <form:form action="${logoutUrl}">
                    <input type="submit" value="Logout" class="btn btn-link" />
                  </form:form>
                </li>
              </ul>
          </li>
        </ul>
      </div>
    </sec:authorize>
  </div>
</nav>
    