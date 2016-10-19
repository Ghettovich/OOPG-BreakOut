package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class RodeSteen extends Steen implements ICollidableWithGameObjects {

	public RodeSteen(int kleur, float x, float y) {
		// TODO Auto-generated constructor stub
		super(kleur, x, y);
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

}
