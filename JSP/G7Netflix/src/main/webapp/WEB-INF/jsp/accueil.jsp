<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>G7NetFlix</title>
  </head>
  <body>
  	<a href="<c:url value="/series"/>">Serie</a>  
  	<a href="<c:url value="/saisons"/>">Saison</a>  
  	<a href="<c:url value="/episodes"/>">Episode</a>  
  </body>
</html>