package rev.controller;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.Group;
import rev.model.SeeFirst;
import rev.model.User;
import rev.service.GroupService;
import rev.service.SeeFirstService;
import rev.service.UserService;
import rev.utilities.JwtUtility;
import rev.utilities.RandomToken;
import rev.utilities.SendingMail;

@RestController
@RequestMapping("/user-service")
public class UserController {
	
	private UserService userServ;
	private SeeFirstService seeFirstServ;
	private JwtUtility jwtUtility;
	private GroupService groupServ;
	
	
	
	
	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
	}


	public UserController(UserService userServ, SeeFirstService seeFirstServ, JwtUtility jwtUtility) {
		super();
		this.userServ = userServ;
		this.seeFirstServ = seeFirstServ;
		this.jwtUtility = jwtUtility;
	}
	
	
	
	@Autowired
	public UserController(UserService userServ, SeeFirstService seeFirstServ, JwtUtility jwtUtility,
			GroupService groupServ) {
		super();
		this.userServ = userServ;
		this.seeFirstServ = seeFirstServ;
		this.jwtUtility = jwtUtility;
		this.groupServ = groupServ;
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
	public @ResponseBody User updateUser(@RequestHeader("Authorization") String header, @RequestBody User user){
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
	
	/**
	 * Add a new See First using user from JWT and User id sent in
	 * @author zacha
	 * @param user only matters about User_id
	 * @return User object
	 */
	
	@PostMapping(value="/addseefirst")
	public @ResponseBody User newSeeFirst(@RequestHeader("Authorization") String header, @RequestBody User user){
		header = header.substring(7);
		header = jwtUtility.getUsernameFromToken(header);
		user = userServ.findByUserId(user.getUserId());
		seeFirstServ.save(new SeeFirst(userServ.findByUsername(header), user));

		return userServ.findByUsername(header);
		
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
	
	/**
	 * @author zacha
	 * @param header JWT with username
	 * @param group	Group to be joining
	 * @return
	 */
	@GetMapping(value="/joingroup")
	public @ResponseBody User getByGroupName(@RequestHeader("Authorization") String header, @RequestBody Group group){
		System.out.println("Joining A group");
		header = header.substring(7);
		header = jwtUtility.getUsernameFromToken(header);
		User user = userServ.findByUsername(header);
		List<Group> groups = user.getGroupList();
		groups.add(groupServ.findByGroupName(group.getGroupName()));
		user.setGroupList(groups);
		return userServ.save(user);
	}
	
	
	
	

}
