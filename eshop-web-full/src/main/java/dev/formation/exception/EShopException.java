package dev.formation.exception;

public class EShopException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EShopException() {
		super();
	}

	public EShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EShopException(String message, Throwable cause) {
		super(message, cause);
	}

	public EShopException(String message) {
		super(message);
	}

	public EShopException(Throwable cause) {
		super(cause);
	}

}
