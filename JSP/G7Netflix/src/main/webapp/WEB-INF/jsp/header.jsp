<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel=stylesheet type="text/css" href="./style.css">
<section id="topheader">
	<header>
		<div id="logo">
		</div>
		<div>
			<h1 id="titre">MyNetflix</h1>
		</div>
	</header>
	<nav class='menu'>
		<ul>
			<li><a href="<c:url value="/series"/>">Series</a></li>
			<li><a href="<c:url value="/saisons"/>">Saisons</a></li>
			<li><a href="<c:url value="/episodes"/>">Episodes</a></li>
		</ul>
	</nav>
</section>
<c:if test="${erreurs}">
<section class="erreur">
	<c:out value="${erreurs}" />
</section>
</c:if>