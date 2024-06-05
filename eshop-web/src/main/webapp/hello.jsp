<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<c:if test="${not empty param['prenom'] || not empty param['nom']}">
	<h1>Bonjour ${param['prenom']} ${param['nom']}</h1>
</c:if>

<c:if test="${empty param['prenom'] && empty param['nom']}">
	<form action="<c:url value='/hi'/>" method="post">
		Nom : <input type="text" name="nom"/><br/>
		Prénom <input type="text" name="prenom"/><br/>
		<button type="submit">Valider</button>
		
	</form>
</c:if>

</body>
</html>