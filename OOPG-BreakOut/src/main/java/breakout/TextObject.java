package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Bram Bergervoet
 * Om een tekst af te beelden op het scherm
 */
public class TextObject extends GameObject {
	private String text;
	private int textSize;

	/**
	 * Constructor
	 * @param text De tekst die afgebeeld moet worden
	 * @param textSize De grote van de tekst
	 */
	public TextObject(String text, int textSize) {
		this.text = text;
		this.textSize = textSize;
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
		g.textAlign(g.LEFT, g.TOP);
		g.textSize(textSize);
		g.text(text, getX(), getY());
	}

	/**
	 * Zet de tekst naar een andere tekst
	 * @param text De tekst die afgebeeld moet worden
	 */
	public void setText(String text) {
		this.text = text;
	}
}