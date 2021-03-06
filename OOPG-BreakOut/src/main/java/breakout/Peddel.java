package breakout;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * De peddel die de speler bestuurd
 * @author Bram Bergervoet, Geert Boeve
 */
public class Peddel extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects, IKeyInput {
	private BreakOut wereld;
	private Bal bal;
	private StickyBalPowerup stickyBal;
	private String naam;
	private int score, levens, hoogte, breedte;
	private int tijd;
	private boolean stickyBalActief;

	/**
	 * Constructor
	 * @param wereld Referentie naar de wereld
	 * @param naam De naam van de peddel (speler)
	 * @param hoogte De hoogte van de peddel
	 * @param breedte De breedte van de peddel
	 */
	Peddel(BreakOut wereld, String naam, int hoogte, int breedte) {
		this.wereld = wereld;
		this.naam = naam;
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.stickyBalActief = false;
		score = 0;
		levens = 3;
		setX(wereld.getTileMap().getMapWidth() / 2 - breedte / 2);
		setY(wereld.getTileMap().getMapHeight() - 50);
		setFriction(0.1f);
		setHeight(hoogte);
		setWidth(breedte);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject collide : collidedGameObjects) {
			if (collide instanceof IPowerup) {
				if (collide instanceof StickyBalPowerup) {
					((StickyBalPowerup) collide).doePowerup(bal, this);
				} else {
					((IPowerup) collide).doePowerup();
				}
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile collide : collidedTiles) {
			setxSpeed(0);
			if (collide.collisionSide == collide.RIGHT) {
				vector = wereld.getTileMap().getTilePixelLocation(collide.theTile);
				setX(vector.x + wereld.getTileMap().getTileSize());
			} else if (collide.collisionSide == collide.LEFT) {
				vector = wereld.getTileMap().getTilePixelLocation(collide.theTile);
				setX(vector.x - getWidth());
			}
		}
	}

	@Override
	public void update() {
		if (getWidth() == 200) {
			tijd++;
		}
		if (tijd >= 600) {
			setWidth(100);
			tijd = 0;
		}
		if (balOpPeddel()) {
			bal.setX(getCenterX());
			if (getStickyBalActief()) {
				bal.setxSpeed(0);
				bal.setySpeed(0);
			}
			// System.out.println("bal op peddel | bal y + dia = " + (bal.getY() + bal.getDiameter()) + " | peddel y = " + getY());
		}
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255);
		g.rect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		final int speed = 10;
		if (keyCode == wereld.LEFT) {
			setDirectionSpeed(270, speed);
		}
		if (keyCode == wereld.RIGHT) {
			setDirectionSpeed(90, speed);
		}
		if (keyCode == wereld.UP) {
			//System.out.println("up pressed");
			if ((bal.getxSpeed() == 0 && bal.getySpeed() == 0)) {
				//System.out.println("speed = 0 set speed");
				bal.setY(bal.getY() - 5);
				bal.setySpeed(-3);
				bal.setxSpeed(-1);
				if (getStickyBalActief()) {
					stickyBal.usePowerUp();
				}
			}
		}
	}

	/**
	 * Er wordt gekeken of de bal op de peddel is
	 * @return true of false  
	 */
	public boolean balOpPeddel() {
		// System.out.println("bal op peddel | bal y + dia = " + (bal.getY() + bal.getDiameter()) + " | peddel y = " + getY());
		if (bal.getY() + bal.getHeight() >= getY()
				&& (bal.getCenterX() >= getX() && bal.getCenterX() <= (getX() + getBreedte()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return score De score van de peddel
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Zet de score van de peddel
	 * @param score De score van de peddel
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return levens De levens van de peddel
	 */
	public int getLevens() {
		return levens;
	}

	/**
	 * Zet de levens van de peddel
	 * @param levens De levens van de peddel
	 */
	public void setLevens(int levens) {
		this.levens = levens;
	}

	/**
	 * @return naam De naam van de peddel
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * Verlaagt de levens van de speler met 1
	 */
	public void levenMinder() {
		levens--;
	}

	/**
	 * @return stickyBalActief Of de powerup stickyBal actief is
	 */
	public boolean getStickyBalActief() {
		return stickyBalActief;
	}

	/**
	 * Zet stickyBalActief op true of false
	 * @param stickyBalActief Of de powerup stickyBal actief is
	 */
	public void setStickyBalActief(boolean stickyBalActief) {
		this.stickyBalActief = stickyBalActief;
	}

	/**
	 * Zet de bal van de peddel
	 * @param bal Referentie naar de bal
	 */
	public void setBal(Bal bal) {
		this.bal = bal;
	}

	/**
	 * @return breedte De breedte van de peddel
	 */
	public int getBreedte() {
		return breedte;
	}

	/**
	 * Zet de breedte van de peddel
	 * @param breedte De breedte van de peddel
	 */
	public void setBreedte(int breedte) {
		this.breedte = breedte;
	}

	/**
	 * Zet de stickyBal van de peddel
	 * @param stickyBal De stickyBal van de peddel
	 */
	public void setStickyBal(StickyBalPowerup stickyBal) {
		this.stickyBal = stickyBal;
	}
}
