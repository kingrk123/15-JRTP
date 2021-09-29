package in.nit.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class AppExceptionHandler {

	///Global Exception Handler
	@ExceptionHandler(value =NullPointerException.class )
	public String handleNullPinterException(Model model) {
		model.addAttribute("errMsg", "Some Problem Occured, Please Try after Sometime...!");
		return "error";
	}
	
	@ExceptionHandler(value =NoBookFoundException.class )
	public String noBookFoundException(Model model) {
		model.addAttribute("errMsg", "Some Problem Occured, Please Try after Sometime...!");
		return "error";
	}
}
