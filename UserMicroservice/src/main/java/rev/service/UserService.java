package rev.service;

import java.util.List;

import rev.model.User;

public interface UserService {
	
	public List<User> findAll();
	public User save(User user);
	public void delete(User user);
	public User findByUserId(int id);
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);

}
