package in.nit.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT_DTLS")
public class Account {

	@Id
	@GenericGenerator(name = "admin_id_gen",
						strategy = "in.nit.generator.AdminGenerator")
	@GeneratedValue(generator = "admin_id_gen")
	@Column(name = "acc_Id")
	private String accId;

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
