package in.nit.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import in.nit.service.MobileService;

@RestController
public class MobileRestcontroller {

	@Autowired
	private MobileService service;
	
	/*
	 * @GetMapping("/mobiles") public List<Mobile> getMobiles() { return
	 * service.fetchAllMobiles(); }
	 */
}
