package breakout;

import com.sun.glass.ui.Timer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class GoudenBalPowerup extends SpriteObject implements IPowerup{
	// Zijn ze private?
	BreakOut wereld;
	Bal bal;
	int breedte, hoogte, duratie;
	private int tijd;
	
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

	public void setTijd(int tijd) {
		this.tijd = tijd;
	}

	@Override
	public void doePowerup(Bal b) {
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub


	}

	public int getTijd() {
		return tijd;
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
		// TODO Auto-generated method stub
		
	}
}
