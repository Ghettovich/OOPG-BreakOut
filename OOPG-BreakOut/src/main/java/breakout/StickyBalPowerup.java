package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * @author Bram Bergervoet, Geert Boeve
 * De powerup die zorgt dat de bal aan de peddel blijft plakken tot de speler hem afschiet
 */
public class StickyBalPowerup extends SpriteObject implements IPowerup {
	private int aantalKeerVasthouden;
	private Bal bal;
	private Peddel peddel;
	private BreakOut wereld;

	/**
	 * Constructor
	 * @param wereld Referentie naar de wereld
	 * @param b Referentie naar de bal
	 * @param x De x positie
	 * @param y De y positie
	 */
	public StickyBalPowerup(BreakOut wereld, Bal b, float x, float y) {
		super(new Sprite("src/main/java/breakout/media/StickyBalPowerup.jpg"));
		this.wereld = wereld;
		this.bal = b;
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
	}

	/**
	 * Zet het aantal keer dat de peddel de bal heeft afgeschoten een lager
	 * Als het aantal keer dat de peddel de bal heeft vastgehouden lager dan 1 is wordt de powerup uit gezet
	 */
	public void usePowerUp() {
		aantalKeerVasthouden--;
		if (aantalKeerVasthouden < 1) {
			peddel.setStickyBalActief(false);
		}
	}

	@Override
	public void update() {
		if (aantalKeerVasthouden < 1) {
			peddel.setStickyBalActief(false);
			wereld.deleteGameObject(this);
		}
	}

	@Override
	public void doePowerup() {
	}

	@Override
	public void doePowerup(Bal bal, Peddel peddel) {
		aantalKeerVasthouden = 3;
		this.bal = bal;
		this.peddel = peddel;
		peddel.setStickyBalActief(true);
		bal.setStickyBal(this);
		peddel.setStickyBal(this);
		System.out.println("sticky bal opgepakt door de peddel");
		wereld.deleteGameObject(this);
	}

	/**
	 * Zet het aantal keer dat de peddel de bal vasthoudt.
	 * @param aantalKeerVasthouden Aantal keer dat de peddel de bal vasthoudt
	 */
	public void setAantalKeerVasthouden(int aantalKeerVasthouden) {
		this.aantalKeerVasthouden = aantalKeerVasthouden;
	}

	/**
	 * Zet de peddel van een StickyBalPowerup
	 * @param peddel Referentie naar de peddel
	 */
	public void setPeddel(Peddel peddel) {
		this.peddel = peddel;
	}
}
