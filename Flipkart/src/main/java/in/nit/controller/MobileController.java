package in.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.nit.binding.SearchForm;
import in.nit.model.Mobile;
import in.nit.service.MobileService;

@Controller
public class MobileController {

	@Autowired
	private MobileService service;
	
	@ModelAttribute
	public void loadFormData(Model model) {
		model.addAttribute("brand", service.getBrandNames());
		model.addAttribute("ram", service.getRam());
		model.addAttribute("rating", service.getRating());
	}
	
	@GetMapping("/")
	public String loadPage(Model model) {
		  SearchForm formObj = new SearchForm();
		  model.addAttribute("formObj", formObj);
		
		return "index";
	}
	
	@PostMapping("/search")
	public String searchMobiles(@ModelAttribute("formObj") SearchForm formObj, Model model) {
		System.out.println(formObj);
		
		List<Mobile> mobileList = service.filterProducts(formObj);
		System.out.println(mobileList);
		model.addAttribute("mobiles", mobileList);
		return "index";
	}
}
