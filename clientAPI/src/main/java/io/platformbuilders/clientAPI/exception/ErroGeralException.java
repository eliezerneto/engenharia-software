package io.platformbuilders.clientAPI.exception;

public class ErroGeralException extends NegocioException {

	private static final long serialVersionUID = -3866243421121379230L;

	public ErroGeralException() {
		super("Problema ao processar sua solicitação!");
	}

}
