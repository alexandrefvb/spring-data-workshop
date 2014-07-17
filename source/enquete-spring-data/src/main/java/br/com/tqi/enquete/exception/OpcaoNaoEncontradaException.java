package br.com.tqi.enquete.exception;

public class OpcaoNaoEncontradaException extends Exception {

    private static final long serialVersionUID = 1L;

    public OpcaoNaoEncontradaException() {
	super("A op\u00e7\u00e3o informada n\u00e3o faz parte da enquete!");
    }
}
