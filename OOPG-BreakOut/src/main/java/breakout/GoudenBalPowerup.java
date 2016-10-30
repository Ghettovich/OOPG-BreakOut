package breakout;

import com.sun.glass.ui.Timer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class GoudenBalPowerup extends SpriteObject implements IPowerup{
	BreakOut wereld;
	Bal bal;
	int breedte, hoogte, duratie;
	
	

	public GoudenBalPowerup(BreakOut wereld, float x, float y) {
		super(new Sprite("src/main/java/breakout/media/GoudenBalPowerup.jpg"));
		this.wereld = wereld;
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
	}

	@Override
	public void doePowerup(Bal b) {
		wereld.deleteGameObject(this);
		//bal.setGoudenBal(this);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doePowerup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doePowerup(Bal bal, Peddel peddel) {
		// TODO Auto-generated method stub
		
	}
}
