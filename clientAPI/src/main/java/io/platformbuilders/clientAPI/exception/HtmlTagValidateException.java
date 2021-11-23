package io.platformbuilders.clientAPI.exception;

public class HtmlTagValidateException extends RuntimeException {

	private static final long serialVersionUID = -8211407061977622595L;

	public HtmlTagValidateException(String mensagem) {
		super(mensagem);
	}

	public HtmlTagValidateException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}