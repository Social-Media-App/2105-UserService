package rev.service;

import rev.model.SeeFirst;
import rev.model.User;

public interface SeeFirstService {
	
	public SeeFirst save(SeeFirst seeFirst);
	public void delete(SeeFirst seeFirst);
	public SeeFirst FindById(int id);

}
