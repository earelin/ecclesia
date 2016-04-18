<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Organizations</h2>

<table class="table"> 
  <thead>
    <tr>
      <th>Name</th>
      <th>Created</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="organization" items="${organizations}">
      <tr>
        <td>${organization.name}</td>
        <td>${organization.created}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
