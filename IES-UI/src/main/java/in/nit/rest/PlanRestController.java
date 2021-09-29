package in.nit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nit.model.Plan;
import in.nit.service.IPlanService;

@RestController
public class PlanRestController {

	@Autowired
	private IPlanService service;
	
	@RequestMapping(path="/plans", method=RequestMethod.GET)
	public List<Plan> getAllPlans(){
		return service.getAllPlan();
}
}