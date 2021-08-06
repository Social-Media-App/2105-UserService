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
	
	@PostMapping(value="/login")
	public @ResponseBody User login(@RequestBody User user){

		User loguser = userServ.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		if (loguser == null) {
			loguser = new User();
			loguser.setUserId(-1);
		}	
		return loguser;

	}
	

}

