package rev.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.User;
import rev.service.UserService;
import rev.utilities.RandomToken;
import rev.utilities.SendingMail;

@RestController
@RequestMapping("/user-service")
public class UserController {
	
	private UserService userServ;
	
	
	@Autowired
	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
	}
	
	@GetMapping(value="/getallusers")
	public @ResponseBody List<User> getAllUsers(){
		System.out.println("Finding all users");
		return userServ.findAll();
	}
	
	@PostMapping(value="/update")
	public @ResponseBody User newUser(@RequestBody User user){
		System.out.println("Update User");
		return userServ.save(user);
	}
	
	/**
	 * @author Matthew Precilio
	 * 
	 * Send an email to reset the password
	 * 
	 * @param username
	 * @return int: -1 no such user; 1 success
	 */
	@PostMapping(value="/send-email")
	public Integer sendEmail(@RequestBody User username) {
		System.out.println(username);
		User forgotPassUser = userServ.findByUsername(username.getUsername());
		if(forgotPassUser == null) return -1;
		String token = RandomToken.randomToken();
		//
		/* add logic to change stored token */
		//
		try {
			//Passes email and the session's token to SendEmail class
			//this sends the reset password to the email + the token for security verification
			SendingMail.sendMail(forgotPassUser.getEmail(), forgotPassUser.getUsername(),token);
			
//			loggy.info("A token has been sent to " + forgotPassUser.getEmail());
		} catch (MessagingException e) {
			System.out.println("Error occured");
//			loggy.error("Error sending the mail (controllerUserEmail): ", e);
			e.printStackTrace();
		}
		
		
		return 1;
		
		
	}
	
	

}
