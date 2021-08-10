package rev.service;

import java.util.List;

import rev.model.Group;

/**
 * @author Muhammad_Ibrahim
 * 
 * This is the group service interface  for the group object. 
 * This is the interface to be implemented in the service implementation layer.
 */
public interface GroupService {

	//	CREATE
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method needs to be implemented in group service implementation.
	 */
	public Group save(Group newGroup);
	
	//	READ
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method needs to be implemented in group service implementation.
	 */
	public List<Group> findAllGroups();
	
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method needs to be implemented in group service implementation.
	 */
	public Group findByGroupId(int id);
	
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method needs to be implemented in group service implementation.
	 */
	public Group findByGroupName(String groupName);
	
	//	UPDATE 
	
	//	DELETE
}
