package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.User;

public class UserServices {

	public boolean register(User user) throws Exception {
//		kết nối db 
		Connection connection = DatabaseConnect.connection();

//		Check trùng email
//		nếu trùng thông báo lỗi
//		không trùng thì thực hiện tiếp lệnh bên dưới 

		String checkEmailSQL = "SELECT id FROM users WHERE email=?";
		PreparedStatement statementCheckEmail = connection.prepareStatement(checkEmailSQL);
		statementCheckEmail.setString(1, user.getEmail());

		ResultSet resultSet = statementCheckEmail.executeQuery();

		if (resultSet.next()) {
			connection.close();
			throw new Exception("Email đã tồn tại");
		}

		String insertSQL = "INSERT INTO users(email, password, name, birthday, gender, phone, role) VALUES(?, ?, ?, ?, ?, ?, ?)";
//		Statement => INSERT INTO users(email, password, name, birthday, gender, phone, role) VALUES('" + email + "'
		PreparedStatement statement = connection.prepareStatement(insertSQL);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getName());
		statement.setDate(4, user.getBirthDay());
		statement.setInt(5, user.getGender());
		statement.setString(6, user.getPhone());
		statement.setInt(7, user.getRole());

		int rows = statement.executeUpdate();

		connection.close();

		return rows > 0;
	}

//	Xây dựng các phương thức
//	Xoá user theo id
//	Cập nhật thông tin user (name, ngày sinh, giới tính sdt)
//	Đổi mật khẩu (kiểm tra mật khẩu cũ trùng với db mới thực hiện update)

	public boolean delete(int id) {
		try {
			String deleteSQL = "DELETE FROM users WHERE id=?";

			Connection connection = DatabaseConnect.connection();
			PreparedStatement statement = connection.prepareStatement(deleteSQL);
			statement.setInt(1, id);

			boolean checkDelete = statement.execute();

			connection.close();

			return checkDelete;

//			PreparedStatement => kiểm tra trong giá trị được set vào ?
//			nếu có tồn tại các key của lệnh sql thì không cho thực thi 
//			Statement => Không có kiểm tra 

//			Xoá user theo email 
//			DELETE FORM users WHERE email=''
//			Truyền lên "abc@gmail.com" => DELETE FORM users WHERE email='abc@gmail.com'
//			' OR '1'='1 => DELETE FORM users WHERE email='' OR '1'='1'

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	Cập nhật thông tin user (name, ngày sinh, giới tính sdt)

	public boolean updateInfo(User user) {
		try {
			String updateSQL = "UPDATE users SET name=?, birthday=?, gender=?, phone=? WHERE id=?";

			Connection connection = DatabaseConnect.connection();
			PreparedStatement statement = connection.prepareStatement(updateSQL);
			statement.setString(1, user.getName());
			statement.setDate(2, user.getBirthDay());
			statement.setInt(3, user.getGender());
			statement.setString(4, user.getPhone());
			statement.setInt(5, user.getId());

			boolean checkUpdate = statement.execute();

			connection.close();

			return checkUpdate;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	Đổi mật khẩu (kiểm tra mật khẩu cũ trùng với db mới thực hiện update)

	public boolean changePassword(int id, String newPassword, String oldPassword) {
		try {
//			kiểm tra mật khẩu cũ trùng với db

			String selectUser = "SELECT password FROM users WHERE id=?";

			Connection connection = DatabaseConnect.connection();

			PreparedStatement statement = connection.prepareStatement(selectUser);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
//				có tồn tại user

				String password = resultSet.getString("password");

				if (password.equals(oldPassword)) {
					String updateSQL = "UPDATE users SET password=? WHERE id=?";

					PreparedStatement statementUpdate = connection.prepareStatement(updateSQL);
					statementUpdate.setString(1, newPassword);
					statementUpdate.setInt(2, id);

					boolean checkUpdate = statementUpdate.execute();

					connection.close();

					return checkUpdate;
				}
				connection.close();
				return false;
			}
			connection.close();
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean login(String email, String password) {
//		WHERE email => password => match => password == password
		try {
			String sqlUser = "SELECT password FROM users WHERE email=?";
			Connection connection = DatabaseConnect.connection();

			PreparedStatement statement = connection.prepareStatement(sqlUser);
			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String passwordDB = resultSet.getString("password");

				connection.close();
				return passwordDB.equals(password);
			}
			connection.close();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
