package com.thecodeveal.app.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.thecodeveal.app.entities.User;
import com.thecodeveal.app.repository.UserDetailsRepository;
import com.thecodeveal.app.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDetailsRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public User add(User user) {
		String pwd = user.getPassword();
		user.setPassword(passwordEncoder.encode(pwd));
		return userRepo.save(user);
	}

	@Override
	public List<User> getUsers() {

		return userRepo.findAll();
	}

}
