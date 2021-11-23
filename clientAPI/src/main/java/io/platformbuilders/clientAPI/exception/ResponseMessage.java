package io.platformbuilders.clientAPI.exception;

import java.util.List;

public class ResponseMessage {

	private List<ErroPadrao> errors;

	public List<ErroPadrao> getErrors() {
		return errors;
	}

	public void setErrors(List<ErroPadrao> errors) {
		this.errors = errors;
	}

}