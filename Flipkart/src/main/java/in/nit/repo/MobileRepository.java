package in.nit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.model.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Serializable> {

	@Query("select distinct(brand) from Mobile")
	public List<String> findByBrandNames();
	
	@Query("select distinct(ram) from Mobile")
	public List<Integer> findByRam();
	
	@Query("select distinct(rating) from Mobile")
	public List<Integer> findByRating();
}
