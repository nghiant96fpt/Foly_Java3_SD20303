<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
</head>
<body>
	<div class="container">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Tên bài viết</th>
		      <th scope="col">Hình ảnh</th>
		      <th scope="col">Tác giả</th>
		      <th scope="col">Danh mục</th>
		      <th scope="col">Lượt xem</th>
		      <th scope="col">Trạng thái</th>
		      <th scope="col">Hành động</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${news}" var="item">
		  		<tr>
			      <th scope="row">${item.id}</th>
			      <td>${item.title}</td>
			      <td>
			      	<img style="width: 100px; height: 100px"
			      		src="${pageContext.request.contextPath}/assets/images/${item.image}"/>
			      </td>
			      <td>${item.user.name}</td>
			      <td>${item.category.name}</td>
			      <td>${item.viewCount}</td>
			      <td>${item.active ? 'Hiển thị' : 'Ẩn'}</td>
			      <td>
			      	<!-- click để chuyển qua trang post form -->
			      	<!-- lấy dữ liệu từ db để hiển thị lên input => SELECT -->
			      	<a href="${pageContext.request.contextPath}/editer/posts-form?id=${item.id}" class="btn btn-warning">Sửa</a>
			      	
			      	
			      	<!-- Khi xoá xong sẽ quay về trang danh sách bài viết -->
			      	<!-- Chức năng xoá không có giao diện (không cần tạo và quản lý JSP) -->
			      	<form method="POST"
			      		action="${pageContext.request.contextPath}/editer/posts-delete"
			      		onSubmit="return confirm('Bạn có muốn xoá không?');">
			      		<input type="hidden" name="id" value="${item.id}"/>
			      		<button class="btn btn-danger" type="submit">Xoá</button>
			      	</form>
			      </td>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>