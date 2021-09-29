package in.nit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

	private static final Logger log = LoggerFactory.getLogger(DemoRestController.class);

	
	@GetMapping("/")
	public String  getMsg() {
		log.debug("Method execution Started");
		String msg = "Welcome To AshokIt";
		log.debug("Method execution Endeded");
		log.info("Method execution Completed");
		return msg;
	}
}
