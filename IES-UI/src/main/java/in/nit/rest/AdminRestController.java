package in.nit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nit.model.Account;
import in.nit.service.impl.AccountService;

@RestController
public class AdminRestController {

	@Autowired
	private AccountService service;
	
	@RequestMapping(path="/accounts", method=RequestMethod.GET)
	public List<Account> getAllAccountss(){
		return service.getAllAccount();
}
}