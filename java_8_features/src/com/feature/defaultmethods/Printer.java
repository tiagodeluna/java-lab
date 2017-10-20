package com.feature.defaultmethods;

public class Printer implements IPrinter {

	@Override
	public void printAnything(String txt) {
		System.out.println(txt);
	}

}
