package rev.service;

import rev.model.SeeFirst;
import rev.model.User;

public interface SeeFirstService {
	////CREATE\\\\\
	public SeeFirst save(SeeFirst seeFirst);
	
	/////////DELETE\\\\\\\\
	public void delete(SeeFirst seeFirst);
	
	
	///////////READ\\\\\\\\\
	
	public SeeFirst FindById(int id);

}
