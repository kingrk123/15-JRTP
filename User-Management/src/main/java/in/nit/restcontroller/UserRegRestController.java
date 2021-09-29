 package in.nit.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nit.bindings.UserRegForm;
import in.nit.constants.AppConstants;
import in.nit.exception.UserAppException;
import in.nit.props.AppProperties;
import in.nit.service.UserService;

@RestController 
public class UserRegRestController {

	@Autowired
	private UserService service;
	
	@Autowired
	private AppProperties app;
	
	@GetMapping("/countries")
	public Map<Integer, String> getCountries(){
		return service.getCountries();
	} 
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer countryId){
		return service.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId){
		return service.getStates(stateId);
	}
	
	@GetMapping("/emailcheck/{email}")
	public String uniqueEmailcheck(@PathVariable String email) {
		return service.emailCheck(email);
	}
	
	@PostMapping("/savUser")
	public String savUser(@RequestBody UserRegForm userRegForm)throws UserAppException {
		boolean saveUser =service.saveUser(userRegForm);
		
		if (saveUser) {
			return app.getMessages().get(AppConstants.USER_REG_SUCCESS) ;
		} else {
			return app.getMessages().get(AppConstants.USER_REG_FAIL);
		}
	}
}
