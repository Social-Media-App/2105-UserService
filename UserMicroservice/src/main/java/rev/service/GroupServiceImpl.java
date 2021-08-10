package rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import rev.dao.GroupDao;
import rev.model.Group;
/**
 * @author Muhammad_Ibrahim
 * 
 * This is the service layer for the group object. 
 * It implements the group Service.
 * It contains/should contain all business logic and never directly accesses the database.
 */
@Service
public class GroupServiceImpl implements GroupService {

	/** Creating group Dao instance	 */
	private GroupDao groupDao;
	
	/**
	 * @author Muhammad_Ibrahim
	 * @param groupDao the instantiation of group dao
	 * 
	 * We use dependency injection with autowired on the constructor.
	 */
	@Autowired
	public GroupServiceImpl(GroupDao groupDao) {
		super();
		this.groupDao = groupDao;
	}

	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method creates/saves a new group.
	 * 
	 * @param newGroup the new group parameter
	 * 
	 * @return the saved new group
	 */
	@Override
	public Group save(Group newGroup) {

		return groupDao.save(newGroup);
	}

	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method finds all groups in the database, adds them to a list and returns that list. 
	 * 
	 * @param none
	 * 
	 * @return list of all groups
	 */
	@Override
	public List<Group> findAllGroups() {
		// TODO Auto-generated method stub
		return groupDao.findAll();
	}

	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method finds a group in the database by it's groupId and returns that group. 
	 * 
	 * @param id an int id
	 * 
	 * @return a group
	 */
	@Override
	public Group findByGroupId(int id) {
		// TODO Auto-generated method stub
		return groupDao.findByGroupId(id);
	}

	/**
	 * @author Muhammad_Ibrahim
	 * 
	 * This method finds a group in the database by it's groupName and returns that group. 
	 * 
	 * @param groupName the given groupName
	 * 
	 * @return a group
	 */
	@Override
	public Group findByGroupName(String groupName) {
		// TODO Auto-generated method stub
		return groupDao.findByGroupName(groupName);
	}

}
