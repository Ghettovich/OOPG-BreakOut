package breakout;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Steen extends GameObject implements ICollidableWithGameObjects {
	
	protected int breedte, hoogte, kleur;
	protected int levens;	
	private BreakOut breakout;
	protected Peddel peddel;

	public Steen(Peddel peddel, BreakOut breakout, int kleur, float x, float y) {
		// TODO Auto-generated constructor stub
		this.breedte = 50;
		this.hoogte = 30;
		this.kleur = kleur;
		this.breakout = breakout;		
		this.levens = 1;
		this.peddel = peddel;
		setY(y);
		setX(x);
		setWidth(breedte);
		setHeight(hoogte);
	}

	public int getBreedte() {
		return breedte;
	}

	public void setBreedte(int breedte) {
		this.breedte = breedte;
	}

	public int getHoogte() {
		return hoogte;
	}

	public void setHoogte(int hoogte) {
		this.hoogte = hoogte;
	}

	public int getLevens() {
		return levens;
	}

	public void setLevens(int levens) {
		this.levens = levens;
	}
	
	public void minLeven() {
		levens --;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		for(GameObject o : collidedGameObjects) {			
			if(o instanceof Bal) {
				spawnRandomPowerUp((Bal)o);	
				minLeven();				
			}
		}
	}
	
	public void spawnRandomPowerUp(Bal bal) {
		Random r = new Random();
		int rGetal = 0;//r.nextInt(15);
		
		if(rGetal == 1) {
			StickyBalPowerup stickyBal = new StickyBalPowerup(breakout, bal, getX(), getY());					
			stickyBal.setAantalKeerVasthouden(2);			
			breakout.addGameObject(stickyBal);
		}
		else if(rGetal == 2) {
			GoudenBalPowerup goudenBal = new GoudenBalPowerup(breakout, bal, bal.getX(), bal.getY());					
			breakout.addGameObject(goudenBal);			
		}
		else if(rGetal == 3) {
			VergrotePedelPowerup vergrotePeddel = new VergrotePedelPowerup(peddel, breakout, getX(), getY());
			breakout.addGameObject(vergrotePeddel);
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(levens < 1) {			
			breakout.deleteGameObject(this);
		}	
	}

	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
		g.fill(kleur);
		g.rect(getX(), getY(), breedte, hoogte);	
		
	}

}
