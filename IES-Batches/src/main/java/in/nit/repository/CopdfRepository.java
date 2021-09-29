package in.nit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.CoPdfEntity;

public interface CopdfRepository extends JpaRepository<CoPdfEntity, Serializable> {

}
