package com.fpoly.java3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // hàm xây dựng không tham số
@AllArgsConstructor // hàm xây dựng đầy đủ tham số
@Data // Getter/Setter
//@Setter
//@Getter
public class Category {
	private int id;
	private String name;
}
