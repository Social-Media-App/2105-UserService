package rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rev.model.Group;
import rev.service.GroupService;

/**
 * @author Muhammad_Ibrahim
 * 
 * This is the controller layer for the group object. 
 * It employs the REST api and creates endpoint with request mapping, and get, post methods.
 */
@RestController
@RequestMapping("/group-service")
public class GroupController {

	/** Creating group service instance	which access the dao as well. */
	private GroupService groupServ;

	/**
	 * @author Muhammad_Ibrahim
	 * @param groupServ the instantiation of group service
	 * 
	 * We use dependency injection with autowired on the constructor.
	 */
	@Autowired
	public GroupController(GroupService groupServ) {
		super();
		this.groupServ = groupServ;
	}
	/**
	 * creates end point for getting list of groups.
	 * 
	 * @return a list of groups
	 */
	@GetMapping(value="/get-all-groups")
	public List<Group> getAllGroups(){
		System.out.println("inside of getAllGroups");
		return groupServ.findAllGroups();
	}
	
	/**
	 * This creates a post mapping for creating a new group
	 * @param newGroup the new group sent as JSON
	 * @return a string that prints to backend confirming new group created
	 */
	@PostMapping(value="/create-new-group")
	public Group createGroup(@RequestBody Group newGroup) {
		System.out.println("in the create group post method");
		return groupServ.save(newGroup);
//		return "created new group";
	}
	
	/**
	 * This creates a get mapping end point 
	 * @param groupName 
	 * @return find by group name 
	 */
	@GetMapping(value="/get-by-group-name")
	public Group getByGroupName(@RequestBody String groupName){
		System.out.println("inside of get by group name");
		return groupServ.findByGroupName(groupName);
	}
}
