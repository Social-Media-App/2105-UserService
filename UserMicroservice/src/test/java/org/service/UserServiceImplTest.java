package org.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import rev.UserMicroserviceApplication;
import rev.dao.UserDao;
import rev.model.User;
import rev.service.UserService;
import rev.service.UserServiceImpl;

@SpringBootTest(classes = UserMicroserviceApplication.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	
	@Mock
	private UserDao userDao;
	
	private UserService userServ;


	@BeforeEach
	void setUp() throws Exception {
		userServ = new UserServiceImpl(userDao);
	}



	@Test
	void insertUser() {
//		fail("Not yet implemented");
		// ARRANGE
		User initialUser = new User(0, "It'sJake", "Jake", "From", "State-Farm", "pass", "pic", "bgnd-pic", "jake@statefarm.com");
		User expectedUser = new User(0, "It'sJake", "Jake", "From", "State-Farm", "pass", "pic", "bgnd-pic", "jake@statefarm.com");
		when(userDao.save(initialUser)).thenReturn(initialUser);
		
		// ACT
		User actualUser = userServ.save(initialUser);
		
		// ASSERT
		verify(userDao, times(1)).save(initialUser);
		assertEquals(expectedUser, actualUser);
	}
	
	@Test
	void getAllUsers() {
		// ARRANGE
		List<User> initialList = new ArrayList<>();
		initialList.add(new User("C-137", "Rick", "Sanchez", "pass","rickest@rick.com"));
		initialList.add(new User("C-592", "Rick-Slow", "Sanchez", "pass","slowRick@councilOfRicks.com"));
		initialList.add(new User("D-894", "Rick-Cocky", "Sanchez", "pass","cockyRick@councilOfRicks.com"));
		
		List<User> expectedList = new ArrayList<>();
		expectedList.addAll(initialList);
		
		when(userDao.findAll()).thenReturn(initialList);
		
		// ACT
		List<User> actualList = userServ.findAll();
		
		//ASSERT
		verify(userDao, times(1)).findAll();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	void findByUsername() {
		// ARRANGE
		User initialUser = new User("It'sJake", "Jake",  "State-Farm", "pass",  "jake@statefarm.com");
		User expectedUser = new User("It'sJake", "Jake",  "State-Farm", "pass", "jake@statefarm.com");
		when(userDao.findByUsername(initialUser.getUsername())).thenReturn(initialUser);
		
		// ACT
		User actualUser = userServ.findByUsername(initialUser.getUsername());
		
		// ASSERT
		verify(userDao, times(1)).findByUsername(initialUser.getUsername());
		assertEquals(expectedUser, actualUser);
		
	}

	@Test
	void findByUserId() {
		// ARRANGE
		User initialUser = new User("It'sJake", "Jake",  "State-Farm", "pass",  "jake@statefarm.com");
		User expectedUser = new User("It'sJake", "Jake",  "State-Farm", "pass", "jake@statefarm.com");
		when(userDao.findByUserId(initialUser.getUserId())).thenReturn(initialUser);
		
		// ACT
		User actualUser = userServ.findByUserId(initialUser.getUserId());
		
		// ASSERT
		verify(userDao, times(1)).findByUserId(initialUser.getUserId());
		assertEquals(expectedUser, actualUser);
		
	}
	
	@Test
	void findByUsernameAndPassword() {
		// ARRANGE
		User initialUser = new User("It'sJake", "Jake",  "State-Farm", "pass",  "jake@statefarm.com");
		User expectedUser = new User("It'sJake", "Jake",  "State-Farm", "pass", "jake@statefarm.com");
		when(userDao.findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword())).thenReturn(initialUser);
		
		// ACT
		User actualUser = userServ.findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword());
		
		// ASSERT
		verify(userDao, times(1)).findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword());
		assertEquals(expectedUser, actualUser);
		
	}
	
//	@Test
//	void deleteUser() {
////		fail("Not yet implemented");
//		// ARRANGE
//		User initialUser = new User(0, "It'sJake", "Jake", "From", "State-Farm", "pass", "pic", "bgnd-pic", "jake@statefarm.com");
//		User expectedUser = new User(0, "It'sJake", "Jake", "From", "State-Farm", "pass", "pic", "bgnd-pic", "jake@statefarm.com");
//		when(userDao.delete(initialUser)).thenReturn(initialUser);
//		
//		// ACT
//		User actualUser = userServ.delete(initialUser);
//		
//		// ASSERT
//		verify(userDao, times(1)).save(initialUser);
//		assertEquals(expectedUser, actualUser);
//	}
}
