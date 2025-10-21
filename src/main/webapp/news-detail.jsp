<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${news.title}</h1>
	<h1>Lượt yêu thích: ${news.favCount}</h1>
	<%-- <c:if test="${news.favId == null}">
		<!-- icon -->
	</c:if>
	<c:if test="${news.favId != null}">
		<!-- icon -->
	</c:if> --%>
	<h1>${news.favId == null ? 'Chưa yêu thích' : 'Yêu thích'}</h1>
</body>
</html>