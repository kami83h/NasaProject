package com.nasaproject.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nasaproject.bean.Camera;
import com.nasaproject.bean.Photo;
import com.nasaproject.connection.JDBCDatabase;

/**
 * A Photo Service which handles the (Database)-Bean connection.
 * @author Kami Hassanzadeh
 *
 */
public class PhotoService implements IPhotoService {

	private JDBCDatabase databaseConnection;
	private PreparedStatement pstmt;

	public PhotoService() {
		this.databaseConnection = new JDBCDatabase();
	}

	@Override
	public List<Camera> selectFavoritById(int id) {
		List<Camera> list = new ArrayList<>();
		try {
			setPreparedStatement("SELECT user_id,photo_id,image FROM favorit where user_id="+ id +";");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Camera camera = new Camera();
				camera.setId(rs.getInt("photo_id"));
				camera.setUserId(rs.getInt("user_id"));
				camera.setImage(rs.getString("image"));
				list.add(camera);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deletePhoto(int id) {
		try {
			String query = " DELETE FROM favorit WHERE photo_id="+ id +";";
			setPreparedStatement(query);
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Photo> findAllPhoto() {
		List<Photo> photoList = new ArrayList<Photo>();
		try {
			setPreparedStatement("SELECT * FROM photo");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Photo photo = new Photo();
				photo.setId(rs.getInt("photo_id"));
				photo.setCamera(rs.getString("camera"));
				photoList.add(photo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return photoList;

	}

	@Override
	public String getPhotoById(int id) {
		String result ="";
		try {
			setPreparedStatement("SELECT camera FROM photo WHERE photo_id="+ id +";");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getString("camera");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	@Override
	public void addPhotoToFavorit(int userId, Camera camera) {

		try {
			String query = " INSERT INTO favorit (user_id,photo_id,image) values (?, ?, ?)";
			setPreparedStatement(query);
			pstmt.setInt (1, userId);
			pstmt.setInt (2, camera.getId());
			pstmt.setString (3, camera.getImage());
			pstmt.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public PreparedStatement setPreparedStatement(String input) {
		try {
			pstmt = databaseConnection.getConnection()
					.prepareStatement(input);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;	
	}
}
