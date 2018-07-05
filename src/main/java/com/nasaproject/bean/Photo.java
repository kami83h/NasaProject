package com.nasaproject.bean;

/**
 * This Class is Create Java Bean class of type Photo
 * @author Kami Hassanzadeh
 */
public class Photo {
	
	private int id;
	private String camera;
	
	public Photo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", camera=" + camera + "]";
	}
}
