package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Peddel extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects {
	protected String naam;
	protected int score;
	protected int levens;
	private int breedte;
	private int hoogte;
	
	Peddel(String naam,int hoogte, int breedte){
		this.naam = naam;
		this.breedte = breedte;
		this.hoogte = hoogte; 
		score = 0;
		levens = 3;
		setX(1000/2);
		setY(700);
		
		setHeight(hoogte);
        setWidth(breedte);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		// TODO Auto-generated method stub
		
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

}
