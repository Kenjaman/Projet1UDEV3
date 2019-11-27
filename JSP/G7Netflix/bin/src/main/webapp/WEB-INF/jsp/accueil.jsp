<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>MyNetflixJSP</title>
  </head>
  <body>
  	<a href="<c:url value="/serie"/>">Serie</a>  
  	<a href="<c:url value="/saison"/>">Saison</a>  
  	<a href="<c:url value="/episode"/>">Episode</a>  
  </body>
</html>