package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.News;

public class NewsServices {
//	CREATE TABLE [dbo].[news] (
//		    [id]          INT            IDENTITY (1, 1) NOT NULL,
//		    [title]       NVARCHAR (500) NOT NULL,
//		    [content]     NTEXT          NULL,
//		    [image]       VARCHAR (50)   NULL,
//		    [create_date] DATETIME2 (7)  DEFAULT (sysutcdatetime()) NOT NULL,
//		    [user_id]     INT            NOT NULL,
//		    [view_count]  INT            DEFAULT ((0)) NOT NULL,
//		    [is_active]   BIT            DEFAULT ((1)) NOT NULL,
//		    [cat_id]      INT            NOT NULL,
//		);

	public boolean addNews(News news) {

		try {

			String sql = "INSERT INTO news"
					+ "(title, content, image, create_date, user_id, view_count, is_active, cat_id) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

			Connection connection = DatabaseConnect.connection();

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, news.getTitle());
			statement.setString(2, news.getContent());
			statement.setString(3, news.getImage());
			statement.setDate(4, news.getCreateDate());
			statement.setInt(5, news.getUser().getId());
			statement.setInt(6, 0);
			statement.setBoolean(7, news.isActive());
			statement.setInt(8, news.getCategory().getId());

			int rows = statement.executeUpdate();

			connection.close();

			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

//	Viết 1 phương thức trả về danh sách bài viết của user đang login
// Có các thông tin đầy đủ của bài viết và tên người đăng bài (user.name)
//	và tên danh mục (categories.name)

//	SELECT * from news WHERE user_id=123
//	=> Danh sáh 
//	for lisst => SELECT * FROM user, SELECT * FROM categories

//	SELECT ... FORM ... JOIN ...

//	SELECT n.*, u.name as auth_name, c.name as cat_name
//	FROM news n 
//	JOIN users u ON n.user_id=u.id 
//	JOIN categories c ON n.cat_id=c.id 
//	WHERE n.user_id=1

}
