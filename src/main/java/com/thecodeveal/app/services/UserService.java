package com.thecodeveal.app.services;

import java.util.List;

import com.thecodeveal.app.entities.User;



public interface UserService {
	
	public User findByUserName(String userName);
	
	public User add(User user);

	public List<User> getUsers();

}
