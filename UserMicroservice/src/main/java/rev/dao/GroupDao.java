package rev.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rev.model.Group;
/**
 * @author Muhammad_Ibrahim
 * 
 * This is the repository for the group object. 
 * We extend the JpaRepository which abstracts all of our dao implementation away.
 */
@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {

	//  CREATE
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method creates/saves a new group. JpaRepo uses the save annotation so that is what 
	 * we'll use in the service layer.
	 * 
	 * @param newGroup the new group parameter
	 * 
	 * @return the saved new group
	 */
	public Group createGroup(Group newGroup);
	
	//  READ
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method finds all groups in the database, adds them to a list and returns that list. 
	 * 
	 * 
	 * @param none
	 * 
	 * @return list of all groups
	 */
	public List<Group> findAllGroups();
	
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method finds a group in the database by it's groupId and returns that group. 
	 * 
	 * 
	 * @param id an int id
	 * 
	 * @return a group
	 */
	public Group findByGroupId(int id);
	
	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method finds a group in the database by it's groupName and returns that group. 
	 * 
	 * 
	 * @param groupName the given groupName
	 * 
	 * @return a group
	 */
	public Group findByGroupName(String groupName);
	
	//  UPDATE
	
	
	//  DELETE
}
