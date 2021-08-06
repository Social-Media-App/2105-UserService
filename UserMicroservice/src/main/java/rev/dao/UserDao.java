package rev.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rev.model.User;


public interface UserDao extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public void delete(User user);
	public User findByUserId(int id);
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	

}
