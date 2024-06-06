<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!-- ETAPE 5 : Exécution de la View -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des adresses</title>
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-icons.min.css"/>">
</head>
<body>
	<header>
		<ul class="nav nav-pills m-5">
			<li class="nav-item"><a class="nav-link" href="<c:url value="/"/>">Accueil</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/commentaire"/>">Commentaire</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/fournisseur"/>">Fournisseur</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/produit"/>">Produit</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/client"/>">Client</a></li>
			<li class="nav-item"><a class="nav-link active" href="<c:url value="/adresse"/>">Adresse</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/commande"/>">Commande</a></li>
		</ul>
	</header>
	<div class="container-fluid">
		<div class="card mt-3">
			<div class="card-header bg-primary text-white display-6">Liste des adresses</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Rue</th>
							<th>Code postal</th>
							<th>Ville</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${mesAdresses}" var="adr">
						<c:url value="/adresse" var="editUrl">
							<c:param name="mode" value="edit"/>
							<c:param name="id" value="${adr.id}"/>
						</c:url>
						<c:url value="/remove" var="removeUrl">
							<c:param name="mode" value="remove"/>
							<c:param name="id" value="${adr.id}"/>
						</c:url>
						<tr>
							<td>${adr.id}</td>
							<td>${adr.rue}</td>
							<td>${adr.codePostal}</td>
							<td>${adr.ville}</td>
							<td><div class="btn-group btn-group-sm">
									<a href="${editUrl}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
									<a href="${removeUrl}"class="btn btn-danger"><i class="bi bi-trash"></i></a>
								</div></td>
						</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="card-footer">
				<c:url value="/adresse" var="addUrl">
					<c:param name="mode" value="add"/>
				</c:url>
				<a href="${addUrl}" class="btn btn-success btn-lg"><i
					class="bi bi-plus-square"></i></a>
			</div>
		</div>
	</div>

	<script src="<c:url value="/assets/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>