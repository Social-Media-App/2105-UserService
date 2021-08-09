package rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.User;
import rev.service.UserService;

@RestController
@RequestMapping("/login-service")
public class LoginController {
	
	private UserService userServ;
	
	@Autowired
	public LoginController(UserService userServ) {
		super();
		this.userServ = userServ;
	}
	
	/**
	 * @author zacha 
	 * Takes in a user, only cares about its username and password then if it works
	 * returns the user it found else it returns a completely null user with -1 as its id
	 * @param user object
	 * @return user Object
	 */
	@PostMapping(value="/login")
	public @ResponseBody User login(@RequestBody User user){

		User loguser = userServ.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		if (loguser == null) {
			loguser = new User();
			loguser.setUserId(-1);
		}	
		return loguser;

	}
	/**
	 * @author zacha
	 * Creates a new user in the database and returns said user
	 * @param User object
	 * @return User object
	 */
	@PostMapping(value="/signup")
	public @ResponseBody User newUser(@RequestBody User user){
		return userServ.save(user);
	}
	
	@PostMapping(value="/resetpw")
	public @ResponseBody User resetPW(@RequestBody User user){
		System.out.println("reset pw");
		User updateuser = userServ.findByUserId(user.getUserId());
		updateuser.setPassword(user.getPassword());
		return userServ.save(updateuser);
	}
	

}

