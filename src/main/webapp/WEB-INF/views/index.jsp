<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="jumbotron">
  <h1>Ecclesia</h1>
  <p>A software for a democratic world</p>
  <sec:authorize access="isAnonymous()">
    <p><a class="btn btn-primary btn-lg" href="<s:url value="/register" />" role="button">Register</a></p>
  </sec:authorize>
</div>
