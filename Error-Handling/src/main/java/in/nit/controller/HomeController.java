package in.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping
	public String welcome(Model model) {
		model.addAttribute("msg", "Welcome To CodeAcademy");
		String name=null;
		name.length();
		return "welcome";
	}
	
	//Controller based exception handling
	@ExceptionHandler(value = NullPointerException.class)
	public String handleNullPinterException(Model model) {
		model.addAttribute("errMsg", "Some Problem Occured, Please Try after Sometime...!");
		return "error";
	}
}
