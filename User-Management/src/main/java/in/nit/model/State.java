package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATES_MASTER")
public class State {


	@Id
	@GeneratedValue
	private  Integer stateId;
	
	private Integer countryId;

	private String stateName;
}
