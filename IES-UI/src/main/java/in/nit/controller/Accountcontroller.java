package in.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Accountcontroller {

	@GetMapping("/")
	public String showHome() {
		return "index";
	}
	
	
}
