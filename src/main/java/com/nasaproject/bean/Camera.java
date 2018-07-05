package com.nasaproject.bean;
/**
 * This Class is Create Java Bean class of type Camera
 * @author Kami Hassanzadeh
 *
 */
public class Camera {
	
	private int id;
	private int userId;
	private String image;
	
	public Camera() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Camera [id=" + id + ", userId=" + userId + ", image=" + image + "]";
	}
}
