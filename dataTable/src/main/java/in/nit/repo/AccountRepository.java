package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nit.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
