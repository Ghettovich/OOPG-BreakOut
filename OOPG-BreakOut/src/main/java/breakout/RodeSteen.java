package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;

/**
 * Een RodeSteen die geraakt moet worden door de bal. Deze heeft alleen twee levens
 * @author Bram Bergervoet, Geert Boeve
 */
public class RodeSteen extends Steen implements ICollidableWithGameObjects {
	private BreakOut breakout;
	
	/**
	 * Constructor
	 * @param peddel Referentie naar de peddel
	 * @param breakout Referentie naar de wereld
	 * @param kleur De kleur
	 * @param x De x positie
	 * @param y De y positie
	 */
	public RodeSteen(Peddel peddel, BreakOut breakout, int kleur, float x, float y) {
		super(peddel, breakout, kleur, x, y);
		this.breakout = breakout;
		levens = 2;		
		setY(y);
		setX(x);
		setWidth(breedte);
		setHeight(hoogte);
	}
	
	@Override
	public void update() {
		if(levens < 1) {
			breakout.deleteGameObject(this);
			peddel.setScore(peddel.getScore() + 5);
			breakout.refreshDasboardText();
		}	
	}
}
