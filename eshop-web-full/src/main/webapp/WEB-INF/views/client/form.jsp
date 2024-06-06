<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!-- ETAPE 5 : Exécution de la View -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition d'un client</title>
<link rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap-icons.min.css"/>">
</head>
<body>
	<header>
		<ul class="nav nav-pills m-5">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/"/>">Accueil</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/commentaire"/>">Commentaire</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/fournisseur"/>">Fournisseur</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/produit"/>">Produit</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="<c:url value="/client"/>">Client</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/adresse"/>">Adresse</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/commande"/>">Commande</a></li>
		</ul>
	</header>
	<div class="container-fluid">
		<div class="card mt-3">
			<form action="<c:url value="/client"/>" method="post">
				<input type="hidden" name="mode" value="save" />
				<input type="hidden" name="id" value="${client.id}" />
				<div class="card-header bg-primary text-white display-6">Edition
					d'un client</div>
				<div class="card-body">
					<div class="form-group">
						<label for="civilite">Civilité:</label> 
						<select class="form-control" id="civilite" name="civilite">
							<option value=""></option>
							<c:forEach items="${civilites}" var="civ">
								<option value="${civ}" ${client.civilite==civ?'selected':''}>${civ}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="nom">Nom:</label> <input type="text"
							class="form-control" id="nom" name="nom"
							value="${client.nom}" />
					</div>
					<div class="form-group">
						<label for="prenom">Prénom:</label> <input type="text"
							class="form-control" id="prenom" name="prenom"
							value="${client.prenom}" />
					</div>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" name="email"
							value="${client.email}" />
					</div>
					<div class="form-group">
						<label for="dtNaissance">Date de naissance:</label> <input type="date"
							class="form-control" id="dtNaissance" name="dtNaissance"
							value="${client.dtNaissance}" />
					</div>
				</div>
				<div class="card-footer">
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn btn-success">
							<i class="bi bi-check-square-fill"></i>
						</button>
						<c:url value="/client" var="cancelUrl">
							<c:param name="mode" value="cancel"/>
						</c:url>
						
						<a class="btn btn-warning" href="${cancelUrl}"><i
							class="bi bi-backspace-fill"></i></a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="<c:url value="/assets/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>
