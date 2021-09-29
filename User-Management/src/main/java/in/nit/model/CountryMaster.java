package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class CountryMaster {

	@Id
	@GeneratedValue
	private Integer countryId;
	
	private  String countryCode;
	
	private String countryName;
}
