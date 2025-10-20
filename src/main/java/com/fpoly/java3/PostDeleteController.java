package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.services.NewsServices;

@WebServlet("/editer/posts-delete")
public class PostDeleteController extends HttpServlet {
//	GET
//	POST

//	Gửi id news
//	lấy user id từ cookie
// select trong bảng yêu thích 
//	Nếu có dữ liệu => đã yêu thích => delete fav 
//	Ngược lại => insert 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Cookie[] cookies = req.getCookies();

			String userId = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
					break;
				}
			}

			String id = req.getParameter("id");

			NewsServices newsServices = new NewsServices();

			newsServices.deleteNewsByIdAndUserId(Integer.parseInt(id), Integer.parseInt(userId));

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(req.getContextPath() + "/editer/posts");

//		req.getRequestDispatcher("")
//		Gọi db để lấy danh sách bài viết => gửi jsp để hiển thị
//		Url ở website sẽ hiển thị url delete 
	}

}

//Gửi id => để xoá đối tượng trong db 
// GET => id gửi trực tiếp trên url?
// url => localhost/java3/editer/posts-delete?id=1

// Biết code => script cho id chạy từ 1 => 10000 để gọi vào url