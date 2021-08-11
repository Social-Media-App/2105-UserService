package rev.controller;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.SeeFirst;
import rev.model.User;
import rev.service.SeeFirstService;
import rev.service.UserService;
import rev.utilities.RandomToken;
import rev.utilities.SendingMail;

@RestController
@RequestMapping("/user-service")
public class UserController {
	
	private UserService userServ;
	private SeeFirstService seeFirstServ;
	
	
	
	
	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
	}


	@Autowired
	public UserController(UserService userServ, SeeFirstService seeFirstServ) {
		super();
		this.userServ = userServ;
		this.seeFirstServ = seeFirstServ;
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
	
	/**
	 * @author zacha
	 * STILL A WORK IN PROGRESS
	 * NEEDS JWT
	 * takes in a user and adds it's id to the list of ids in the JWT
	 * NEEDS TO UPDATE THE JWT
	 * @param User object
	 * @return returns
	 */
	
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
	
	/**
	 * Add a new See First using user from JWT and User id sent in
	 * @author zacha
	 * @param user only matters about User_id
	 * @return User object
	 */
	
	@PostMapping(value="/addseefirst")
	public @ResponseBody User newSeeFirst(@RequestBody User user){
		user = userServ.findByUserId(user.getUserId());
		seeFirstServ.save(new SeeFirst(userServ.findByUserId(1), user));
		
		
		return user;
		
	}
	
	/**
	 * Takes in a SeeFirst Id and deletes it
	 * @author zacha
	 * @param user only matters about User_id
	 * @return User object
	 */
	@PostMapping(value="/remseefirst")
	public void RemSeeFirst(@RequestBody User user){
		user = userServ.findByUserId(user.getUserId());
		
		seeFirstServ.delete(seeFirstServ.FindById(user.getUserId()));
		
	}
	
	
	

}
