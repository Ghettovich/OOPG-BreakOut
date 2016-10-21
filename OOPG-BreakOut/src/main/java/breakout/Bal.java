package breakout;

import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Float;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PGraphics;

public class Bal extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	private BreakOut breakout;
	private float direction;
	private int diameter;
	private int speed;
	protected int kleur;
	
	public Bal(BreakOut breakout) {
		this.diameter = 25;
		this.x = 312;
		this.y = 200;
		this.speed = 2;
		this.breakout = breakout;	
		this.breakout.addGameObject(this, getX(), getY());
		this.direction = 40;
		this.setDirectionSpeed(direction, speed);
		
		setY(y);
		setX(x);		
		setWidth(diameter);
		setHeight(diameter);
	}
	
		
	@Override
	public void update() {
		// TODO Auto-generated method stub
		setDirectionSpeed(direction, speed);		
		
	}
	
//	For further answer you, this will give you the following://
//		0 Degrees: Straight up;
//		90 Degrees: Straight Right; 180 Degrees: Straight Down; 270 Degrees: Straight Left;//
//		So lets test.
//		0 - 45 = 360 - 45 = 315
//		0 + 45 = 45
//		Between 315 and 360 we have up. Between 0 and 45 too.
//		90 - 45 = 45
//		90 + 45 = 135
//		Between 45 and 135 we have left.
//		180 - 45 = 135
//		180 + 45 = 225
//		Between 135 and 225 we have down.
//		270 - 45 = 225
//		270 + 45 = 315	
//		Between 225 and 315 we have left.
	
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
		for(CollidedTile tiles : collidedTiles) {
			
			System.out.println("tile collision");
		}		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		for(GameObject o : collidedGameObjects) {
			
						
			if(o instanceof Steen || o instanceof RodeSteen) {
				
				if(getDirection() >= 315 && getDirection() <= 360) {
					//direction =
					setDirection(direction);
					
				}
				else if(getDirection() >= 0 && getDirection() <= 45) {
					direction += 90;
				}		
				
				setDirectionSpeed(direction, speed);	
				
				((Steen)o).setLevens(((Steen)o).getLevens() -1);			
				if(((Steen)o).getLevens() < 1) {
					
					breakout.deleteGameObject(o);
					//System.out.println("Steen collision");
										
				}				
			}				
		}		

	}

}
