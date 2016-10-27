package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Steen extends GameObject implements ICollidableWithGameObjects {
	
	private int breedte, hoogte, kleur;
	protected int levens;
	private BreakOut breakout;

	public Steen(BreakOut breakout, int kleur, float x, float y) {
		// TODO Auto-generated constructor stub
		this.breedte = 50;
		this.hoogte = 30;
		this.kleur = kleur;
		this.breakout = breakout;
		
		this.levens = 1;
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
	
	

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
		//g.stroke(255, 255, 0);
		g.fill(kleur);
		g.rect(getX(), getY(), breedte, hoogte);	
		
	}

}
