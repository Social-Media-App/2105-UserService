package rev.controller;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
=======
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
>>>>>>> 15e7e91b11090bfb4d046836710a3f3bdabc1e95
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> ca49075771cebad398e4c88c4414a25a85e1e727
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import rev.filters.JwtRequestFilter;
import rev.model.AuthenticationResponse;
=======
import rev.model.JwtRequest;
import rev.model.JwtResponse;
<<<<<<< HEAD
import rev.model.SeeFirst;
>>>>>>> 15e7e91b11090bfb4d046836710a3f3bdabc1e95
=======
>>>>>>> ca49075771cebad398e4c88c4414a25a85e1e727
import rev.model.User;
import rev.service.MyUserDetailsService;
import rev.service.UserService;
<<<<<<< HEAD
import rev.util.JwtUtil;
=======
import rev.utilities.JwtUtility;
<<<<<<< HEAD
>>>>>>> 15e7e91b11090bfb4d046836710a3f3bdabc1e95
=======
import rev.utilities.RandomToken;
import rev.utilities.SendingMail;
import rev.utilities.ToEncrypted;
>>>>>>> ca49075771cebad398e4c88c4414a25a85e1e727

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true")
@RequestMapping("/login-service")
public class LoginController {
	
	private UserService userServ;

	private JwtUtility jwtUtility;

	private AuthenticationManager authenticationManager;

	private MyUserDetailsService userDetailsService;
	
	
	
<<<<<<< HEAD
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
=======
>>>>>>> 15e7e91b11090bfb4d046836710a3f3bdabc1e95
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
		byte[] help = ToEncrypted.createSalt();
		System.out.println(help);
		System.out.println(ToEncrypted.generateHash(jwtRequest.getPassword(), help));
		
		User tempUser = userServ.findByUsername(jwtRequest.getUsername());
		try {
			jwtRequest.setPassword(ToEncrypted.generateHash(jwtRequest.getPassword(), tempUser.getSalt()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
<<<<<<< HEAD
	public @ResponseBody String login(@RequestBody User user) throws Exception{
		System.out.println("Begining login");

//		User loguser = userServ.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//		if (loguser == null) {
//			loguser = new User();
//			loguser.setUserId(-1);
//		}
		
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		System.out.println("After before try catch block");
		final User userDetails = userServ.findByUsername(user.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return jwt;
		
		//return loguser;
=======
	public @ResponseBody User login(@RequestBody User user){
		User tempUser = userServ.findByUsername(user.getUsername());
		try {
			user.setPassword(ToEncrypted.generateHash(user.getPassword(), tempUser.getSalt()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User loguser = userServ.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		if (loguser == null) {
			loguser = new User();
			loguser.setUserId(-1);
		}	
		return loguser;
>>>>>>> ca49075771cebad398e4c88c4414a25a85e1e727

	}
	/**
	 * @author zacha
	 * Creates a new user in the database and returns said user
	 * @param User object
	 * @return User object
	 * @throws NoSuchAlgorithmException 
	 */
	@PostMapping(value="/signup")
	public @ResponseBody User newUser(@RequestBody User user){
		System.out.println(user);
		if (userServ.existsByUserName(user.getUsername()) && userServ.existsByEmail(user.getEmail()))
			return new User(-1);
		user.setSalt(ToEncrypted.createSalt());
		try {
			user.setPassword(ToEncrypted.generateHash(user.getPassword(), user.getSalt()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(updateuser.getResetToken().equals(user.getResetToken()))
		{
			try {
				updateuser.setPassword(ToEncrypted.generateHash(user.getPassword(), updateuser.getSalt()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			updateuser.setResetToken(RandomToken.randomToken());
			return userServ.save(updateuser);
		}
		return new User(-1);
	}
	
	/**
	 * @author Matthew
	 * 
	 * Gets a uesername request body from the front end.
	 * 
	 * @param username
	 * @return If username exists, return 1; else return -1
	 */
	@PostMapping(value="/send-email")
	public Integer sendEmail(@RequestBody User username) {
		User userWhoForgotPass = userServ.findByUsername(username.getUsername());
		if(userWhoForgotPass == null) return -1; //Username doesnt exist
		String token = RandomToken.randomToken();
		userWhoForgotPass.setResetToken(token);
		userServ.save(userWhoForgotPass);
		try {
			SendingMail.sendMail(userWhoForgotPass.getEmail(), userWhoForgotPass.getUsername(),token);
		} catch (MessagingException e) {
			System.out.println("Error occured");
			e.printStackTrace();
		}
		User forgotPassUser1 = userServ.findByUsername(username.getUsername());
		System.out.println(forgotPassUser1);
		return 1;	
	}
	
	/**
	 * @author Matthew
	 * 
	 * Gets a uesername request body from the front end.
	 * 
	 * @param username
	 * @return If username exists, return 1; else return -1
	 */
	@PutMapping(value="/reset-pass")
	public Integer resetPass(@RequestBody User userWhoForgotPass) {
		System.out.println(userWhoForgotPass.toString());
		return userServ.resetPass(userWhoForgotPass);
	}
	
}

