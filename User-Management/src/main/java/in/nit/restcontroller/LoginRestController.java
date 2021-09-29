package in.nit.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nit.bindings.LoginForm;
import in.nit.exception.UserAppException;
import in.nit.service.UserService;

@RestController
public class LoginRestController {

	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm)throws UserAppException {
		return service.loginCheck(loginForm);
	}
}
