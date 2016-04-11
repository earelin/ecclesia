<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<h2>Register</h2>
<div class="row"><div class="col-xs-12 col-md-6 col-md-offset-3">
<form:form modelAttribute="register">
  <div class="form-group">
    <label form="username">Name</label>
    <form:input path="username" class="form-control" />
  </div>
  <div class="form-group">
    <label form="email">Email</label>
    <form:input type="text" path="email" class="form-control" />
  </div>
  <div class="form-group">
    <label form="password">Password</label>
    <form:password path="password" class="form-control" />    
  </div>
  <div class="form-group">
    <label form="repeatPassword">Repeat password</label>
    <form:password path="repeatPassword" class="form-control" />
  </div>
  <div class="container-fluid">
    <div class="progress">
      <div id="complexity-bar" class="progress-bar" role="progressbar"></div>
    </div>
    <p class="text-center">Password complexity <span id="complexity">0%</span></p>
  </div>
  <input type="submit" value="Register" class="btn btn-primary">
</form:form>
</div></div>
<script src="<s:url value="/resources/core/js/jquery.complexify.min.js" />"></script>
<script src="<s:url value="/resources/core/js/jquery.validate.min.js" />"></script>
<script src="<s:url value="/resources/core/js/jquery.validate.bootstrap.js" />"></script>
<script type="text/javascript">
  $('#password').complexify({}, function (valid, complexity) {
		var progressBar = $('#complexity-bar');

		progressBar.toggleClass('progress-bar-success', valid);
		progressBar.toggleClass('progress-bar-danger', !valid);
		progressBar.css({'width': complexity + '%'});

		$('#complexity').text(Math.round(complexity) + '%');
	});
  
  $("#register").validate({
   rules: {
     username: {
       required: true
     },
     email: {
       required: true
     },
     password: {
       required: true
     },
     repeatPassword: {
       required: true
     }
   }
 });
</script>
