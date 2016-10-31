package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class RodeSteen extends Steen implements ICollidableWithGameObjects {
	
	public RodeSteen(Peddel peddel, BreakOut breakout, int kleur, float x, float y) {
		// TODO Auto-generated constructor stub
		super(peddel, breakout, kleur, x, y);
		levens = 2;		
		setY(y);
		setX(x);
		setWidth(breedte);
		setHeight(hoogte);
	}
}
