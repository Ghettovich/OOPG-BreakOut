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
	
	public void usePowerUp() {
		aantalKeerVasthouden--;
		if(aantalKeerVasthouden < 1) {
			peddel.setStickyBalActief(false);			
		}
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(aantalKeerVasthouden < 1) {
			peddel.setStickyBalActief(false);
			
			wereld.deleteGameObject(this);
		}
	}

	@Override
	public void doePowerup() {
	}
	

	@Override
	public void doePowerup(Bal bal, Peddel peddel) {
		// TODO Auto-generated method stub
		aantalKeerVasthouden = 3;
		this.bal = bal;
		this.peddel = peddel;
		peddel.setStickyBalActief(true);		
		bal.setStickyBal(this);
		peddel.setStickyBal(this);
		System.out.println("sticky bal opgepakt door de peddel");
		wereld.deleteGameObject(this);
	}


}
