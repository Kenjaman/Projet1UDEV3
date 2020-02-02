<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel=stylesheet type="text/css" href="./style.css">

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div>
			<h1 id="titre"><a href="<c:url value="/accueil"/>">MyNetflix</a></h1>
		</div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/series"/>">Series</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/saisons"/>">Saisons</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/episodes"/>">Episodes</a>
            </li>
            </ul>
        </div>
    </nav>
</header>
<c:if test="${erreurs}">
<section class="erreur">
	<c:out value="${erreurs}" />
</section>
</c:if>