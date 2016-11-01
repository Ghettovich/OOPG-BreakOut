package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;

public class RodeSteen extends Steen implements ICollidableWithGameObjects {
	private BreakOut breakout;
	
	public RodeSteen(Peddel peddel, BreakOut breakout, int kleur, float x, float y) {
		super(peddel, breakout, kleur, x, y);
		this.breakout = breakout;
		levens = 2;		
		setY(y);
		setX(x);
		setWidth(breedte);
		setHeight(hoogte);
	}
	
	@Override
	public void update() {
		if(levens < 1) {
			breakout.deleteGameObject(this);
			peddel.setScore(peddel.getScore() + 5);
			breakout.refreshDasboardText();
		}	
	}
}
