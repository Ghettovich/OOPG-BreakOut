package breakout;


import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;


public class VergrotePedelPowerup extends SpriteObject implements IPowerup {
	private BreakOut wereld;
	private Peddel peddel;
	
	public VergrotePedelPowerup(Peddel peddel, BreakOut wereld, float x, float y) {
		super(new Sprite("src/main/java/breakout/media/VergrotePeddelPowerup.jpg"));
		this.peddel = peddel;
		this.wereld = wereld;
		setX(x);
		setY(y);
		setySpeed(1.2f);
		setHeight(50);
		setWidth(50);
	}
	
	public void setPeddel(Peddel peddel) {
		this.peddel = peddel;
	}
	
	@Override
	public void doePowerup() {
		wereld.deleteGameObject(this);
		if (peddel.getWidth() == 100 && peddel.getX() <= peddel.getWidth() + wereld.getTileMap().getTileSize()) {
			peddel.setX(wereld.getTileMap().getTileSize());
		} else if (peddel.getWidth() == 100 && peddel.getX() >= wereld.getTileMap().getMapWidth() - peddel.getWidth()) {
			peddel.setX(wereld.getTileMap().getMapWidth() - peddel.getWidth() - wereld.getTileMap().getTileSize());
		} else if(peddel.getWidth() == 100) {
			peddel.setX(getX() - 100);
		}
		peddel.setWidth(200);
		peddel.setBreedte(200);
	}

	@Override
	public void update() {
		if (getY() >= wereld.getTileMap().getMapHeight()) {
			wereld.deleteGameObject(this);
		}
	}

	@Override
	public void doePowerup(Bal bal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doePowerup(Bal bal, Peddel peddel) {
		// TODO Auto-generated method stub
		
	}
}
