package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.User;

public class UserServices {

	public boolean insert(User user) {
		try {
//			kết nối db 
			Connection connection = DatabaseConnect.connection();

			String insertSQL = "INSERT INTO users(email, password, name, birthday, gender, phone, role) VALUES(?, ?, ?, ?, ?, ?, ?)";
//			Statement => INSERT INTO users(email, password, name, birthday, gender, phone, role) VALUES('" + email + "'
			PreparedStatement statement = connection.prepareStatement(insertSQL);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setDate(4, user.getBirthDay());
			statement.setInt(5, user.getGender());
			statement.setString(6, user.getPhone());
			statement.setInt(7, user.getRole());

			boolean insertCheck = statement.execute();

			connection.close();

			return insertCheck;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	Xây dựng các phương thức
//	Xoá user theo id
//	Cập nhật thông tin user (name, ngày sinh, giới tính sdt)
//	Đổi mật khẩu (kiểm tra mật khẩu cũ trùng với db mới thực hiện update)
}
