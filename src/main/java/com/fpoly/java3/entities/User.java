package com.fpoly.java3.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // hàm xây dựng không tham số
@AllArgsConstructor // hàm xây dựng đầy đủ tham số
@Data // Getter/Setter
//@Setter
//@Getter
public class User {
	private int id;
	private String email;
	private String password;
	private String name;
	private String phone;
	private int gender;
	private Date birthDay;
	private int role;
//	0 == user
//	1 == editer
//	2 == admin
}
