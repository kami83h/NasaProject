package com.nasaproject.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nasaproject.bean.Favorit;
import com.nasaproject.connection.JDBCDatabase;

/**
 * A Favorit Service which handles the (Database)-Bean connection.
 * @author Kami Hassanzadeh
 *
 */
public class FavoritService implements IFavoritService {

	private JDBCDatabase databaseConnection;
	private PreparedStatement pstmt;
	
	public  FavoritService() {
		this.databaseConnection = new JDBCDatabase();
	}

	@Override
	public List<Favorit> findListById(int userId) {

		List<Favorit> favoritList = new ArrayList<Favorit>();
		setPreparedStatement("SELECT * FROM favorit WHERE user_id = "+ userId +";");
		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Favorit favorit = new Favorit();
				favorit.setPhotoId(rs.getInt("photo_id"));
				favorit.setUserId(rs.getInt("user_id"));
				favoritList.add(favorit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favoritList;
	}

	@Override
	public void addToFavorit(Favorit favorit) {
		
		try {
			setPreparedStatement("INSERT INTO favorit VALUES (?, ?)");
			pstmt.setInt (1, favorit.getUserId());
			pstmt.setInt (2, favorit.getPhotoId());
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Favorit> findAllFavorit() {
		
		List<Favorit> favoritList = new ArrayList<Favorit>();
		setPreparedStatement("SELECT * FROM favorit;");
		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Favorit favorit = new Favorit();
				favorit.setUserId(rs.getInt("user_id"));
				favorit.setPhotoId(rs.getInt("photo_id"));
				favoritList.add(favorit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favoritList;
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
	
	public boolean isInFavorit(List<Favorit> favoritList, int id) {

		for(Favorit list : favoritList) {
			if(list.getPhotoId() == id) {
				return true;
			}
		}
		return false;
	}

}
