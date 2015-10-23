<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title><g:layoutTitle default="Grails"/></title>
      <asset:stylesheet src="bootstrap.css"/>
      <asset:stylesheet src="bootstrap-theme.css"/>
      <asset:stylesheet src="application.css"/>
      <g:layoutHead/>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
          <div class="navbar-header">
            <g:link class="navbar-brand" uri="/">Socialismo Precario</g:link>
          </div>
        </div>
      </nav>
    </header>  
    <div id="content" class="container">
      <g:layoutBody/>
    </div>
    <asset:javascript src="jquery-1.11.3.js"/>
    <asset:javascript src="bootstrap.js"/>
  </body>
</html>
