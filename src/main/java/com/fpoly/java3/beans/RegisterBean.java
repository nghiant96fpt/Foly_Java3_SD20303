package com.fpoly.java3.beans;

import java.util.Calendar;
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
	private String birthDay; // 2025-10-12, 2010-10-11

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

//		Lấy giá trị năm từ birthday 
//		birthDay.split() => array String => parse từ string => int 

		try {
			String[] birthDayArr = birthDay.split("-");
//			["2025", "10", "12"]
			int year = Integer.parseInt(birthDayArr[0]);

			Calendar calendar = Calendar.getInstance();
			int yearNow = calendar.get(Calendar.YEAR);

			if (yearNow - year < 18) {
				map.put("errBirthDay", "Ngày sinh phải lớn hơn 18 tuổi");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errBirthDay", "Ngày sinh phải lớn hơn 18 tuổi");
		}

//		if (new Date().getYear() - birthDay.getYear() < 18) {
//			map.put("errBirthDay", "Ngày sinh phải lớn hơn 18 tuổi");
//		}

		if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
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

		return map;
	}
}