package in.nit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import in.nit.model.Account;

public interface IAccountService {

	public boolean saveAccount(Account Account);
	
	public List<Account> getAllAccount();
	
	public Page<Account> getAllAccountNew(Integer pageNo,Integer pageSize);
	
	public Account getAccountById(Integer AccountId);
	
	public boolean deleteById(Integer AccountId);
	
	public Boolean isAccountExists(Account Account);
	
	Optional<Account> getAccountById(String id);
	void deleteAccount(String id);
	void updateAccount(Account acc);
}
