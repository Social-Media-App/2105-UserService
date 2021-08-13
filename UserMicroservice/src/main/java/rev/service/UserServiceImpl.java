package rev.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rev.dao.UserDao;
import rev.model.User;
import rev.utilities.RandomToken;
import rev.utilities.ToEncrypted;

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
		return userDao.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {

		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> findAllById(Set<Integer> ids) {
		return userDao.findAllById(ids);
	}

	@Override
	public Boolean existsByUserName(String userName) {
		return userDao.existsByUsername(userName);
		
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userDao.existsByUsername(email);
		
	}

	@Override
	public int resetPass(User userWhoForgotPass) {
		User matchingUser = userDao.findByUsername(userWhoForgotPass.getUsername());
		if(matchingUser == null)return -1;
		if(!userWhoForgotPass.getResetToken().equals(matchingUser.getResetToken()))return -2;
		try {
			String hashedPass = ToEncrypted.generateHash(userWhoForgotPass.getPassword(), matchingUser.getSalt());
			if(hashedPass.equals(matchingUser.getPassword())) return -3;
			matchingUser.setPassword(hashedPass);
			String resetToken = RandomToken.randomToken();
			matchingUser.setResetToken(resetToken);
			userDao.save(matchingUser);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 1;
	}
}
