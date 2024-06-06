<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!-- ETAPE 5 : Exécution de la View -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
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
			<div class="card-header bg-primary text-white display-6">Liste
				des clients</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Civilité</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Email</th>
							<th>Date de naissance</th>
							<th>Adresse</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${clients}" var="c">
							<c:url value="/client" var="editUrl">
								<c:param name="mode" value="edit" />
								<c:param name="id" value="${c.id}" />
							</c:url>
							<c:url value="/client" var="removeUrl">
								<c:param name="mode" value="remove" />
								<c:param name="id" value="${c.id}" />
							</c:url>
							<fmt:parseDate value="${c.dtNaissance}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
							
							<tr>
								<td>${c.id}</td>
								<td>${c.civilite}</td>
								<td>${c.nom}</td>
								<td>${c.prenom}</td>
								<td>${c.email}</td>
								<td><fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy"/></td>
								<td>${c.adresse.rue} ${c.adresse.codePostal} ${c.adresse.ville}</td>
								<td><div class="btn-group btn-group-sm">
										<a href="${editUrl}" class="btn btn-primary"><i
											class="bi bi-pencil-square"></i></a> <a href="${removeUrl}"
											class="btn btn-danger"><i class="bi bi-trash"></i></a>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="card-footer">
				<c:url value="/client" var="addUrl">
					<c:param name="mode" value="add" />
				</c:url>
				<a href="${addUrl}" class="btn btn-success btn-lg"><i
					class="bi bi-plus-square"></i></a>
			</div>
		</div>
	</div>

	<script src="<c:url value="/assets/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>