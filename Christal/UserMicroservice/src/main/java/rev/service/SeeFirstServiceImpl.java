package rev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rev.dao.SeeFirstDao;
import rev.model.SeeFirst;

@Service
public class SeeFirstServiceImpl implements SeeFirstService {
	
	SeeFirstDao seeFirstDao;

	@Autowired
	public SeeFirstServiceImpl(SeeFirstDao seeFirstDao) {
		super();
		this.seeFirstDao = seeFirstDao;
	}

	@Override
	public SeeFirst save(SeeFirst seeFirst) {
		// TODO Auto-generated method stub
		return seeFirstDao.save(seeFirst);
	}

	@Override
	public void delete(SeeFirst seeFirst) {
		seeFirstDao.delete(seeFirst);
		
	}

	@Override
	public SeeFirst FindById(int id) {
		// TODO Auto-generated method stub
		return seeFirstDao.findBySeeFirstId(id);
	}

}
