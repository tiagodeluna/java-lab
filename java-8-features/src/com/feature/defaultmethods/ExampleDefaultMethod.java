package com.feature.defaultmethods;

public class ExampleDefaultMethod {

	public static void main(String[] args) {
		
		Printer example = new Printer();
		example.printAnything("Tiago Luna");
		example.printNumber(10);
		example.printNumber(new Integer(100));
		example.printNumber(null);
	}
}
