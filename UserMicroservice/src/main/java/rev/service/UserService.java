package rev.service;

import java.util.List;
import java.util.Set;

import rev.model.User;

public interface UserService {
	
	///////////////READ\\\\\\\\\\\\\\\
	/**
	 * @author zacha
	 * 
	 * Gets all Users from database
	 * @param none
	 * @return Array list of User Objects
	 * 
	 */
	public List<User> findAll();
	
	/**
	 * @author zacha
	 * 
	 * Gets all User in a set of id
	 * @param none
	 * @return Array list of User Objects
	 * 
	 */
	public List<User> findAllById(Set<Integer> ids);
	/**
	 * @author zacha
	 * 
	 * Gets a User from database based on its Id
	 * @param Id integer based on id of user you want to find 
	 * @return Array list of User Objects
	 * 
	 */
	public User findByUserId(int id);
	/**
	 * @author zacha
	 * 
	 * Gets a user based on username
	 * @param username string
	 * @return Single User object
	 * 
	 */
	public User findByUsername(String username);
	/**
	 * @author zacha
	 * 
	 * Gets a user based on Username and Password
	 * @param Username and Password Strings
	 * @return User Object
	 * 
	 */
	public User findByUsernameAndPassword(String username, String password);
	
	//////////////CREATE\\\\\\\\\\\\\\
	/**
	 * @author zacha
	 * 
	 * add user to database
	 * @param User
	 * @return User object that was created
	 * 
	 */
	public User save(User user);
	
	//////////////DELETE\\\\\\\\\\\\\\
	/**
	 * @author zacha
	 * 
	 * Remove user from database
	 * @param User object
	 * @return none
	 * 
	 */
	public void delete(User user);	

}
