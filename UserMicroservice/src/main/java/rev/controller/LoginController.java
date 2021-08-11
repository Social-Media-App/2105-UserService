package rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.JwtRequest;
import rev.model.JwtResponse;
import rev.model.SeeFirst;
import rev.model.User;
import rev.service.MyUserDetailsService;
import rev.service.SeeFirstService;
import rev.service.UserService;
import rev.utilities.JwtUtility;

@RestController
@RequestMapping("/login-service")
public class LoginController {
	
	private UserService userServ;

	private JwtUtility jwtUtility;

	private AuthenticationManager authenticationManager;

	private MyUserDetailsService userDetailsService;
	
	
	
	public LoginController(UserService userServ) {
		super();
		this.userServ = userServ;
	}

	@Autowired
	public LoginController(UserService userServ, JwtUtility jwtUtility, AuthenticationManager authenticationManager,
			MyUserDetailsService userDetailsService) {
		super();
		this.userServ = userServ;
		this.jwtUtility = jwtUtility;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * @author Miguel and Eric
	 * 
	 * This will be a post endpoint to send the username and password to the authentication server
	 * 
	 * @param JwtRequest object
	 * @return JwtResponse Object
	 */
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						jwtRequest.getUsername(),
						jwtRequest.getPassword()
				)
		);
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid credentials", e);
		}
		
		final UserDetails userDetails = 
				userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		final String token =
				jwtUtility.generateToken(userDetails);
		
		return new JwtResponse(token);
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
		System.out.println(user);
		if (userServ.existsByUserName(user.getUsername()) && userServ.existsByEmail(user.getEmail()))
			return new User(-1);
		return userServ.save(user);
	}
	/**
	 * @author zacha
	 * @param user
	 * @return User object, with updated password
	 */
	@PostMapping(value="/resetpw")
	public @ResponseBody User resetPW(@RequestBody User user){
		System.out.println("reset pw");
		User updateuser = userServ.findByUserId(user.getUserId());
		updateuser.setPassword(user.getPassword());
		return userServ.save(updateuser);
	}
	
	
	

}

