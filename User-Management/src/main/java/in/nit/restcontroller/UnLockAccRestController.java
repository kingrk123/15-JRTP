package in.nit.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nit.bindings.UnlockAccForm;
import in.nit.exception.UserAppException;
import in.nit.service.UserService;

@RestController
public class UnLockAccRestController {

	@Autowired
	private UserService service;
	
	@PostMapping("/unlock")
	public String unlockacc(@RequestBody UnlockAccForm unlockAccForm) throws UserAppException {
		boolean status = service.unlockAccount(unlockAccForm);
		
		if (status) {
			return "Account Unlock Successfully";
		}else {
			return "Incorrect Temporary Password";
		}
	}
}
