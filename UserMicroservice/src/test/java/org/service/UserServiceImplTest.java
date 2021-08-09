package org.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}
