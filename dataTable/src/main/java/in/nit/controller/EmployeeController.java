package in.nit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.nit.model.Employee;
import in.nit.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String goHome(){
		return "index";
	}
	@RequestMapping(path="/edit/{id}", method=RequestMethod.GET)
	public String goHm(@PathVariable Integer id, Model model){
		model.addAttribute("usr",id);
		
		Optional<Employee> opt=service.getEmployeeById(id);
		if(opt.isPresent()) {
			Employee emp =opt.get();
			model.addAttribute("emp", emp);
		}

		return "edit";
	}

	@PostMapping("/update")
	public String update(
			@ModelAttribute Employee emp,
			Model model) {
		service.updateShipmentType(emp);
		String msg="Employee '"+emp.getId()+"' Updated!";
		
		model.addAttribute("Msg", msg);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Long id,Model model) 
	{
		service.deleteShipmentType(id);
		return "redirect:/";
		
	}
	
}

