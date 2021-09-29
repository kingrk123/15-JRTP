package in.nit.repository;

import java.util.List;

import in.nit.model.CoTriggersEntity;

public interface CoTriggersDao {

	public List<CoTriggersEntity> findPendTriggersWithOraHash(String status, Integer tb, Integer ci);
	
	public List<CoTriggersEntity> findPendTriggers(String status);
}
