package in.nit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.CountryMaster;

public interface CountryRepo extends JpaRepository<CountryMaster, Serializable> {

}
