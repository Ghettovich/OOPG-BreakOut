package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import processing.core.PGraphics;
import processing.core.PVector;

public class Peddel extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects, IKeyInput {
	
	private BreakOut wereld;	
	private Bal bal;
	private StickyBalPowerup stickyBal;
	protected String naam;
	protected int score, levens, hoogte, breedte;
	private int tijd;
	private boolean stickyBalActief;

	Peddel(BreakOut wereld, String naam,int hoogte, int breedte){
		this.wereld = wereld;
		this.naam = naam;
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.stickyBalActief = false;
		score = 0;
		levens = 1;
		setX(wereld.getTileMap().getMapWidth()/2-breedte/2);
		setY(wereld.getTileMap().getMapHeight()-50);
        setFriction(0.1f);
		setHeight(hoogte);
        setWidth(breedte);
	}
	
	public boolean getStickyBalActief() {
		return stickyBalActief;
	}
	
	public void setStickyBalActief(boolean stickyBalActief) {
		this.stickyBalActief = stickyBalActief;
	}

	public void setBal(Bal bal) {
		this.bal = bal;		
	}
	 
	public int getHoogte() {
		return hoogte;
	}

	public void setHoogte(int hoogte) {
		this.hoogte = hoogte;
	}

	public int getBreedte() {
		return breedte;
	}

	public void setBreedte(int breedte) {
		this.breedte = breedte;
	}
	
	public void setStickyBal(StickyBalPowerup stickyBal) {		
		this.stickyBal = stickyBal;
	}
	
	public StickyBalPowerup getStickyBallPowerup() {
		return stickyBal;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for(GameObject collide : collidedGameObjects){
			if (collide instanceof IPowerup){
				if(collide instanceof StickyBalPowerup) {					
					((StickyBalPowerup) collide).doePowerup(bal, this);
				}
				else if(collide instanceof GoudenBalPowerup) {
					((GoudenBalPowerup) collide).doePowerup(bal);
				}
				else if(collide instanceof VergrotePedelPowerup) { 
					((VergrotePedelPowerup) collide).setPeddel(this);
					((VergrotePedelPowerup) collide).doePowerup();
				}
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		
		PVector vector;
		for(CollidedTile collide: collidedTiles){
			setxSpeed(0);
			if(collide.collisionSide == collide.RIGHT){
				vector = wereld.getTileMap().getTilePixelLocation(collide.theTile);
                setX(vector.x + wereld.getTileMap().getTileSize());
			}
			else if(collide.collisionSide == collide.LEFT){
				vector = wereld.getTileMap().getTilePixelLocation(collide.theTile);
                setX(vector.x-getWidth());
			}
		}		
	}

	@Override
	public void update() { 
		if(getWidth() == 200){
			tijd++;
		}
		if(tijd >= 600){
			setWidth(100);
			setX(getX()- 50);
			tijd = 0;
		}	
		if(balOpPeddel()) {	
			
			bal.setX(getCenterX());
			if(getStickyBalActief()) {
				bal.setxSpeed(0);
				bal.setySpeed(0);	
				
			}
//			else {
//				//bal.setX(getCenterX() - (bal.getDiameter() / 2));
//			}
			//bal.setX(getPrevX());// / 2);
			//bal.setX(getCenterX() - bal.getDiameter() / 2);
			//bal.setY(getY() - bal.getDiameter());	
			System.out.println("bal op peddel | bal y + dia = " + (bal.getY() + bal.getDiameter()) + " | peddel y =  " + getY());
		}
	}
	
	@Override
	public void draw(PGraphics g) {
		g.fill(255);
		g.rect(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void keyPressed(int keyCode, char key){
		final int speed = 10;
        if (keyCode == wereld.LEFT) {
            setDirectionSpeed(270, speed);
        }
        if (keyCode == wereld.RIGHT) {
            setDirectionSpeed(90, speed);
        }        
        if(keyCode == wereld.UP) {        	
        	System.out.println("up pressed" );
    		if((bal.getxSpeed() == 0 && bal.getySpeed() == 0)) {     			
    			System.out.println("speed = 0 set speed" );
    			bal.setY(bal.getY() - 5);
				bal.setySpeed(-3);
				//bal.setxSpeed(-1);
				if(stickyBalActief) {
					stickyBal.usePowerUp();
				}
			}	
		}
	}	
	
	public boolean balOpPeddel() {
		//System.out.println("bal op peddel | bal y + dia = " + (bal.getY() + bal.getDiameter()) + " | peddel y =  " + getY());
		if( bal.getY() + bal.getHeight() >= getY() && (bal.getCenterX() >= getX() && bal.getCenterX() <= (getX() + getBreedte()))) {			
			return true;
		}
		else {
			return false;
		}		
	}
	
	
	///getters en setters
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevens() {
		return levens;
	}

	public void setLevens(int levens) {
		this.levens = levens;
	}

	public String getNaam() {
		return naam;
	}
	
	public void levenMinder() {
		levens--;
	}
}
