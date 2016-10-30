package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class StickyBalPowerup extends SpriteObject implements IPowerup {
	
	private int breedte, hoogte, aantalKeerVasthouden;
	
	Bal bal;
	Peddel peddel;
	BreakOut wereld;


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
	
	public int getAantalKeerVasthouden() {
		return aantalKeerVasthouden;
	}	

	public void setAantalKeerVasthouden(int aantalKeerVasthouden) {
		this.aantalKeerVasthouden = aantalKeerVasthouden;
	}
		
	public void setPeddel(Peddel peddel) {
		this.peddel = peddel;
	}

	public boolean powerUpActief() {
		if(aantalKeerVasthouden > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void usePowerUp() {
		aantalKeerVasthouden--;
		
	}
	
	@Override
	public void doePowerup(Bal b) {

		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(aantalKeerVasthouden == 0) {
			wereld.deleteGameObject(this);
		}
	}

	@Override
	public void doePowerup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doePowerup(Bal bal, Peddel peddel) {
		// TODO Auto-generated method stub
		this.bal = bal;
		this.peddel = peddel;
		aantalKeerVasthouden = 3;
		bal.setStickyBal(this);
		peddel.setStickyBal(this);
		System.out.println("sticky bal opgepakt door de peddel");
		wereld.deleteGameObject(this);
	}

}
