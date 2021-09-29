package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.nit.model.Plan;
import in.nit.repository.PlanRepo;
import in.nit.service.IPlanService;

@Service
public class PlanService implements IPlanService {

	@Autowired
	private PlanRepo repo;

	@Override
	public boolean savePlan(Plan Plan) {
		Plan.setActiveSw("Y");
		Plan saveEntity = repo.save(Plan);
		if (saveEntity !=null ) {
			return true;
		}
		return false;
	}

	@Override
	public List<Plan> getAllPlan() {
		Plan PlanFilter = new Plan();
		PlanFilter.setActiveSw("Y");
		Example<Plan> example = Example.of(PlanFilter);
		List<Plan> Plans = repo.findAll(example);
		return Plans;
	}

	@Override
	public Page<Plan> getAllPlanNew(Integer pageNo, Integer pageSize) {
		Plan PlanFilter = new Plan();
		PlanFilter.setActiveSw("Y");
		Example<Plan> example = Example.of(PlanFilter);
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

		Page<Plan> findAll= repo.findAll(example,pageRequest);

		return findAll;
	}

	@Override
	public Plan getPlanById(Integer PlanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Integer PlanId) {
		Optional<Plan> findById= repo.findById(PlanId);
		if (findById.isPresent()) {
			Plan Plan = findById.get();
			Plan.setActiveSw("N");
			repo.save(Plan);	
			return true;
		}
		return false;
	}

	@Override
	public Boolean isPlanExists(Plan Plan) {
		Example<Plan> example = Example.of(Plan);
		return repo.exists(example);
	}

	@Override
	public Optional<Plan> getPlanById(String id) {
		Optional<Plan> opt = repo.findById(id);
		return opt;
	}

	@Override
	public void deletePlan(String id) {
		Optional<Plan> findById= repo.findById(id);
		if (findById.isPresent()) {
			Plan Plan = findById.get();
			Plan.setActiveSw("N");
			repo.save(Plan);	
		}
	}

	@Override
	public void updatePlan(Plan acc) {
		acc.setActiveSw("Y");
		repo.save(acc);
		
	}

}
