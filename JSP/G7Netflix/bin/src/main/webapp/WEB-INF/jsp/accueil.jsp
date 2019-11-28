<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>MyNetflixJSP</title>
  </head>
  <body>
  	<a href="<c:url value="/series"/>">Series</a>  
  	<a href="<c:url value="/saisons"/>">Saisons</a>  
  	<a href="<c:url value="/episodes"/>">Episodes</a>  
  </body>
</html>