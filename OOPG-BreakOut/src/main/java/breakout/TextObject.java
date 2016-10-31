package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;
    private int textSize;

    public TextObject(String text, int textSize) {
        this.text=text;
        this.textSize = textSize;
    }

    public void setText(String text) {
        this.text=text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(textSize);
        g.text(text,getX(),getY());
    }
}