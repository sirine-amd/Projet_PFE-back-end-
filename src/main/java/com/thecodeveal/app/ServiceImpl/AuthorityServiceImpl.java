package com.thecodeveal.app.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thecodeveal.app.entities.Authority;
import com.thecodeveal.app.entities.User;
import com.thecodeveal.app.repository.AuthorityRepo;
import com.thecodeveal.app.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepo authorityRepo;

	public Authority findByRoleName(String roleName) {
		return authorityRepo.findByRoleName(roleName);
	}

	@Override
	public Authority add(Authority authority) {
		
		return authorityRepo.save(authority);
	}
	
	
}
