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

import in.nit.model.Account;
import in.nit.service.IAccountService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAccountService service;

	@GetMapping
	public String showAlladmin() {
		return "admin";
	}

	@GetMapping("/create")
	public String createAdmin(Model model) {
		model.addAttribute("account", new Account());
		return "createAdmin";
	}

	@PostMapping("/saveAccount")
	public String handleSaveBtnClick(@Valid Account account, BindingResult result, Model model,RedirectAttributes redirectAttributes) {

		/*
		 * if (result.hasErrors()) { return "createAdmin"; }
		 */

		/* RedirectView redirectView = new RedirectView("/", true); */


		Boolean accountExists = service.isAccountExists(account);
		if (accountExists) {
			redirectAttributes.addFlashAttribute("Msg", "DUPLICATE CONTACT FOUND");
		} else {

			Boolean isSaved = service.saveAccount(account);
			if (isSaved) {
				redirectAttributes.addFlashAttribute("Msg", "ACCOUNT SAVED SUCCESSFULLY");

			} else {
				redirectAttributes.addFlashAttribute("Msg", "FAILED TO SAVE ACCOUNT");
			}
		}
		return "redirect:create";
	}


	@GetMapping("/view")
	public String ViewAdmin(Model model) {
		//List<Account> lst = service.getAllAccount();
		return "viewAdmin";
	}

	@RequestMapping(path="/edit/{id}", method=RequestMethod.GET)
	public String goHm(@PathVariable String id, Model model){
		model.addAttribute("usr",id);


		Optional<Account> opt=service.getAccountById(id);
		if(opt.isPresent()) {
			Account emp =opt.get();
			model.addAttribute("emp", emp); }

		return "editAdmin";
	}
	
	
	@PostMapping("/update")
	public String update(
			@ModelAttribute Account emp,
			Model model) {
		service.updateAccount(emp);
		
		String msg="Admin '"+emp.getAccId()+"' Updated!";
		
		model.addAttribute("Msg", msg);
		
		return "redirect:view";
	}

	
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable String id,Model model) 
	{
		service.deleteAccount(id);
		return "viewAdmin";
		
	}
}
