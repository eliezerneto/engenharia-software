package io.platformbuilders.clientAPI.exception;

public class RegistroNaoEncontradoException extends NegocioException {

	private static final long serialVersionUID = -3585414845239378650L;

	public RegistroNaoEncontradoException() {
		super("Registro não encontrado!");
	}

}
