package com.fpoly.java3.beans;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsFormBean {
	private String title;
	private String desc;
	private Part imagePart;
	private int category;
	private int status;
}
