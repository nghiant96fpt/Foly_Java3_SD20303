package com.fpoly.java3;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.RegisterBean;
import com.fpoly.java3.entities.User;
import com.fpoly.java3.services.UserServices;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		try {
//			Nhận dữ liệu từ các ô input ở form 
//			Kiểm tra lỗi ở các dữ liệu đã nhận được
//			Hiện thị lỗi nếu có và các giá trị đã nhận được 
			RegisterBean bean = new RegisterBean();

			BeanUtils.populate(bean, req.getParameterMap());

			req.setAttribute("bean", bean);

			if (bean.getErrors().isEmpty()) {
//				Không có lỗi xảy ra ở form
//				Chuyển tất cả dữ liệu ở bean qua entity 

				User user = new User();
				user.setEmail(bean.getEmail());
				user.setPassword(bean.getPassword());
				user.setName(bean.getName());
				user.setPhone(bean.getPhone());
				user.setGender(bean.getGender());
//				valueOf của Date sql nhận vào 1 cái chuỗi ngày tháng năm theo
//				theo định dạng yyyy-mm-dd
				Date birthDay = Date.valueOf(bean.getBirthDay());
				user.setBirthDay(birthDay);
				user.setRole(0);

				UserServices userServices = new UserServices();
				Boolean checkRegister = userServices.register(user);
				if (checkRegister) {
//					Đăng ký thành công 
//					req.setAttribute("error", "Đăng ký thành công");

					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				} else {
//					Đăng ký thất bại
					req.setAttribute("error", "Đăng ký thất bại");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", e.getMessage());

//			e.getMessage() nội dung lỗi được truyền từ server qua
		}
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}
