<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>

<c:forEach  var="i" begin="0" end="100" step="1">
    <c:if test="${i%2 == 0 }"><li>Un message n°${i} !</li></c:if>
</c:forEach>

</ul>
</body>
</html>