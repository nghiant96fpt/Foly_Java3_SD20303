package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		String title = req.getParameter("title"); // => abc

//		localhost:8080/java3/?name=abc => title = null

//		localhost:8080/java3/?title=true
//		=> title = true => type == String

//		url?queryparams (key=value&key=value&...)

//		String titleTest = req.getParameter("title "); // => null

		String contextPath = req.getContextPath();

//		Gửi giá trị của contextPath qua view thông qua model
//		hiển thị giá trị của contextPath lên giao diện 

		req.setAttribute("ctxPath", contextPath);

		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
