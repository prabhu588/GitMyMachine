package com.db.awmd.challenge.exception;

public class TrasactonFailedException extends RuntimeException{

	 public TrasactonFailedException(String message) {
		    super("Transaction failed");
		  }
}
