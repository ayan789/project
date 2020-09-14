package com.example.tlcommoncomponents.decrypt;

import lombok.Data;

@Data
public class PlatformBaseException extends RuntimeException{
	/** 
	 * @since 
	 */ 
	private static final long serialVersionUID = 4851807404643931302L;
	private String error;
	private int code;

	public PlatformBaseException(String error, int code) {
		super(error);
		this.error = error;
		this.code = code;
	}


	public PlatformBaseException(Throwable throwable, int code) {
		super(throwable);
		this.error = throwable.getMessage();
		this.code = code;
	}


	public PlatformBaseException(String error, Throwable throwable, int code) {
		super(error, throwable);
		this.error = error;
		this.code = code;
	}

}
