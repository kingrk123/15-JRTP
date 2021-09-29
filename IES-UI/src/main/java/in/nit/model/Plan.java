package in.nit.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "P_DTLS")
public class Plan {

	@Id
	@GeneratedValue
	private int PLAN_ID;
	
	private String PLAN_NAME;
	
	private String PLAN_DESC;
	private String ACIVE_SW;
	private LocalDate START_DT;
	private LocalDate END_DT;
	private LocalDate CREATED_DT;
	private String CREATED_BY;
	private LocalDate UPDATED_DT;
	private String UPDATED_BY;
	private String activeSw;

}
