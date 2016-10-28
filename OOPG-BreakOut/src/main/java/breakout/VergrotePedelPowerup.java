package breakout;


import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;


public class VergrotePedelPowerup extends SpriteObject implements IPowerup {
	private BreakOut wereld;
	private Peddel peddel;
	
	public VergrotePedelPowerup(BreakOut wereld, Peddel peddel, int x, int y) {
		super(new Sprite("src/main/java/breakout/media/VergrotePeddelPowerup.jpg"));
		this.wereld = wereld;
		this.peddel = peddel; 
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
	}

	@Override
	public void doePowerup() {
		wereld.deleteGameObject(this);
		peddel.setWidth(200);
		peddel.setWidth(200);
		if (peddel.getX() <= peddel.getWidth() + wereld.getTileMap().getTileSize()) {
			peddel.setX(wereld.getTileMap().getTileSize());
		} else if (peddel.getX() >= wereld.getTileMap().getMapWidth() - peddel.getWidth()) {
			peddel.setX(wereld.getTileMap().getMapWidth() - peddel.getWidth() - wereld.getTileMap().getTileSize());
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
