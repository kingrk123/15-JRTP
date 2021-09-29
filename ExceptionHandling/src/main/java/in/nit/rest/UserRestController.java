package in.nit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.nit.exception.NoDataFound;
import in.nit.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService service;
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<String> getName(@PathVariable Integer userId) throws NoDataFound {
		String userName =service.getUserName(userId);
		return new ResponseEntity<>(userName,HttpStatus.OK);
	}
}
