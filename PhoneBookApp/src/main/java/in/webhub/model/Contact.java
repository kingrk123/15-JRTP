package in.webhub.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTACT_DTLS")
public class Contact {

	@Id
	@GeneratedValue
	@Column(name = "CONTACT_ID")
	private Integer contactId;
	
	@Column(name = "CONTACT_NAME")
	@NotEmpty(message = "Contact Name is Mandatory")
	private String contactName;
	
	@Column(name = "CONTACT_NUMBER")
	@NotNull(message = "Contact Number is Mandatory")
	//@Pattern(regexp="(^$|[0-9]{10})")
	private Long contactNumber;
	
	
	@Column(name = "CONTACT_EMAIL")
	@NotEmpty(message = "Contact Email is Mandatory")
	@Email(message = "please provide valid Email")
	private String contactEmail;
	
	@Column(name = "ACTIVE_SW")
	private String activeSw;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE",updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false )
	private LocalDate updatedDate;
}
