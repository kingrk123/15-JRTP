package in.nit.service.imp;

import org.springframework.stereotype.Service;

import in.nit.exception.NoBookFoundException;
import in.nit.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Override
	public Double findPriceById(String bookId) {
		if (bookId.equals("B101")) {
			return 450.00;
		}else {
			throw new NoBookFoundException("No Book Found with given ID");
		}
	}
}
