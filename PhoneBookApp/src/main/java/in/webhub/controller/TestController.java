package in.webhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/welcome")
	public String showPage(Model model) {
		model.addAttribute("msg", "Welcome To WebHub..!");
		return "index";
	}
}
