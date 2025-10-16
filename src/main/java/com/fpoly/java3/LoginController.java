package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.LoginBean;
import com.fpoly.java3.entities.User;
import com.fpoly.java3.services.UserServices;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Login Get Method");

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		try {
			LoginBean bean = new LoginBean();

			BeanUtils.populate(bean, req.getParameterMap());

			req.setAttribute("bean", bean);

			if (bean.getErrors().isEmpty()) {
				UserServices userServices = new UserServices();
				User user = userServices.login(bean.getUsername(), bean.getPassword());

				if (user != null) {
					System.out.println("Login success!!");

//					role == 0 => User
//					role == 1 => Editer
//					role == 2 => Admin

//					Làm sao để biết được user đang đăng nhập vai trò gì?
//					userId => user nào đang sử dụng hệ thống?

//					loginServices => true || false 
//					loginServices => User || null 

//					if (user.getRole() == 0) {
//						resp.sendRedirect(req.getContextPath() + "/home");
//						return;
//					}
//
//					if (user.getRole() == 1) {
//						resp.sendRedirect(req.getContextPath() + "/editer/posts");
//						return;
//					}
//
//					if (user.getRole() == 2) {
//						resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
//						return;
//					}

					Cookie cookieUserId = new Cookie("userId", String.valueOf(user.getId()));
					cookieUserId.setMaxAge(60 * 60 * 24 * 7);

					Cookie cookieRole = new Cookie("role", String.valueOf(user.getRole()));
					cookieRole.setMaxAge(60 * 60 * 24 * 7);

					resp.addCookie(cookieUserId);
					resp.addCookie(cookieRole);

					switch (user.getRole()) {
					case 0:
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					case 1:
						resp.sendRedirect(req.getContextPath() + "/editer/posts");
						return;
					case 2:
						resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
						return;
					}

//					3 dòng => array 
//					String[] paths = { "/home", "/editer/posts", "/admin/dashboard" };
//					resp.sendRedirect(req.getContextPath() + paths[user.getRole()]);
//					return;

				} else {
					System.out.println("Login fail!!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		System.out.println("Post Method");
//
////		lấy dữ liệu từ ô input
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		System.out.println(username);
//		System.out.println(password);
//
////		Trả dữ liệu lại để hiển thị lên form
//		req.setAttribute("username", username);
//		req.setAttribute("password", password);

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
