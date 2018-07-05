package com.nasaproject.repository;

import java.io.IOException;

import com.nasaproject.bean.Camera;
/**
 * 	An Interface for the PhotoRepository.class
 *  @author Kami Hassanzadeh
 */

public interface IPhotoRepository {
	
	Camera getImageIdByNumber(String camera);
	
	Camera getImageIdByPage(String pageNumber);
	
	String getAllImage();
}
