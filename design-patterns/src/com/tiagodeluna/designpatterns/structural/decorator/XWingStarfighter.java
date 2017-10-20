package com.tiagodeluna.designpatterns.structural.decorator;

/**
 * The concrete decorator extends the base decorator functionality and modifies the component behavior 
 * accordingly.
 * 
 * @author tiagodeluna
 *
 */
public class XWingStarfighter extends StarfighterDecorator {

	public XWingStarfighter(Starfighter battleship) {
		super(battleship);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.println(" Model: T-65 X-wing Starfighter");
		System.out.println(" Characteristics:");
		System.out.println(" - Possessing deflector shields");
		System.out.println(" - Hyperdrive");
		System.out.println(" - R2 astromech for repairs and navigation");
		System.out.println(" - Proton torpedoes");
	}
}
