<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<form:form modelAttribute="register">
  <div class="form-group">
    <label form="username">Name</label>
    <form:input path="username" class="form-control" />
  </div>
  <div class="form-group">
    <label form="email">Email</label>
    <form:input type="email" path="email" class="form-control" />
  </div>
  <div class="form-group">
    <label form="password">Password</label>
    <form:input path="password" class="form-control" />
  </div>
  <div class="form-group">
    <label form="repeatPassword">Repeat password</label>
    <form:input path="repeatPassword" class="form-control" />
  </div>
  <input type="submit" name="Register" class="btn btn-default">
</form:form>
