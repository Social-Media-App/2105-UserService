package rev.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rev.model.User;
import rev.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userServ;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userServ.findByUsername(username);
	}


}
