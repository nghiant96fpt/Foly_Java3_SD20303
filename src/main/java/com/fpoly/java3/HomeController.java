package com.fpoly.java3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.entities.User;

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

//		req.setAttribute("ctxPath", "abc");

//		Tạo 1 đối tượng user
//		Có các trường dữ liệu
//		email, password, name, gender, phone, role, birthDay
//		Có hàm xây dựng không tham số và đầy đủ tham số
//		Có Getter/Setter 

//		Khai báo 1 biến user
//		Khởi tạo và set tất cả giá trị vào đối tượng user 
		User user = new User();
		user.setEmail("email@gmail.com");
		user.setName("Nguyen Van A");
		user.setPassword("123456");
		user.setPhone("0123456789");
		user.setGender(true);
		user.setRole(0); // role == 0 => User || role == 1 => Admin

//		Ngày tháng năm sinh của cá nhân 
//		23/09/2005
//		new Date() => 23/09/2025 
		Date date = new Date(2005, 8, 23);

		user.setBirthDay(date);

		req.setAttribute("user", user);

//		Khai báo 1 mảng User có 3 item (Không dùng Array List)
//		Set giá trị name vào từng item trong mảng user đó 

		User[] users = new User[3];
//		users[0] = new User();
//		users[0].setName("Nguyen Van Index 0");
//
//		users[1] = new User();
//		users[1].setName("Nguyen Van Index 1");
//
//		users[2] = new User();
//		users[2].setName("Nguyen Van Index 2");

//		dùng for 

		for (int index = 0; index < users.length; index++) {
			users[index] = new User();
			users[index].setName("Nguyen Van Index " + index);
		}

		req.setAttribute("users", users);

		ArrayList<User> usersArrayList = new ArrayList<User>();
//		Danh sách user 
		usersArrayList.add(user);
		req.setAttribute("usersArrayList", usersArrayList);

		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
