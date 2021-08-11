package org.controller;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import rev.UserMicroserviceApplication;
import rev.controller.LoginController;
import rev.model.JwtRequest;
import rev.model.JwtResponse;
import rev.model.User;
import rev.service.UserService;

@SpringBootTest(
	    classes = UserMicroserviceApplication.class
	)
@ExtendWith(MockitoExtension.class)

class LoginControllerTest {

	LoginController myCont;
	
	@Mock
	UserService myServ;
	
	@Mock
	AuthenticationManager authenticationManager;


	@BeforeEach
	void setUp() throws Exception {
		myCont = new LoginController(myServ);
	}
	
	@Test
	void newUser() {
		//ARRANGE
		User initialUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		User expectedUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		when(myServ.save(initialUser)).thenReturn(initialUser);
		//ACT
		User actualUser = myCont.newUser(initialUser);
		//ASSERT
		verify(myServ, times(1)).save(initialUser);
		assertEquals(expectedUser, actualUser);
		
	}
	/**
	 *@author zacha
	 *Tests a correct login
	 */
	@Test
	void login() {
		//ARRANGE
		User initialUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		User expectedUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");

		when(myServ.findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword())).thenReturn(initialUser);
		//ACT
		User actualUser = myCont.login(initialUser);
		//ASSERT
		verify(myServ, times(1)).findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword());
		assertEquals(expectedUser, actualUser);
	}
	
	/**
	 * @author zacha
	 * Tests a wrong username and password
	 */
	@Test
	void loginFail() {
		//ARRANGE
		User initialUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
	
		when(myServ.findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword())).thenReturn(null);
		//ACT
		User actualUser = myCont.login(initialUser);
		//ASSERT
		verify(myServ, times(1)).findByUsernameAndPassword(initialUser.getUsername(), initialUser.getPassword());
		assertEquals(-1, actualUser.getUserId());
	}
	
	/**
	 * @author Miguel Leon
	 * Junit test for the resetPW method
	 */
	@Test
	void resetPW() {
		
		User updateUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		User expectedUser = new User(0,"GameGrumps", "Grump", "Not", "SoGrump", "WERE", "pic", "bkg-pic", "GG@grump.com");
		
		when(myServ.findByUserId(updateUser.getUserId())).thenReturn(updateUser);
		when(myServ.save(updateUser)).thenReturn(updateUser);
		
		User actualUser = myCont.resetPW(updateUser);
		
		verify(myServ, times(1)).findByUserId(updateUser.getUserId());
		verify(myServ, times(1)).save(updateUser);

		assertEquals(actualUser, expectedUser);
	}
	
//	@Test
//	void authenticate() {
//		
//		JwtRequest jwtRequest = new JwtRequest("username","password");
//		
//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//						jwtRequest.getUsername(),
//						jwtRequest.getPassword()
//				));
//		
//		when(jwtRequest.getUsername()).thenReturn(jwtRequest);
//	} 

}
