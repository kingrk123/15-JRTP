package in.nit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nit.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Serializable> {

	
}
