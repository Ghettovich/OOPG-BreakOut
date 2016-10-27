package breakout;

import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Float;

import breakout.tile.MurenTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PGraphics;

public class Bal extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	private BreakOut breakout;
	private int diameter;
	private int speed;
	protected int kleur;
	
	public Bal(BreakOut breakout, float startX, float startY) {
		
		this.diameter = 30;		
		this.speed = 2;
		this.breakout = breakout;					
		setxSpeed(-1);
		setySpeed(-4);
		setY(startY);
		setX(startX);
		setWidth(diameter);
		setHeight(diameter);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//setDirectionSpeed(direction, speed);		
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
		for(CollidedTile tile : collidedTiles) {
							
			if(tile.collisionSide == 1 ){
				setxSpeed(2);
			}
			else if(tile.collisionSide == 2) {				
				setySpeed(4);				
				System.out.println("tile collision");
			}
			else if(tile.collisionSide == 3) {
				setxSpeed(-2);
			}					
		}		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		for(GameObject o : collidedGameObjects) {
			
			if(o instanceof Steen || o instanceof RodeSteen) {
										
								
				System.out.println(o.calculateDirection(o.getxSpeed(), o.getySpeed()));
				System.out.println(this.calculateDirection(this.getxSpeed(), this.getySpeed()));
				
				System.out.println(o.getAngleFrom(this));
				
				if(o.getAngleFrom(this) >= 270 && o.getAngleFrom(this) <= 360) {
					setySpeed(-4);
				
				}
				if(o.getAngleFrom(this) >= 0 && o.getAngleFrom(this) <= 45) {
					setySpeed(-4);					
				}			
				if(o.getAngleFrom(this) >= 90 && o.getAngleFrom(this) <= 180) {
					setySpeed(4);					
				}	
				if(o.getAngleFrom(this) >= 180 && o.getAngleFrom(this) <= 270) {
					setySpeed(4);					
				}	
				
												
				if(o instanceof Steen || o instanceof RodeSteen) {
					
					((Steen)o).setLevens(((Steen)o).getLevens() -1);			
					if(((Steen)o).getLevens() == 0) {
						
						breakout.deleteGameObject(o);
						//System.out.println("Steen collision");
											
					}					
				}
			}
			if(o instanceof Peddel) {
				System.out.println("peddel collision");
				setySpeed(-4);
				
			}
		}		
	}

}
