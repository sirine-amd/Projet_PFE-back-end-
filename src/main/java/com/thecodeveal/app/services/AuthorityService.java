package com.thecodeveal.app.services;

import com.thecodeveal.app.entities.Authority;
import com.thecodeveal.app.entities.User;


public interface AuthorityService {
	 
	public Authority add(Authority authority);
	public Authority findByRoleName(String roleName);

}
