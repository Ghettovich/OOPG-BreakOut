package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PGraphics;

public class Bal extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	private BreakOut breakout;
	private int diameter;
	protected int kleur;
	
	public Bal(BreakOut breakout) {
		this.diameter = 25;
		this.x = 612;
		this.y = 200;
		this.breakout = breakout;	
		
		setySpeed(-1);		
		setWidth(diameter);
		setHeight(diameter);		
		this.breakout.addGameObject(this, getX(), getY());
	}
	
		
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub		
        g.ellipseMode(g.CORNER); 
        g.stroke(0, 50, 200, 100);
        g.fill(0, 50, 200, 255);
        g.ellipse(getX(), getY(), diameter, diameter);				
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

}
