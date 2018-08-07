package com.mftcc.common;



public class DAOException extends java.lang.RuntimeException {
	
	private static final long serialVersionUID = -5409404883539380796L;
	
	private String errorCode;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
	public DAOException(String message) {		
		super(new Exception(message));
		this.message = message;
	}


	public DAOException(Exception e){
		super(e);
	}
	

}
