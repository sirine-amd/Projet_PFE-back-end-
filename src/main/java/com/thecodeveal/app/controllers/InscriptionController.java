package com.thecodeveal.app.controllers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecodeveal.app.entities.Authority;
import com.thecodeveal.app.entities.User;
import com.thecodeveal.app.responses.UserInfo;
import com.thecodeveal.app.services.AuthorityService;
import com.thecodeveal.app.services.UserService;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class InscriptionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	/*
	 * @PostMapping("/signup") public ResponseEntity<?> SignUp(@RequestBody User
	 * user) { userService.add(user); return
	 * ResponseEntity.ok("user ajouté avec succée");
	 * 
	 * }
	 */
	@PostMapping("/signup")	
	 public  ResponseEntity<?> SignUp(@RequestBody UserInfo U) {
		System.out.println(U.getRoles());

	
		Authority A= authorityService.findByRoleName((String) U.getRoles());
		System.out.println(A);
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        
		User U1 =new User();
		U1.setAdresse(U.getAdressse());
		U1.setNom(U.getNom());
		U1.setPrenom(U.getPrenom());
		U1.setPassword(U.getPassword());
		U1.setGenre(U.getGenre());
		U1.setDate_de_naissance(U.getDate_de_naissance());
		U1.setEmail(U.getEmail());
		U1.setEtat_civil(U.getEtat_civil());
		U1.setUserName(U.getUserName());
		U1.setAuthority(A);
		U1.setNumero_de_telephone(U.getNumero_de_telephone());
		U1.setCreatedAt(dateObj);
		 userService.add(U1);		
		 return ResponseEntity.ok("user ajouté avec succée");
		
	}
	

}
