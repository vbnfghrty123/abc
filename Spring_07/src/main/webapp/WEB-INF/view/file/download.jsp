<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다운로드</title>
</head>
<body>

<c:forEach var="file" items="${downloadFiles}" varStatus="status">
	<p>${status.count} : 
	<a href="${pageContext.request.contextPath}/resources/upload/${file.key}">${file.key}</a> (${file.value} Bytes)</p>  
</c:forEach>
    
</body>
</html>