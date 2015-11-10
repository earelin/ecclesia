<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Ecclesia</title>
  <link rel="stylesheet" type="text/css"
      href="<s:url value="/resources/core/css/bootstrap.min.css" />" >
  <link rel="stylesheet" type="text/css"
      href="<s:url value="/resources/core/css/style.css" />" >
  <script src="<s:url value="/resources/core/js/jquery-1.11.3.min.js" />"></script>
</head>
<body>
  <div id="page">
    <header id="header">
      <t:insertAttribute name="header" />
    </header>
    <div id="content" class="container">
      <t:insertAttribute name="body" />
    </div>
    <footer id="footer">
      <t:insertAttribute name="footer" />
    </footer>
  </div>  
  <script src="<s:url value="/resources/core/js/bootstrap.min.js" />"></script>
</body>
</html>
