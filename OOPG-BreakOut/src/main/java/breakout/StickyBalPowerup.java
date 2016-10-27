package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class StickyBalPowerup extends SpriteObject implements IPowerup {
	BreakOut wereld;
	int breedte, hoogte;
	 
	public StickyBalPowerup(BreakOut wereld, int x, int y) {
		super(new Sprite("src/main/java/breakout/media/powerup_increase.jpg"));
		this.wereld = wereld;
		setX(x);
		setY(y);
		breedte = 50;
		hoogte = 12;
		setySpeed(1.2f);
		setHeight(hoogte);
		setWidth(breedte);
	}

	@Override
	public void doePowerup() {
		wereld.deleteGameObject(this);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
