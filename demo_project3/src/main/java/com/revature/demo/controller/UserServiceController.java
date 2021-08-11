package com.revature.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.demo.models.User;
import com.revature.demo.service.UserService;

@RestController
@RequestMapping("/user-service")
public class UserServiceController {

	private UserService userServ;

	@Autowired
	public UserServiceController(UserService userServ) {
		super();
		this.userServ = userServ;
	}
	
	@GetMapping(value = "/getAllUsers")
	public @ResponseBody List<User> getAllUsers(){
		System.out.println("Finding all users");
		return userServ.findAll();
	}
}
