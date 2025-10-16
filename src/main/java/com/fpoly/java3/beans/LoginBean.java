package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginBean {
	private String username;
	private String password;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

		if (!username.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			map.put("errUsername", "Email không đúng định dạng");
		}

		if (password.isBlank()) {
			map.put("errPassword", "Mật khẩu không được rỗng");
		}

		return map;
	}
}
