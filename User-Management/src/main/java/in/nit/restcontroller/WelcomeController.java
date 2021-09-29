package in.nit.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class WelcomeController {

	@RequestMapping(value ="/",produces = "application/json")
	public String getURLValue(HttpServletRequest request){
	    System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
	    return "Hello";
}
}