package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class StickyBalPowerup extends SpriteObject implements IPowerup {
	
	private int breedte, hoogte, aantalKeerVasthouden;
	
	Bal bal;
	Peddel peddel;
	BreakOut wereld;


	public StickyBalPowerup(BreakOut wereld, Bal b, Peddel peddel, float x, float y) {
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
		this.bal = b;
		aantalKeerVasthouden = 3;
		b.setStickyBal(this);	
		peddel.setStickyBal(this);		
		System.out.println("sticky bal opgepakt door de peddel");
		wereld.deleteGameObject(this);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if (bal != null) {
			if(peddel.getY() == this.getY() + bal.getDiameter() && this.getX() >= peddel.getX() &&
					this.getX() <= peddel.getX() + peddel.getBreedte() ) {
				
				peddel.setX(getX() + peddel.getCenterX());			
				bal.setxSpeed(0);
				bal.setySpeed(0);
				setSpeed(0);
				System.out.println("stickybal actief peddel collision");
				
			}
		}
	}

	@Override
	public void doePowerup() {
		// TODO Auto-generated method stub
		
	}

}
