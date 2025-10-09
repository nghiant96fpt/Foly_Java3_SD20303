package com.fpoly.java3.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterBean {
	private String email;
	private String password;
	private String name;
	private int gender;
	private String phone;
	private Date birthDay;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

		if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$\n")) {
			map.put("errEmail", "Email không đúng định dạng");
		}

		if (password.length() < 6) {
			map.put("errPassword", "Mật khẩu phải có ít nhất 6 ký tự");
		}

		if (name.isBlank()) {
			map.put("errName", "Tên không rỗng");
		}

		if (gender == 0) {
			map.put("errGender", "Giới tính bắt buộc nhập");
		}

		if (!phone.matches("^0\\d{9}$")) {
			map.put("errPhone", "Số điện thoại không đúng định dạng");
		}

		if (new Date().getYear() - birthDay.getYear() < 18) {
			map.put("errBirthDay", "Ngày sinh phải lớn hơn 18 tuổi");
		}

		return map;
	}
}