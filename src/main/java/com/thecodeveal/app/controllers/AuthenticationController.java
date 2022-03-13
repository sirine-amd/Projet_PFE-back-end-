package com.thecodeveal.app.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecodeveal.app.config.JWTTokenHelper;
import com.thecodeveal.app.entities.User;
import com.thecodeveal.app.requests.AuthenticationRequest;
import com.thecodeveal.app.responses.LoginResponse;
import com.thecodeveal.app.responses.UserInfo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
		System.out.println("cou");
		System.out.println(authenticationRequest.getPassword());
		System.out.println(authenticationRequest.getUserName());

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		System.out.println("cocuocu");

		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("cou2");

		User user=(User)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		System.out.println("cou3");

		LoginResponse response=new LoginResponse();
		response.setToken(jwtToken);
		

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
	    userInfo.setNom(userObj.getNom());
		userInfo.setPrenom(userObj.getPrenom());
		userInfo.setDate_de_naissance(userObj.getDate_de_naissance());
		userInfo.setNumero_de_telephone(userObj.getNumero_de_telephone());
		userInfo.setEmail(userObj.getEmail());
		userInfo.setGenre(userObj.getGenre());
		userInfo.setAdressse(userObj.getAdresse());
        userInfo.setEtat_civil(userObj.getEtat_civil());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		userInfo.setUserName(userObj.getUsername());
		
		return ResponseEntity.ok(userInfo);
		
		
		
	}
}
