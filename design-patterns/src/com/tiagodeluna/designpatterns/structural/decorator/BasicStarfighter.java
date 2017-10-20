package com.tiagodeluna.designpatterns.structural.decorator;

/**
 * This is the basic implementation of the component interface.
 * 
 * @author tiagodeluna
 *
 */
public class BasicStarfighter implements Starfighter {

	@Override
	public void assemble() {
		System.out.println("*** Building Spaceship ***");
		System.out.println(" Class: Starfighter");
		System.out.println(" Client: Rebel Alliance");
	}

}
