package com.revature.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.demo.models.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public void saveUser(User user);
	public void deleteUser(User user);
	public User findByUserId(Integer id);
	

}
