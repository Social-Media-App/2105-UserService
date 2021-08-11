package org.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import rev.UserMicroserviceApplication;
import rev.controller.UserController;
import rev.model.SeeFirst;
import rev.model.User;
import rev.service.SeeFirstService;
import rev.service.UserService;

@SpringBootTest(
	    classes = UserMicroserviceApplication.class
	)
@ExtendWith(MockitoExtension.class)

class UserControllerTest {
	
	UserController myCont;
	
	@Mock
	UserService myServ;
	
	@Mock
	SeeFirstService mySeeFirstServ;


	@BeforeEach
	void setUp() throws Exception {
		myCont = new UserController(myServ);
	}


	@Test
	void updateUser() {
		//ARRANGE
		User initialUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		User expectedUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		when(myServ.save(initialUser)).thenReturn(initialUser);
		System.out.println(initialUser);
		//ACT
		User actualUser = myCont.updateUser(initialUser);
		System.out.println(expectedUser);
		//ASSERT
		verify(myServ, times(1)).save(initialUser);
		assertEquals(expectedUser, actualUser);
		
	}
	
	@Test
	void getUsers() {
		//ARRANGE
		User initialUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		User expectedUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		List<User> initialUserList = new ArrayList<>();
		initialUserList.add(initialUser);
		when(myServ.findAll()).thenReturn(initialUserList);
		//ACT
		List<User> actualUser = myCont.getAllUsers();
		//ASSERT
		verify(myServ, times(1)).findAll();
		assertEquals(expectedUser, actualUser.get(0));
		
	}
	
	@Test 
	void newSeeFirst() {
		
		User expectedUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		
		when(myServ.findByUserId((expectedUser.getUserId()))).thenReturn(expectedUser);
		when(mySeeFirstServ.save(new SeeFirst(myServ.findByUserId(1), expectedUser))).thenReturn(expectedUser);
		
		User actualUser = myCont.newSeeFirst(expectedUser);
		
		verify(myServ, times(1)).findByUserId(expectedUser.getUserId());	
		verify(mySeeFirstServ, times(1)).save(new SeeFirst(myServ.findByUserId(1), expectedUser));
		
		assertEquals(expectedUser, actualUser);
	}




}
