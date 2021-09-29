package in.nit.bindings;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegForm {

	
	private String fname;
	
	private String lname;
	
	private String email;
	
	private Long phno;
	
	private LocalDate dob;
	
	private String gender;
	
	private Integer countryId;
	
	private Integer stateId;
	
	private Integer cityId;
}
