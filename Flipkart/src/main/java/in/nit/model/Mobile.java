package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MOBILES")
public class Mobile {

	@Id
	@Column(name = "MOBILE_ID")
	private Integer mobileId;
	
	@Column(name = "BRAND_NAME")
	private String brand;
	
	@Column(name = "MOBILE_PRICE")
	private Double price;
	
	@Column(name = "MOBILE_RAM")
	private Integer ram;
	
	@Column(name = "MOBILE_RATING")
	private Integer rating;
	
}
