<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Users</h2>

<table class="table">
  <thead>
    <tr>
      <th>Username</th>
      <th>Email</th>
      <th>Created</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.created}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
