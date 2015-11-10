<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<form name='loginForm' method='POST'>
	<c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>
	<div class="form-group">
		<label for="username">Username</label>
    <input type="text" id="username" name="username" class="form-control" />
	</div>
	<div class="form-group">
		<label for="password">Password</label>
    <input type="password" id="password" name="password" class="form-control" />
	</div>
  <sec:csrfInput />
  <input type="submit" name="submit" class="btn btn-primary">
</form>
