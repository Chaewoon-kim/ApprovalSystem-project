package com.oopsw.exception;

public class DatabaseTransactionException extends Exception {
	
	public DatabaseTransactionException(){
		super();
	}
	
	public DatabaseTransactionException(String message){
		super(message);
	}
}
