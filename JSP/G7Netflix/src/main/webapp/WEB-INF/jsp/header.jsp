<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="topheader">
	<header>
		<div id="logo">
			<img alt="logo" src="<c:url value='/images/KenFlixIcon.svg'/>" />
		</div>
		<div>
			<h1 id="titre">MyNetflix</h1>
		</div>
	</header>
	<nav>
		<ul>
			<li><a href="<c:url value="/series"/>">Serie</a></li>
			<li><a href="<c:url value="/saisons?idserie=0"/>">Saison</a></li>
			<li><a href="<c:url value="/episodes?idserie=0&$idsaison=0"/>">Episode</a></li>
		</ul>
	</nav>
</section>