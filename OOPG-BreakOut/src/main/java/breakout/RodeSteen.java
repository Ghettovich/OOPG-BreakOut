package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class RodeSteen extends Steen implements ICollidableWithGameObjects {
	
	private int levens;

	public RodeSteen(BreakOut breakout, int kleur, float x, float y) {
		// TODO Auto-generated constructor stub
		super(breakout, kleur, x, y);
		levens = 2;		
		setY(y);
		setX(x);
		setWidth(breedte);
		setHeight(hoogte);
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

}
