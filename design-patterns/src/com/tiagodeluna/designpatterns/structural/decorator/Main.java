package com.tiagodeluna.designpatterns.structural.decorator;

/**
 * Decorator pattern allows a user to add new functionality to an existing object without altering 
 * its structure. This type of design pattern comes under structural pattern as this pattern acts 
 * as a wrapper to existing class.
 * 
 * This pattern creates a decorator class which wraps the original class and provides additional 
 * functionality keeping class methods signature intact.
 * 
 * In the following example, we demonstrate the use of this pattern creating two different types of 
 * Rebel Alliance starships with its specific features without altering the Basic Starfighter class.
 * 
 * @author tiagodeluna
 *
 */
public class Main {

	public static void main(String[] args) {

		//Creating a X-Wing Starfighter
		Starfighter xwing = new XWingStarfighter(new BasicStarfighter());
		xwing.assemble();

		System.out.println("\n");
		
		//Creating a Y-Wing Starfighter
		Starfighter ywing = new YWingStarfighter(new BasicStarfighter());
		ywing.assemble();
	}

}
