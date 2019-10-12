package exception;

public class PazienteNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PazienteNotFoundException() {
		super();
	}

	public PazienteNotFoundException(String message) {
		super(message);
	}

}
