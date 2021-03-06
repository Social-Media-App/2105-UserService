package rev.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rev.model.User;



@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userServ;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return new User("admin", "password", new ArrayList<>());
//		User user = userServ.findByUsername(username);
//		return (UserDetails) new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//	}
	
	/**
	 * @author Miguel and Eric
	 * 
	 * This will take in the username credential to validate
	 * 
	 * @param String object
	 * @return org.springframework.security.core.userdetails.User Object - !note: User object from springframework security and not our model.User
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userServ.findByUsername(username);
	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
