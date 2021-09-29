package in.nit.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nit.model.Account;
import in.nit.service.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(path="/accounts", method=RequestMethod.GET)
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}
    
}