package rev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rev.model.JwtRequest;
import rev.model.JwtResponse;
import rev.service.MyUserDetailsService;
import rev.utilities.JwtUtility;

@RestController
public class HomeController {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
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

}
