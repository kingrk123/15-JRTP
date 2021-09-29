package in.nit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.City;

public interface CityRepo extends JpaRepository<City, Serializable> {

	public List<City> findByStateId(Integer stateId);
}
