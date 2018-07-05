package com.nasaproject.service;

import java.util.List;

import com.nasaproject.bean.Camera;
import com.nasaproject.bean.Photo;

/**
 * An Interface for the PhotoService.class
 * @author Kami Hassanzadeh
 *
 */
public interface IPhotoService {
	
	List<Camera> selectFavoritById(int id);
	
	String getPhotoById(int id);
	
	void addPhotoToFavorit(int userId, Camera camera);
	
	void deletePhoto(int id);
	
	List<Photo> findAllPhoto();

}
