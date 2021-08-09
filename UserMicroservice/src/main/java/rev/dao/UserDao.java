package rev.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rev.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	//////////////DELETE\\\\\\\\\\\\\\
	/**
	 * @author zacha
	 * Deletes a user from database
	 * @param User to delete
	 * @return Nothing
	 */
	public void delete(User user);

	
	
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
	 * Gets User by its Id
	 * @param id
	 * @return User Object
	 */
	public User findByUserId(int id);
	
	/**
	 * @author zacha
	 * Gets user by username
	 * @param username string
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * @author zacha
	 * @param username string
	 * @param password string
	 * @return User object
	 */
	public User findByUsernameAndPassword(String username, String password);
	
	

}
