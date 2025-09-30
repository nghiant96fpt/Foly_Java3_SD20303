package com.fpoly.java3;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.entities.Category;

@WebServlet("/admin/posts-form")
public class PostFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < 5; index++) {
			Category category = new Category();
			category.setId(index + 1);
			category.setName("Category " + (index + 1));
			categories.add(category);
		}

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < 5; index++) {
			Category category = new Category();
			category.setId(index + 1);
			category.setName("Category " + (index + 1));
			categories.add(category);
		}

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
