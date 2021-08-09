package rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.User;
import rev.service.UserService;

@RestController
@RequestMapping("/user-service")
public class UserController {
	
	private UserService userServ;
	
	
	@Autowired
	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
	}
	
	/**
	 * @author zacha
	 * 
	 * Get all users from the database
	 * @param none	 * 
	 * @return Array List of Users
	 */
	@GetMapping(value="/getallusers")
	public @ResponseBody List<User> getAllUsers(){
		System.out.println("Finding all users");
		return userServ.findAll();
	}
	/**
	 * @author zacha
	 * Takes in the user to be changed and changes it
	 * @param User user
	 * @return returns the changed user
	 */
	@PostMapping(value="/update")
	public @ResponseBody User updateUser(@RequestBody User user){
		System.out.println("Update User");
		return userServ.save(user);
	}
	
	

}
