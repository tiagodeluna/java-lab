package com.tiagodeluna.designpatterns.structural.decorator;

/**
 * The concrete decorator extends the base decorator functionality and modifies the component behavior 
 * accordingly.
 * 
 * @author tiagodeluna
 *
 */
public class YWingStarfighter extends StarfighterDecorator {

	public YWingStarfighter(Starfighter battleship) {
		super(battleship);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.println(" Model: BTL-A4 Y-wing Assault Starfighter");
		System.out.println(" Characteristics:");
		System.out.println(" - One-pilot seat protected by transparisteel canopy");
		System.out.println(" - Domed turret with gunner behind cockpit");
		System.out.println(" - Astromech droid slot");
		System.out.println(" - Two Koensayr R200 ion jet engines");
	}
}
