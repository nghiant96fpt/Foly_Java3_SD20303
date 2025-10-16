package com.fpoly.java3.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/admin/*", "/user/*", "/editer/*" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		System.out.println("Start do filter");

//		User đã đăng nhập chưa
//		- Cookie null => chưa đăng nhập => Chuyển về trang đăng nhập 
//		- userid hoặc role lưu ở Cookie không có giá trị (null)
//		=> Chưa đăng nhập => chuyển về đăng nhập 

//		User đã đăng nhập (có đủ userid và role ở Cookie)
//		Nếu path /user/* và role != 0 => Sai vai trò => Login
//		Nếu path /editer/* và role != 1 => Sai vai trò => Login
//		Nếu path /admin/* và role != 2 => Sai vai trò => Login

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();

		if (cookies == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String userId = null;
		String role = null;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId = cookie.getValue();
			}

			if (cookie.getName().equals("role")) {
				role = cookie.getValue();
			}
		}

		if (userId == null || role == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

//		Nếu path /user/* và role != 0 => Sai vai trò => Login
//		Nếu path /editer/* và role != 1 => Sai vai trò => Login
//		Nếu path /admin/* và role != 2 => Sai vai trò => Login

		String path = req.getRequestURI();
//		/Java3SD20303/admin/posts-form

//		role == 1
//		true && false && false => false && false => false 

//		role == 0

		if (path.contains("/user/") && !role.equals("0") && !role.equals("1")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (path.contains("/editer/") && !role.equals("1")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (path.contains("/admin/") && !role.equals("2")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		chain.doFilter(request, response);

	}

}
