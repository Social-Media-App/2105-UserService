package rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value="/users")
	public @ResponseBody List<User> getAllUsers(){
		System.out.println("Finding all users");
		return userServ.findAll();
	}
	
	
	

}
