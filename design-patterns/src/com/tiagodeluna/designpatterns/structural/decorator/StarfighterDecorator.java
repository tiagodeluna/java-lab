package com.tiagodeluna.designpatterns.structural.decorator;

/**
 * This is the base decorator class, that implements the component interface and has a HAS-A relationship 
 * with the component interface. The component variable should be accessible to the child decorator 
 * classes, so we will make this variable protected.
 * 
 * @author tiagodeluna
 *
 */
public class StarfighterDecorator implements Starfighter {

	protected Starfighter battleship;
	
	public StarfighterDecorator(Starfighter battleship) {
		super();
		this.battleship = battleship;
	}

	@Override
	public void assemble() {
		this.battleship.assemble();
	}

}
