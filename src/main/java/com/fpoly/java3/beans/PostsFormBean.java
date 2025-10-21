package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsFormBean {
	private int id;
	private String title;
	private String desc;
	private Part image;
	private int category;
	private int status;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

//		Tiêu đề không được bỏ trống
//		Mô tả bài viết phải có từ 10 -> 300 ký tự 
//		File upload: phải là ảnh, kích thước tối đa 20KB
//		Danh mục bắt buộc chọn
//		Trạng thái bắt buộc chọn
//		Viết các câu lệnh điều kiện để kiểm tra các lỗi trên 
//		"" || "        "
//		title.trim().isEmpty()
		if (title.isBlank()) {
			map.put("errTitle", "Tiêu đề không rỗng");
		}
//		10 -> 300 từ 
//		String[] descArr = desc.trim().split(" "); // => ["Tiêu", "ddeef",...]
//		if(descArr.length > 300)
		if (desc.trim().length() < 10 || desc.trim().length() > 300) {
			map.put("errDesc", "Tiêu đề không rỗng");
		}
//		image/png, image/jpg,...
//		20KB => ? byte
		int maxSize = 1024 * 1024 * 10;
		if (image == null) {
			map.put("errImage", "File upload bắt buộc chọn");
		} else if (!image.getContentType().startsWith("image/")) {
			map.put("errImage", "File upload bắt buộc là ảnh");
		} else if (image.getSize() > maxSize) {
			map.put("errImage", "File upload có kích thước tối đa là 20KB");
		}

		if (category < 1) {
			map.put("errCategory", "Danh mục bắt buộc chọn");
		}

		if (status == 0) {
			map.put("errStatus", "Trạng thái bắt buộc chọn");
		}

		return map;
	}
}
