package com.revature.demo.service;

import java.util.List;

import com.revature.demo.models.User;

public interface UserService {

	public List<User> findAll();
	public User save(User user);
	public void delete(User user);
	public User findByUserId(int id);
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);

}
