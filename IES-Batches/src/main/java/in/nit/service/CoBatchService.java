package in.nit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import in.nit.model.CoPdfEntity;
import in.nit.model.CoTriggersEntity;
import in.nit.repository.CoTriggersDao;

public class CoBatchService {

	@Autowired
	private CoTriggersDao coTriggersDao;
	
	public List<CoTriggersEntity> findPendingTriggers() {
		List<CoTriggersEntity> entities =coTriggersDao.findPendTriggers("P");
		List<CoTriggersEntity> models =new Array
		return null;
	}

	public void updatePendingTrigger(CoTriggersEntity trigger) {
		// TODO Auto-generated method stub
		
	}

	public void savePdf(CoPdfEntity pdfModel) {
		// TODO Auto-generated method stub
		
	}

	

}
