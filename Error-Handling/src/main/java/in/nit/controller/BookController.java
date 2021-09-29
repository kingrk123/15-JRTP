package in.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nit.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping("/findPriceById")
	public String getPriceById(@RequestParam("bookId") String bookId, Model model ) {
		Double bPrice=service.findPriceById(bookId);
		model.addAttribute("price", "Book Price: "+bPrice);
		return "display";
	}
}
