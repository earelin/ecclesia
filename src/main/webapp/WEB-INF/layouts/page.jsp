<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ecclesia</title>
    <link rel="stylesheet" type="text/css"
        href="<s:url value="/resources/core/css/bootstrap.min.css" />" >
  </head>
  <body>
    <header id="header">
      <t:insertAttribute name="header" />
    </header>
    <div id="content" class="container">
      <t:insertAttribute name="body" />
    </div>
    <footer id="footer">
      <t:insertAttribute name="footer" />
    </footer>
  </body>
</html>
