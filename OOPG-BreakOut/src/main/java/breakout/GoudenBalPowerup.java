package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class GoudenBalPowerup extends SpriteObject implements IPowerup{
	BreakOut wereld;
	int breedte, hoogte;

	public GoudenBalPowerup(BreakOut wereld, int x, int y) {
		super(new Sprite("src/main/java/breakout/media/GoudenBalPowerup.jpg"));
		this.wereld = wereld;
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
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
