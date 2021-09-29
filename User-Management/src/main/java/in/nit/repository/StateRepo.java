package in.nit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.State;

public interface StateRepo extends JpaRepository<State, Serializable> {

	public List<State> findByCountryId(Integer countryId);
}
