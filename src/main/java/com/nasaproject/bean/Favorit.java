package com.nasaproject.bean;
/**
 * This Class is Create Java Bean class of type FavoritImage
 * @author Kami Hassanzadeh
 *
 */
public class Favorit {
	
	private int userId;
	private int photoId;
	
	public Favorit() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	@Override
	public String toString() {
		return "Favorit [userId=" + userId + ", photoId=" + photoId + "]";
	};
}
