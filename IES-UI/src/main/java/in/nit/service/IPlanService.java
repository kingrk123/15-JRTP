package in.nit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import in.nit.model.Plan;

public interface IPlanService {

	public boolean savePlan(Plan Plan);
	
	public List<Plan> getAllPlan();
	
	public Page<Plan> getAllPlanNew(Integer pageNo,Integer pageSize);
	
	public Plan getPlanById(Integer PlanId);
	
	public boolean deleteById(Integer PlanId);
	
	public Boolean isPlanExists(Plan Plan);
	
	Optional<Plan> getPlanById(String id);
	void deletePlan(String id);
	void updatePlan(Plan plan);
}
