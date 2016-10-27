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
	
	protected String naam;
	protected int score, levens;
	private int breedte, hoogte;

	
	Peddel(BreakOut wereld, String naam,int hoogte, int breedte){
		this.wereld = wereld;
		this.naam = naam;
		this.breedte = breedte;
		this.hoogte = hoogte; 
		score = 0;
		levens = 3;
		setX(wereld.getTileMap().getMapWidth()/2-breedte);
		setY(wereld.getTileMap().getMapHeight()-100);
        setFriction(0.1f);
		setHeight(hoogte);
        setWidth(breedte);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for(GameObject collide : collidedGameObjects){
			if (collide instanceof VergrotePedelPowerup){
				((VergrotePedelPowerup) collide).doePowerup();
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
                setX(vector.x-breedte);
			}
		}		
	}

	@Override
	public void update() { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255);
		g.rect(getX(), getY(), breedte, hoogte);
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
	public void setBreedte(int breedte) {
		this.breedte = breedte;
	}
	public int getBreedte() {
		return breedte;
	}
}
