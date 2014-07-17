package br.com.tqi.enquete.exception;

/**
 * Exception que informa que uma enquete não pode ser removida pois está ativa.
 */
public class EnqueteInativaException extends Exception {

    private static final long serialVersionUID = 1L;

    public EnqueteInativaException() {
	super("N\u00e3o \u00e9 poss\u00edvel votar em uma enquete inativa.");
    }
}
