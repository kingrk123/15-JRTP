package in.nit.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

	
	@ExceptionHandler(value = ArithmeticException.class)
	public ResponseEntity<String> handleArithmaticException(ArithmeticException ae) {
		return new ResponseEntity<>(ae.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NoDataFound.class)
	public ResponseEntity<ErrorResponse> handleNoDataFoundException(NoDataFound ndf) {
		
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("ERROR202");
		response.setErrorMsg(ndf.getMessage());
		response.setDate(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}

