<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<form name='loginForm' method='POST'>
	<c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>
	<p>
		<label for="username">Username</label>
    <input type="text" id="username" name="username"/>
	</p>
	<p>
		<label for="password">Password</label>
		<input type="password" id="password" name="password"/>
	</p>
  <sec:csrfInput />
  <input type="submit" name="submit" class="btn">
</form>
