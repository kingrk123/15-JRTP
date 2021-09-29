package in.nit.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DateController {

	@GetMapping("/date")
	public String displayDate(Model model) {
		model.addAttribute("dateMsg", "Today Date : "+new Date());
		String s = null;
		s.length();
		return "dateDisplay";
	}
}
