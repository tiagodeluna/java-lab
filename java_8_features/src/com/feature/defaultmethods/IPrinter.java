package com.feature.defaultmethods;

public interface IPrinter {

	void printAnything(String txt);
	
	//This is an interface static method
	static void printError(String txt) {
		System.err.println(txt);
	}
	
	//This is an interface default method
	default void printNumber(Integer number) {
		if (number != null) {
			printAnything(number.toString());
		}
		else {
			printError("The number value is null");
		}
	}
}
