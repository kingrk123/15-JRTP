package in.webhub.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import in.webhub.constants.AppConstants;
import in.webhub.model.Contact;
import in.webhub.service.IcontactService;

@Controller
public class ContactInfoController {

	@Autowired
	private IcontactService service;

	@GetMapping("/show")
	public String loadForm(Model model) {
		Contact cObj= new Contact();
		
		model.addAttribute(AppConstants.CONTACT, cObj);
		return AppConstants.CONTACT;
	}

	/*
	 * @PostMapping("/saveContact") public String btnHandle(Model
	 * model,BindingResult result,@Valid @RequestBody Contact contact) {
	 * 
	 * if (result.hasErrors()) { return AppConstants.CONTACT; }
	 * 
	 * Boolean contactExists = service.isContactExists(contact); if (contactExists)
	 * { model.addAttribute("errMsg", "Duplicate Contact Found"); } else { Boolean
	 * isSaved = service.saveContact(contact); if (isSaved) {
	 * model.addAttribute("succMsg", "Contact Saved Successfully"); } else {
	 * model.addAttribute("errMsg", "Failed To Save Contact"); } } return "Contact";
	 * }
	 */
	

	@PostMapping("/saveContact")
	public String handleSaveBtnClick(@Valid Contact contact, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return AppConstants.CONTACT;
		}

		Boolean contactExists = service.isContactExists(contact);
		if (contactExists) {
			model.addAttribute("errMsg", "Duplicate Contact Found");
		} else {
			Boolean isSaved = service.saveContact(contact);
			if (isSaved) {
				model.addAttribute("succMsg", "Contact Saved Successfully");
			} else {
				model.addAttribute("errMsg", "Failed To Save Contact");
			}
		}
		return AppConstants.CONTACT;
	}

	@GetMapping("/viewContacts")
	public ModelAndView getAllcontacts(HttpServletRequest req) {
		
		Integer pageSize=3;
		
		Integer pageNumber =1;
		
		String reqParam = req.getParameter("pno");
		if(reqParam!=null && !"".equals(reqParam)) {
			pageNumber =Integer.parseInt(reqParam);
		}
		
		ModelAndView mav = new ModelAndView();
		 Page<Contact> page = service.getAllContactNew(pageNumber-1, pageSize);
		 
		 int totalPage= page.getTotalPages();
		 
		 List<Contact> list = page.getContent();
		 
		 mav.addObject("lst", list);
		 mav.addObject("tp", totalPage);
		 mav.addObject("currPno", pageNumber);
		
		 mav.setViewName("contactsAll");
		
		return mav;
	}
	
}

