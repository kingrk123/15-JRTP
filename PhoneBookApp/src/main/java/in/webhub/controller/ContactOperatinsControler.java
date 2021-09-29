package in.webhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.webhub.model.Contact;
import in.webhub.service.IcontactService;

@Controller
public class ContactOperatinsControler {

	@Autowired
	private IcontactService service;
	
	@GetMapping("/editcontact")
	public ModelAndView editContact(@RequestParam ("cid") Integer contactId) {
		ModelAndView mav = new ModelAndView();
		Contact cObj= service.getContactById(contactId);
		mav.addObject("contact", cObj);
		
		mav.setViewName("Contact");
		return mav;
	}

	@GetMapping("/deleteContact")
	public String handleDeleteRecord(@RequestParam ("cid") Integer contactId ) {
		service.deleteById(contactId);
		return "redirect:viewContacts";
	}
	
}
