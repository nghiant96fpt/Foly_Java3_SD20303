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
		<div class="col-6 offset-3">
			<form method="POST"
				action="${pageContext.request.contextPath}/admin/posts-form"
				enctype="multipart/form-data">
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Tiêu đề</label>
				  <input value="${bean.title}" name="title" type="text" class="form-control">
				  <small class="text-danger">${bean.errors.errTitle}</small>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Nội dung</label>
				  <textarea name="desc" class="form-control" rows="3">${bean.desc}</textarea>
				  <small class="text-danger">${bean.errors.errDesc}</small>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Hình ảnh</label>
				  <input name="image" type="file" class="form-control" accept="image/*">
				  <small class="text-danger">${bean.errors.errImage}</small>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Danh mục bài viết</label>
				  <select name="category" class="form-select" aria-label="Default select example">
					  <option ${bean == null || bean.category == 0 ? 'selected' : '' } value="0">----Chọn danh mục------</option>
					  <c:forEach items="${categories}" var="cat">
					  	<option ${cat.id == bean.category ? 'selected' : ''}  value="${cat.id}">${cat.name}</option>
					  </c:forEach>
				</select>
				<small class="text-danger">${bean.errors.errCategory}</small>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Trạng thái bài viết</label>
				  <div class="form-check">
					  <input ${bean.status == 1 ? 'checked' : ''} name="status" value="1" class="form-check-input" type="radio" id="radioDefault1">
					  <label class="form-check-label" for="radioDefault1">
					   	Hiển thị
					  </label>
					</div>
					<div class="form-check">
					  <input ${bean.status == 2 ? 'checked' : ''} name="status" value="2" class="form-check-input" type="radio" id="radioDefault2">
					  <label class="form-check-label" for="radioDefault2">
					   	Ẩn
					  </label>
					</div>
					<small class="text-danger">${bean.errors.errStatus}</small>
				</div>
				
				<button type="submit" class="btn btn-primary">Thêm bài viết</button>
			</form>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>