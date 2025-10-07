package com.fpoly.java3.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {
	private int id;
	private String title;
	private String content;
	private String image;
	private Date createDate;
	private User user; // FK
	private int viewCount;
	private boolean isActive;
	private Category category; // FK
}
/*
 * lấy ra danh sách bài viết có các thông tin của bài viêt và tên danh mục, tên
 * người viết bài SELECT ... FROM news JOIN users JOIN cat while(){ 3 đối tượng
 * news, user, category setUser => news,... }
 */
