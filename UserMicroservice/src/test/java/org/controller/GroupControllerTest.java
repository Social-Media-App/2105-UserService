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

import rev.UserMicroserviceApplication;
import rev.controller.GroupController;
import rev.model.Group;
import rev.service.GroupService;

@SpringBootTest(classes=UserMicroserviceApplication.class)
@ExtendWith(MockitoExtension.class)
class GroupControllerTest {

	GroupController myController;
	
	@Mock
	GroupService myServ;
	

	@BeforeEach
	void setUp() throws Exception {
		myController = new GroupController(myServ);
	}

	@Test
	void createGroup() {
//		fail("Not yet implemented");
		// ARRANGE
		Group initialGroup = new Group(0,"our group");
		Group expectedGroup = new Group(0,"our group");
		when(myServ.save(initialGroup)).thenReturn(initialGroup);
		System.out.println(initialGroup);
		
		// ACT
		Group actualGroup = myController.createGroup(initialGroup);
		System.out.println(expectedGroup);
		
		// ASSERT
		verify(myServ, times(1)).save(initialGroup);
		assertEquals(expectedGroup, actualGroup);
	}

}
