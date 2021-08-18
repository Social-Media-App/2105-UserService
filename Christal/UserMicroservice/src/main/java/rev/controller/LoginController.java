package rev.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
import rev.utilities.RandomToken;
import rev.utilities.SendingMail;
import rev.utilities.ToEncrypted;

@RestController
@RequestMapping("/login-service")
@CrossOrigin("*")
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
        System.out.println("reset pw"+user);
        User updateuser = userServ.findByUsername(user.getUsername());
        if(updateuser == null) {
        	return new User(-1);
        }
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
	
	
	@GetMapping(value="/getall")
	public List<User> getAllUsersHA(){
		return userServ.findAll();
	}
	
	
	
	

}

