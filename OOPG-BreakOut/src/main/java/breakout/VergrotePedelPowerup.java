package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class VergrotePedelPowerup extends GameObject implements IPowerup{
	public BreakOut wereld;
	public int diameter;
	
	public VergrotePedelPowerup(BreakOut wereld, int x, int y){
		this.wereld = wereld;
		setX(x);
		setY(y);
		diameter = 30;
		setHeight(diameter);
        setWidth(diameter); 
	}
	
	@Override
	public void doePowerup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(156,89,189);
		g.rect(getX(), getY(), diameter, diameter);
		
	}

}
