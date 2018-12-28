package com.shameela;

/*
 * @class StudentNotFoundException for handling 404 page not found exception
 */
public class StudentNotFoundException extends RuntimeException {
/*
 * User defined exception for handling 404 exception
 */
	public StudentNotFoundException(String exception) {
		super(exception);
	}

}
