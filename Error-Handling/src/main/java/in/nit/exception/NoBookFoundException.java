package in.nit.exception;

public class NoBookFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBookFoundException(String msg) {
		super(msg);
	}
}
