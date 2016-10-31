package breakout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	protected int kleur;  
	
	private BreakOut breakout;
	private int diameter;
	private Peddel peddel;
	private GoudenBalPowerup goudenBal;
	private StickyBalPowerup stickyBal;
		
	public Bal(BreakOut breakout, Peddel peddel) {		
		this.diameter = 30;	
		this.breakout = breakout;
		this.peddel = peddel;
		this.kleur = 0xFFFFFFFF; 
		setY(peddel.getY() - diameter);
		setX(peddel.getX() + diameter);		
		setWidth(diameter);
		setHeight(diameter);
	}
	
	public void setKleur(int kleur) {
		this.kleur = kleur;
	}

	public GoudenBalPowerup getGoudenBal() {
		return goudenBal;
	}

	public void setGoudenBal(GoudenBalPowerup goudenBal) {
		this.goudenBal = goudenBal;
	}
	
	public void setStickyBal(StickyBalPowerup stickyBal) {
		this.stickyBal = stickyBal;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub	
		if(this.getY() >= breakout.getHeight()) {			
			if(peddel.getLevens() < 1) {
				System.out.println("DOOD");
			}
			else {
				setY(peddel.getY() - diameter);
				setX(peddel.getX() + diameter);	
				setSpeed(0);
				peddel.levenMinder();
			}
		}
		if(goudenBal != null) {
			goudenBal.setTijd(goudenBal.getTijd() + 1);
			if(goudenBal.getTijd() >= 180) {
				setKleur(0xFFFFFFFF);
				setGoudenBal(null);
			}
		}
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
        g.ellipseMode(g.CORNER); 
        //g.stroke(0, 50, 200, 100);
        g.fill(kleur);
        g.ellipse(getX(), getY(), diameter, diameter);				
	}
	
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		// TODO Auto-generated method stub
		PVector vector;
		for(CollidedTile tile : collidedTiles) {
			vector = breakout.getTileMap().getTilePixelLocation(tile.theTile);
			berekenBounceTile(this.getAngleFrom(((int)vector.x), ((int)vector.y)), tile);			
		}		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		for(GameObject o : collidedGameObjects) {
			
			if(o instanceof Steen) {						
				peddel.setScore(peddel.getScore() + 1);
				//if(goudenBal.)
				if(getGoudenBal() == null) {
					berekenBounceSteen(o.getAngleFrom(this));	
				}							
			}
			if(o instanceof Peddel) {
				if(peddel.getStickyBalActief()) {	
					stickyBal.setPeddel(peddel);
					System.out.println("peddel geraakt, stickybal actief | aantalkeervast = " + peddel.getStickyBallPowerup().getAantalKeerVasthouden());
				}
				else
				{
					berekenBounceSteen(o.getAngleFrom(this));	
				}				
			}
		}		
	}
	
	private void berekenBounceSteen(float objectAngle) {
		
		if(objectAngle >= 0 && objectAngle < 45) {
			setySpeed(-3);	
		}
		else if(objectAngle >= 45 && objectAngle < 135) {			
			setxSpeed(3);			
		}
		else if(objectAngle >=135 && objectAngle < 180) {
			setySpeed(3);	
		}
		else if(objectAngle >= 180 && objectAngle < 225) {
			setySpeed(3);
		}
		else if(objectAngle >= 225 && objectAngle < 270) {
			setxSpeed(-3);	
		}
		else if(objectAngle >= 270 && objectAngle <= 360) {
			setySpeed(-3);		
		}		
	}
	
	private void berekenBounceTile(float objectAngle, CollidedTile tile) {
		if(tile.collisionSide == tile.LEFT){			
			
//			if(objectAngle >= 0 && objectAngle <= 90) {
//				setxSpeed(-3);
//			}	
//			else if(objectAngle >= 90 && objectAngle <= 180) {
//				setxSpeed(-3);
//			}
			setxSpeed(-3);
		}
		else if(tile.collisionSide == tile.BOTTOM) {
			
			setySpeed(3);				
		}
		else if(tile.collisionSide == tile.RIGHT) {
			
//			if(objectAngle >= 180 && objectAngle <= 270) {
//				setxSpeed(3);
//			}	
//			else if(objectAngle >= 270 && objectAngle <= 360) {
//				setxSpeed(3);
//			}
			setxSpeed(3);
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
