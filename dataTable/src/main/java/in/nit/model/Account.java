package in.nit.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

	@Id
	
	/*
	 * @GenericGenerator(name = "acc_id_gen", strategy =
	 * "in.nit.generator.Mygenerator")
	 * 
	 * @GeneratedValue(generator = "order_id_gen")
	 */
	@GeneratedValue
	private Integer id;
	
	private String fName;			
	private String lName;				
	private String gender;
	private Long ssn;
	private Long phNo;
	private String eMail;
	private String role;
	private String password;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dob;
	private String activeSw;
	
}
