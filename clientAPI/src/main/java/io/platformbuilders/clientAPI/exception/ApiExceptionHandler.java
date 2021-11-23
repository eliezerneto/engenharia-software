package io.platformbuilders.clientAPI.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> tratarNegocio(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErroPadrao erroPadrao = new ErroPadrao();
		ResponseMessage problema = new ResponseMessage();
		erroPadrao.setCodigo(status.value());

		StringBuilder sb = new StringBuilder();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			sb.append((sb.length() > 0 ? "," : "") + mensagem);
		}
		erroPadrao.setMensagem(sb.toString());

		List<ErroPadrao> lstErroPadrao = new ArrayList<>();
		lstErroPadrao.add(erroPadrao);
		problema.setErrors(lstErroPadrao);
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ResponseEntity<Object> tratarRegistroNaoEncontrado(RegistroNaoEncontradoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		return handleExceptionInternal(ex, null, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ErroGeralException.class)
	public ResponseEntity<Object> tratarRegistroErroGeralException(ErroGeralException ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		return handleExceptionInternal(ex, null, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(HtmlTagValidateException.class)
	public ResponseEntity<Object> tratarTagHTML(HtmlTagValidateException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErroPadrao erroPadrao = new ErroPadrao();
		erroPadrao.setCodigo(status.value());
		erroPadrao.setMensagem(ex.getMessage());

		if (body instanceof String)
			erroPadrao.setMensagem((String) body);
		else if (HttpStatus.BAD_REQUEST.equals(status))
			erroPadrao.setMensagem("Requisição mal formada.");

		ResponseMessage responseMessage = new ResponseMessage();
		List<ErroPadrao> lstErroPadrao = new ArrayList<>();
		lstErroPadrao.add(erroPadrao);
		responseMessage.setErrors(lstErroPadrao);

		return super.handleExceptionInternal(ex, responseMessage, headers, status, request);
	}

}
