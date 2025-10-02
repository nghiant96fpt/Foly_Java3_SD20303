package com.fpoly.java3;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.PostsFormBean;
import com.fpoly.java3.entities.Category;

@MultipartConfig
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
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		ArrayList<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < 5; index++) {
			Category category = new Category();
			category.setId(index + 1);
			category.setName("Category " + (index + 1));
			categories.add(category);
		}

		req.setAttribute("categories", categories);

//		lấy dữ liệu từ input file 
		Part image = req.getPart("image");

//		tên file được lưu trên máy của user khi upload 
		System.out.println(image.getSubmittedFileName());

//		kiểu của file khi được upload 
//		image => image/png || image/jpg || ...
//		video => video/mp4 || ...
//		audio => audio/mp3 || ...
//		pdf = > application/pdf
		System.out.println(image.getContentType());

//		kích thước của file được upload (byte)
		System.out.println(image.getSize());
//		convert 25MB => ? byte
//		int maxSize = 1024 * 1024 * 25;

		try {
			PostsFormBean bean = new PostsFormBean();

			BeanUtils.populate(bean, req.getParameterMap());

			req.setAttribute("bean", bean);

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
