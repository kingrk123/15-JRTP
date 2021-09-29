package in.nit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.model.Plan;
import in.nit.service.IPlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private IPlanService service;
	
	@GetMapping("/create")
	public String createPlan(Model model) {
		model.addAttribute("plan", new Plan());
		return "createPlan";
	}
	
	@GetMapping("/view")
	public String ViewPlan(Model model) {
		//model.addAttribute("plann", new Plan());
		return "viewPlan";
	}
	
	@PostMapping("/savePlan")
	public String handleSaveBtnClick(@Valid Plan plan, BindingResult result, Model model,RedirectAttributes redirectAttributes) {

		Boolean PlanExists = service.isPlanExists(plan);
		if (PlanExists) {
			redirectAttributes.addFlashAttribute("Msg", "DUPLICATE CONTACT FOUND");
		} else {
			System.out.println(plan);
			Boolean isSaved = service.savePlan(plan);
			if (isSaved) {
				redirectAttributes.addFlashAttribute("Msg", "PLAN SAVED SUCCESSFULLY");

			} else {
				redirectAttributes.addFlashAttribute("Msg", "FAILED TO SAVE PLAN");
			}
		}
		return "redirect:create";
	}


	@RequestMapping(path="/edit/{id}", method=RequestMethod.GET)
	public String goHm(@PathVariable String id, Model model){
		model.addAttribute("usr",id);


		Optional<Plan> opt=service.getPlanById(id);
		if(opt.isPresent()) {
			Plan plan =opt.get();
			model.addAttribute("plan", plan); }

		return "editPlan";
	}
	
	
	@PostMapping("/update")
	public String update(
			@ModelAttribute Plan plan,
			Model model) {
		service.updatePlan(plan);
		
		String msg="Admin '"+plan.getPLAN_ID()+"' Updated!";
		
		model.addAttribute("Msg", msg);
		
		return "redirect:view";
	}

	
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable String id,Model model) 
	{
		service.deletePlan(id);
		return "viewplan";
		
	}

}
