package com.nasaproject.bean;

import java.util.List;
/**
 * This Class is Create Java Bean class of type User
 * @author Kami Hassanzadeh
 */
public class User {
	
	private int id;
	private String username;
	private String password;
	private List<Photo> favoritList;

	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Photo> getFavoritList() {
		return favoritList;
	}

	public void setFavoritList(List<Photo> favoritList) {
		this.favoritList = favoritList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", favoritList=" + favoritList
				+ "]";
	}
}
