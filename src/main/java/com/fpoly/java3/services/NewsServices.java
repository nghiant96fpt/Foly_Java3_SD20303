package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.News;
import com.fpoly.java3.entities.User;

public class NewsServices {

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

// Lấy danh sách bài viết của 1 user (editer)
	public List<News> getNewsListByUserId(int userId) {
		List<News> newsList = new ArrayList<News>();

		try {
			String sql = "SELECT n.*, u.name as auth_name, c.name as cat_name FROM news n"
					+ " JOIN users u ON n.user_id=u.id JOIN categories c ON n.cat_id=c.id WHERE n.user_id=?";

			Connection connection = DatabaseConnect.connection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt("id"));
				news.setTitle(resultSet.getString("title"));
				news.setContent(resultSet.getString("content"));
				news.setImage(resultSet.getString("image"));
				news.setActive(resultSet.getBoolean("is_active"));
				news.setCreateDate(resultSet.getDate("create_date"));
				news.setViewCount(resultSet.getInt("view_count"));

				User user = new User();
				user.setName(resultSet.getString("auth_name"));
				news.setUser(user);

				Category category = new Category();
				category.setName(resultSet.getString("cat_name"));
				news.setCategory(category);

				newsList.add(news);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newsList;
	}

//	DELETE FORM news WHERE id=? => Editer A có thể xoá bài viết của Editer B

//	DELETE FORM news WHERE id=? AND user_id=?
//  UPDATE SET WHERE id=? AND user_id=?

	public boolean deleteNewsByIdAndUserId(int id, int userId) {
		try {
			String delete = "DELETE FROM news WHERE id=? AND user_id=?";

			Connection connection = DatabaseConnect.connection();
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
			statement.setInt(2, userId);

			int rows = statement.executeUpdate();

			connection.close();

			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateNews(News news) {
		try {
			String update = "UPDATE news SET title=?, content=?, image=?, is_active=?, cat_id=?"
					+ " WHERE id=? AND user_id=?";

			Connection connection = DatabaseConnect.connection();
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getContent());
			statement.setString(3, news.getImage());
			statement.setBoolean(4, news.isActive());
			statement.setInt(5, news.getCategory().getId());
			statement.setInt(6, news.getId());
			statement.setInt(7, news.getUser().getId());

			int rows = statement.executeUpdate();

			connection.close();

			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

//	CREATE TABLE [dbo].[news] (
//  [id]          INT            IDENTITY (1, 1) NOT NULL,
//  [title]       NVARCHAR (500) NOT NULL,
//  [content]     NTEXT          NULL,
//  [image]       VARCHAR (50)   NULL,
//  [create_date] DATETIME2 (7)  DEFAULT (sysutcdatetime()) NOT NULL,
//  [user_id]     INT            NOT NULL,
//  [view_count]  INT            DEFAULT ((0)) NOT NULL,
//  [is_active]   BIT            DEFAULT ((1)) NOT NULL,
//  [cat_id]      INT            NOT NULL,
//);

	public News getNewsByIdAndUserId(int id, int userId) {
		try {
			String sql = "SELECT * FROM news WHERE id=? AND user_id=?";
//			trả về 1 dòng 

			Connection connection = DatabaseConnect.connection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, userId);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt("id"));
				news.setTitle(resultSet.getString("title"));
				news.setContent(resultSet.getString("content"));
				news.setImage(resultSet.getString("image"));
				news.setActive(resultSet.getBoolean("is_active"));
				news.setCreateDate(resultSet.getDate("create_date"));
				news.setViewCount(resultSet.getInt("view_count"));

				User user = new User();
				user.setId(userId);
				news.setUser(user);

				Category category = new Category();
				category.setId(resultSet.getInt("cat_id"));
				news.setCategory(category);

				connection.close();

				return news;
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
