package com.fpoly.java3;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.PostsFormBean;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.News;
import com.fpoly.java3.entities.User;
import com.fpoly.java3.services.CategoryServices;
import com.fpoly.java3.services.NewsServices;

@MultipartConfig
@WebServlet("/editer/posts-form")
public class PostFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CategoryServices categoryServices = new CategoryServices();
		List<Category> categories = categoryServices.getList();

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		CategoryServices categoryServices = new CategoryServices();
		List<Category> categories = categoryServices.getList();

		req.setAttribute("categories", categories);

//		lấy dữ liệu từ input file 
//		Part image = req.getPart("image");
//
////		tên file được lưu trên máy của user khi upload 
//		System.out.println(image.getSubmittedFileName());
//
////		kiểu của file khi được upload 
////		image => image/png || image/jpg || ...
////		video => video/mp4 || ...
////		audio => audio/mp3 || ...
////		pdf = > application/pdf
//		System.out.println(image.getContentType());
//
////		kích thước của file được upload (byte)
//		System.out.println(image.getSize());
//		convert 25MB => ? byte
//		int maxSize = 1024 * 1024 * 25;

		try {
			PostsFormBean bean = new PostsFormBean();

			BeanUtils.populate(bean, req.getParameterMap());
//			Chỉ convert được string, int, boolean,...

			Part image = req.getPart("image");
			bean.setImage(image);

			req.setAttribute("bean", bean);

			if (bean.getErrors().isEmpty()) {
//				TODO
//				Lưu ảnh vào project => tên ảnh
//				Lưu các thông tin của bài viết và tên ảnh vào DB

//				image/png, image/jpg, image/jpge
				String type = bean.getImage().getContentType().split("/")[1];
//				bean.getImage().getContentType().split("/") => ["image", "png"]

//				new Date().getTime() => Thời gian hiện tại - 1/1/1900 => dãy số ms 
				String name = String.format("%s.%s", new Date().getTime(), type);
//				12131313213 => thiếu kiểu của ảnh .png, .jpg 
//				12131313213.png,  12131313213.jpg

//				Đường đẫn lưu file trong project
//				String path = "/assets/images/" + name;

				String path = String.format("/assets/images/%s", name);

//				Đừng đẫn cứng lưu file bên trong server tomcat
				String tomcatPath = req.getServletContext().getRealPath(path);

//				Lưu file vào đường đãn cứng của tomcat
				bean.getImage().write(tomcatPath);

				System.out.println(tomcatPath);

//				Insert dữ liệu vào db
//				Convert beans to entity 
				News news = new News();
				news.setTitle(bean.getTitle());
				news.setContent(bean.getDesc());
				news.setImage(name);

				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
//				Tháng 0 -> 11
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				String date = String.format("%d-%d-%d", year, month, day);
				news.setCreateDate(java.sql.Date.valueOf(date));

				Cookie[] cookies = req.getCookies();

				String userId = "";
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("userId")) {
						userId = cookie.getValue();
						break;
					}
				}

				User user = new User();
				user.setId(Integer.parseInt(userId));
				news.setUser(user);
				news.setViewCount(0);
				news.setActive(bean.getStatus() == 1);
				Category category = new Category();
				category.setId(bean.getCategory());
				news.setCategory(category);

				NewsServices newsServices = new NewsServices();
				boolean checkAddNews = newsServices.addNews(news);

				if (checkAddNews) {
					System.out.println("Thêm thành công");
				} else {
					System.out.println("Thêm thất bại");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
