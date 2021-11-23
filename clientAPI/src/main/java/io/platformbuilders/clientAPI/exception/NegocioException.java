package io.platformbuilders.clientAPI.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -8211407061977622595L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}

	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
