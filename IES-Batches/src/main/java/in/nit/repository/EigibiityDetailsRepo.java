package in.nit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.EligibilityDetailEntity;

public interface EigibiityDetailsRepo extends JpaRepository<EligibilityDetailEntity, Serializable> {

}
