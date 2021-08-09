package rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rev.filters.JwtRequestFilter;
import rev.model.AuthenticationResponse;
import rev.model.User;
import rev.service.UserService;
import rev.util.JwtUtil;

@RestController
@RequestMapping("/login-service")
public class LoginController {
	
	private UserService userServ;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	public LoginController(UserService userServ) {
		super();
		this.userServ = userServ;
	}
	
	@PostMapping(value="/login")
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

	}
	
}

