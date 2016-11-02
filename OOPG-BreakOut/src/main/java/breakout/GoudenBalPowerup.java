package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * Maakt een powerUp die valt en dan opgepakt kan worden door de peddel
 * @author Bram Bergervoet, Geert Boeve
 */
public class GoudenBalPowerup extends SpriteObject implements IPowerup {
	private BreakOut wereld;
	private Bal bal;
	private int tijd;
	
	/**
	 * Constructor
	 * @param wereld Referentie naar de wereld
	 * @param bal Referentie naar de bal
	 * @param x De x positie
	 * @param y De y positie
	 */
	public GoudenBalPowerup(BreakOut wereld, Bal bal, float x, float y) {
		super(new Sprite("src/main/java/breakout/media/GoudenBalPowerup.jpg"));
		this.wereld = wereld;
		this.bal = bal;
		this.tijd = 0;
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
	}

	@Override
	public void doePowerup() {
		System.out.println("gouden bal opgepakt");
		bal.setGoudenBal(this);
		bal.setKleur(125);
		wereld.deleteGameObject(this);
	}

	@Override
	public void doePowerup(Bal bal, Peddel peddel) {
	}

	@Override
	public void update() {
	}

	/**
	 * Zet de tijd van een GoudenBalPowerup
	 * @param tijd De tijd
	 */
	public void setTijd(int tijd) {
		this.tijd = tijd;
	}
	
	/**
	 * @return tijd De tijd dat de powerup bezig is
	 */
	public int getTijd() {
		return tijd;
	}
}
