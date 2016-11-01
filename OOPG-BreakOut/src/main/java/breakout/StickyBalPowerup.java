package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class StickyBalPowerup extends SpriteObject implements IPowerup {
	private int aantalKeerVasthouden;
	private Bal bal;
	private Peddel peddel;
	private BreakOut wereld;

	public StickyBalPowerup(BreakOut wereld, Bal b, float x, float y) {
		super(new Sprite("src/main/java/breakout/media/StickyBalPowerup.jpg"));
		this.wereld = wereld;
		this.bal = bal;
		this.peddel = peddel;
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
	}

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

	public void setAantalKeerVasthouden(int aantalKeerVasthouden) {
		this.aantalKeerVasthouden = aantalKeerVasthouden;
	}

	public void setPeddel(Peddel peddel) {
		this.peddel = peddel;
	}
}
