package com.nasaproject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nasaproject.bean.User;

/**
 * An Interface for the UserService.class
 */
@Service
public interface IUserService {

	User findById(int id);
    
    User findByUsername(User user);
     
    void addUser(User user);
 
    List<User> findAllUsers(); 
	
}
