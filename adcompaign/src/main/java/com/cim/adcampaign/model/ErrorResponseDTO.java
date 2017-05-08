package com.cim.adcampaign.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus responseStatus;
	private String errorCode;
	private String errorMsg;

	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ErrorResponseDTO [responseStatus=" + responseStatus + ", errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + "]";
	}

}
