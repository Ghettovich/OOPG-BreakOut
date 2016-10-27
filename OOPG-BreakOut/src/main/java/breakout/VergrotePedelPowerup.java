package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import processing.core.PGraphics;

public class VergrotePedelPowerup extends SpriteObject implements IPowerup {
	private BreakOut wereld;
	private int breedte, hoogte;
	private Peddel peddel;

	public VergrotePedelPowerup(BreakOut wereld, Peddel peddel, int x, int y) {
		super(new Sprite("src/main/java/breakout/media/powerup_increase.jpg"));
		this.wereld = wereld;
		this.peddel = peddel; 
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
		peddel.setBreedte(200);
		peddel.setWidth(200);
		if (peddel.getX() <= peddel.getBreedte() + wereld.getTileMap().getTileSize()) {
			peddel.setX(wereld.getTileMap().getTileSize());
		} else if (peddel.getX() >= wereld.getTileMap().getMapWidth() - peddel.getBreedte()) {
			peddel.setX(wereld.getTileMap().getMapWidth() - peddel.getBreedte() - wereld.getTileMap().getTileSize());
		} else {
			peddel.setX(getX() - 100);
		}
	}

	@Override
	public void update() {
		if (getY() >= wereld.getTileMap().getMapHeight()) {
			wereld.deleteGameObject(this);
		}

	}
}
