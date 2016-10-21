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
	protected int score;
	protected int levens;
	private int breedte;
	private int hoogte;
	private int minX;
	private float maxX;
	
	Peddel(BreakOut wereld, String naam,int hoogte, int breedte){
		this.wereld = wereld;
		this.naam = naam;
		this.breedte = breedte;
		this.hoogte = hoogte; 
		minX = 30;
		maxX = -200;
		score = 0;
		levens = 3;
		setX(900/2-breedte);
		setY(500);
        setFriction(0.1f);
		setHeight(hoogte);
        setWidth(breedte);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		 PVector vector;
		for(CollidedTile collide: collidedTiles){
			System.out.println("collide");
			if(collide.collisionSide == collide.RIGHT){
				System.out.println(x);
				setxSpeed(0);
				vector = wereld.getTileMap().getTilePixelLocation(collide.theTile);
                setX(vector.x+30);
			}
			else if(collide.collisionSide == collide.LEFT){
				setxSpeed(0);
				vector = wereld.getTileMap().getTilePixelLocation(collide.theTile);
                setX(vector.x-breedte);
				System.out.println(wereld.width);
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
}
