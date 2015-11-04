<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Organizations</h2>

<table class="table">
  <c:forEach var="organization" items="${organizations}">
    <tr>
      <td>${organization.name}</td>
      <td>${organization.created}</td>
      <td>edit - delete</td>
    </tr>
  </c:forEach>
</table>
