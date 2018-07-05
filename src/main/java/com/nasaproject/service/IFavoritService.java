package com.nasaproject.service;

import java.util.List;
import java.util.Map;

import com.nasaproject.bean.Favorit;
import com.nasaproject.bean.Photo;

/**
 * An Interface for the FavoritService.class
 * @author Kami Hassanzadeh
 *
 */
public interface IFavoritService {
	
	List<Favorit> findListById(int userId);
	
	void addToFavorit(Favorit favorit);
	
	List<Favorit> findAllFavorit();

}
