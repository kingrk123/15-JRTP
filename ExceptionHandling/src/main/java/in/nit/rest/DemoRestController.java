package in.nit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

	@GetMapping("/")
	public String demo1() {
		String msg =null;
		try {
			int i =10/0;
			msg = "Welcome To Ashok It";
		} catch (Exception e) {
			throw new ArithmeticException("Number Should Not Divide by 0");
		}
		return msg;
	}
	
	
}
