package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.nit.model.Account;
import in.nit.repository.AccountRepo;
import in.nit.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepo repo;

	@Override
	public boolean saveAccount(Account account) {
		account.setActiveSw("Y");
		Account saveEntity = repo.save(account);
		if (saveEntity !=null && saveEntity.getAccId()!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Account> getAllAccount() {
		Account accountFilter = new Account();
		accountFilter.setActiveSw("Y");
		Example<Account> example = Example.of(accountFilter);
		List<Account> Accounts = repo.findAll(example);
		return Accounts;
	}

	@Override
	public Page<Account> getAllAccountNew(Integer pageNo, Integer pageSize) {
		Account accountFilter = new Account();
		accountFilter.setActiveSw("Y");
		Example<Account> example = Example.of(accountFilter);
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

		Page<Account> findAll= repo.findAll(example,pageRequest);

		return findAll;
	}

	@Override
	public Account getAccountById(Integer AccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Integer accountId) {
		Optional<Account> findById= repo.findById(accountId);
		if (findById.isPresent()) {
			Account account = findById.get();
			account.setActiveSw("N");
			repo.save(account);	
			return true;
		}
		return false;
	}

	@Override
	public Boolean isAccountExists(Account account) {
		Example<Account> example = Example.of(account);
		return repo.exists(example);
	}

	@Override
	public Optional<Account> getAccountById(String id) {
		Optional<Account> opt = repo.findById(id);
		return opt;
	}

	@Override
	public void deleteAccount(String id) {
		Optional<Account> findById= repo.findById(id);
		if (findById.isPresent()) {
			Account Account = findById.get();
			Account.setActiveSw("N");
			repo.save(Account);	
		}
	}

	@Override
	public void updateAccount(Account acc) {
		acc.setActiveSw("Y");
		repo.save(acc);
		
	}

}
