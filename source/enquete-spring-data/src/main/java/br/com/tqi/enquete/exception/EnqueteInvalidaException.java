package br.com.tqi.enquete.exception;

/**
 * Exception que indica que uma enquete possui dados inválidos.
 */
public class EnqueteInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     *            Mensagem explicando o motivo pelo qual a enquete está
     *            inválida.
     */
    private EnqueteInvalidaException(String message) {
	super(message);
    }

    /**
     * Método utilitário para lançar a exception caso a condição informada seja
     * verdadeira.
     * 
     * @param condition
     *            Condição.
     * @param errorMessage
     *            Mensagem de erro a ser atribuida.
     * @throws EnqueteInvalidaException
     *             Caso a condição seja verdadeira.
     */
    public static void throwIf(boolean condition, String errorMessage)
	    throws EnqueteInvalidaException {
	if (condition) {
	    throw new EnqueteInvalidaException(errorMessage);
	}
    }
}
