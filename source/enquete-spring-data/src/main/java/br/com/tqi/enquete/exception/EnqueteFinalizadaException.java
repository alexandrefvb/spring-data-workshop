package br.com.tqi.enquete.exception;

/**
 * Exception que informa que uma enquete não pode ser removida pois está ativa.
 */
public class EnqueteFinalizadaException extends Exception {

    private static final long serialVersionUID = 1L;

    public EnqueteFinalizadaException() {
	super("A enquete est\u00e1 ativa e n\u00e3o pode ser atualizada.");
    }
}
