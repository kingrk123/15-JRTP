package in.nit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nit.model.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Serializable> {

	
}
