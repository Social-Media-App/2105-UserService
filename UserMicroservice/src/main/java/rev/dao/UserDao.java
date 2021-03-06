package rev.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rev.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	
	//////////////////UPDATE\\\\\\\\\\\\\\\\\\\\

	
	
	//////////////////DELETE\\\\\\\\\\\\\\\\\\\\

	public void delete(User user);
	
	/////////////EXISTS\\\\\\\\\
	
	/**
	 * @author zacha
	 * @param string username
	 * @return returns boolean based on if exists
	 */
	public Boolean existsByUsername(String username);
	
	/**
	 * @author zacha
	 * @param string email
	 * @return returns boolean based on if exists
	 */
	public Boolean existsByEmail(String email);
	
	

	
	
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
