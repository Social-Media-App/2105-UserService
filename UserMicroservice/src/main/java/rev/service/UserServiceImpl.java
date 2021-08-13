package rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rev.dao.UserDao;
import rev.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
		
	}
	
	

	@Override
	public User findByUserId(int id) {
		// TODO Auto-generated method stub
		return userDao.findByUserId(id);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		System.out.println("im here");
		return userDao.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {

		return userDao.findByUsernameAndPassword(username, password);
	}
}
