package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import processing.core.PGraphics;

public class VergrotePedelPowerup extends SpriteObject implements IPowerup{
	private BreakOut wereld;
	private int diameter;
	private Peddel peddel;
	
	public VergrotePedelPowerup(BreakOut wereld,Peddel peddel, int x, int y){
		super(new Sprite("src/main/java/breakout/media/Penguins.jpg"));
		this.wereld = wereld;
		this.peddel = peddel;
		setX(x);
		setY(y);
		diameter = 30;
		setySpeed(diameter/25f);
		setHeight(diameter);
        setWidth(diameter); 
	}
	
	@Override
	public void doePowerup() {
		wereld.deleteGameObject(this);
				if(peddel.getX() <=peddel.getBreedte() + wereld.getTileMap().getTileSize()){
					peddel.setX(wereld.getTileMap().getTileSize());
				}
				else{
				peddel.setX(getX()-100);
				}
				peddel.setBreedte(200);
				peddel.setWidth(200);
	}

	@Override
	public void update() {
		if(getY() >=  wereld.getTileMap().getMapHeight()){
			wereld.deleteGameObject(this);
		}
		
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(156,89,189);
		g.rect(getX(), getY(), diameter, diameter);
		
	}

}
