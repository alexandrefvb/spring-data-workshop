package br.com.tqi.enquete.exception;

/**
 * Exception que informa que uma enquete não pode ser removida pois está ativa.
 */
public class EnqueteAtivaException extends Exception {

    private static final long serialVersionUID = 1L;

    public EnqueteAtivaException() {
	super(
		"A enquete informada est\u00e1 ativa e n\u00e3o pode ser removida ou atualizada.");
    }
}
