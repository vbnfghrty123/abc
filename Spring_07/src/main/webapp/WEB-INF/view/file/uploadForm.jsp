<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/file/upload" method="post" enctype="multipart/form-data" >
<input type="file" name ="file">
<%-- 여러개 선택 
<input type="file" name ="files" multiple>--%>
<input type="text" name="id">
<input type="submit" value="전송">
</form>
</body>
</html>