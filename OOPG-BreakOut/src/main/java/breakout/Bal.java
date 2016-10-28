package breakout;

import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Float;

import breakout.tile.MurenTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import processing.core.PGraphics;
import processing.core.PVector;

public class Bal extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects, IKeyInput {

	private BreakOut breakout;
	private int diameter;
	private float speed;
	protected int kleur;    
	
	public Bal(BreakOut breakout, float startX, float startY) {
		
		this.diameter = 30;		
		this.speed = 2;
		this.breakout = breakout;	
		
		setxSpeed(-2);
		setySpeed(-2);
		
		//setDirectionSpeed(30, speed);
		
		setY(startY);
		setX(startX);
		setWidth(diameter);
		setHeight(diameter);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//setDirectionSpeed(direction, speed);		
		if(this.getY() >= breakout.getHeight()) {
			System.out.println("DOOD");
			
		}
		
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
		PVector vector;
		for(CollidedTile tile : collidedTiles) {
			vector = breakout.getTileMap().getTilePixelLocation(tile.theTile);
			berekenBounceTile(this.getAngleFrom(((int)vector.x), ((int)vector.y)), tile);		
			System.out.println("tile collision angle:" + this.getAngleFrom(((int)vector.x), ((int)vector.y)));
			System.out.println("bereken bal direction vanaf tile: " +this.calculateDirection(vector.x, vector.y));
		}		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		for(GameObject o : collidedGameObjects) {
			
			if(o instanceof Steen || o instanceof RodeSteen) {
							
				System.out.println("bereken bal direction " + this.calculateDirection(this.getxSpeed(), this.getySpeed()));				
				System.out.println("hoek van object (steen) " + o.getAngleFrom(this));				
				berekenBounceSteen(o.getAngleFrom(this));
				
				if(o instanceof Steen) {					
					breakout.deleteGameObject(o);
				}
			}
			if(o instanceof Peddel) {
				
				setySpeed(-3);
				
			}
		}		
	}
	
	@Override
	public void keyPressed(int keyCode, char key){

	}
	
	private void berekenBounceSteen(float objectAngle) {
		
		if(objectAngle >= 0 && objectAngle < 45) {
			setySpeed(-3);		
			//flipYSpeed();
		}
		else if(objectAngle >= 45 && objectAngle < 135) {
			setxSpeed(3);			
			//flipXSpeed();
			
		}
		else if(objectAngle >=135 && objectAngle < 180) {
			setySpeed(3);		
			//flipYSpeed();
		}
		else if(objectAngle >= 180 && objectAngle < 225) {
			setySpeed(3);
			//flipYSpeed();
		}
		else if(objectAngle >= 225 && objectAngle < 270) {
			setxSpeed(-3);	
			//flipXSpeed();
		}
		else if(objectAngle >= 270 && objectAngle <= 360) {
			setySpeed(-3);
			//flipYSpeed();			
		}		
	}
	
	private void berekenBounceTile(float objectAngle, CollidedTile tile) {
		if(tile.collisionSide == tile.LEFT){			
			
			if(objectAngle >= 0 && objectAngle <= 90) {
				setxSpeed(-3);
			}	
			else if(objectAngle >= 90 && objectAngle <= 180) {
				setxSpeed(3);
			}
		}
		else if(tile.collisionSide == tile.BOTTOM) {				
			setySpeed(3);				
		}
		else if(tile.collisionSide == tile.RIGHT) {
			
			if(objectAngle >= 180 && objectAngle <= 270) {
				setxSpeed(-3);
			}	
			else if(objectAngle >= 270 && objectAngle <= 360) {
				setxSpeed(3);
			}
		}
	}
	
	private void flipXSpeed() {
		
		System.out.println("x snelheid" + getxSpeed());		
				
		if(getxSpeed() < 0) {			
			setxSpeed(getxSpeed() - (2 * getxSpeed()));			
		}
		else {
			setxSpeed(getxSpeed() + (2 * getxSpeed()));
		}		
	}
	
	private void flipYSpeed() {
		
		System.out.println("y snelheid: " + getySpeed());
		
		if(getySpeed() < 0) {		
			setySpeed(getySpeed() - 2 * getySpeed());			
		}
		else {
			setySpeed(getySpeed() + 2 * getySpeed());		
		}
	}
	
	private float flipSpeed(float speed) {
		
		if(speed < 0) {
			return speed - (2 * speed);
		}
		else {
			return speed + (2 * speed);
		}		
	}
	
	

}
