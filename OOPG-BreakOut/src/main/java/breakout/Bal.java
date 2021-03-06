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
 * Maakt een bal, gebruikt GameObject voor de functies en implementeerd de collisions om te kijken of de bal een GameObject of een Tile raakt
 * @author Geert Boeve
 */
public class Bal extends GameObject implements ICollidableWithTiles, ICollidableWithGameObjects {
	protected int kleur;
	private BreakOut breakout;
	private int diameter;
	private Peddel peddel;
	private GoudenBalPowerup goudenBal;
	private StickyBalPowerup stickyBal;
	
	/**
	 * Constructor
	 * @param breakout Referentie naar de wereld
	 * @param peddel Referentie naar de peddel
	 */
	public Bal(BreakOut breakout, Peddel peddel) {
		this.diameter = 30;
		this.breakout = breakout;
		this.peddel = peddel;
		this.kleur = 0xFFFFFFFF;
		setY(peddel.getY() - diameter);
		setX(peddel.getX() + diameter);
		setWidth(diameter);
		setHeight(diameter);
	}
	
	@Override
	public void update() {
		if (this.getY() >= breakout.getHeight()) {
			if (peddel.getLevens() < 1) {
				//System.out.println("DOOD");
			} else {
				setY(peddel.getY() - diameter);
				setX(peddel.getX() + diameter);
				setSpeed(0);
				peddel.levenMinder();
			}
		}
		if (goudenBal != null) {
			goudenBal.setTijd(goudenBal.getTijd() + 1);
			if (goudenBal.getTijd() >= 180) {
				setKleur(0xFFFFFFFF);
				setGoudenBal(null);
			}
		}
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER);
		g.fill(kleur);
		g.ellipse(getX(), getY(), diameter, diameter);
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile tile : collidedTiles) {
			vector = breakout.getTileMap().getTilePixelLocation(tile.theTile);
			berekenBounceTile(this.getAngleFrom(((int) vector.x), ((int) vector.y)), tile);
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject o : collidedGameObjects) {
			if (o instanceof Steen) {
				if (getGoudenBal() == null) {
					berekenBounceGameObject(o.getAngleFrom(this));
				}
			}
			if (o instanceof Peddel) {
				if (peddel.getStickyBalActief()) {
					stickyBal.setPeddel(peddel);
					//System.out.println("peddel geraakt, stickybal actief | aantalkeervast = "+ peddel.getStickyBallPowerup().getAantalKeerVasthouden());
				} else {
					berekenBounceGameObject(o.getAngleFrom(this));
				}
			}
		}
	}

	private void berekenBounceGameObject(float objectAngle) {
		if (objectAngle >= 0 && objectAngle < 45) {
			setySpeed(-3);
		} else if (objectAngle >= 45 && objectAngle <= 135) {
			setxSpeed(3);
		} else if (objectAngle >= 135 && objectAngle <= 180) {
			setySpeed(3);
		} else if (objectAngle >= 180 && objectAngle <= 225) {
			setySpeed(3);
		} else if (objectAngle >= 225 && objectAngle <= 270) {
			setxSpeed(-3);
		} else if (objectAngle >= 270 && objectAngle <= 360) {
			setySpeed(-3);
		}
	}

	private void berekenBounceTile(float objectAngle, CollidedTile tile) {
		if (tile.collisionSide == tile.LEFT) {
			setxSpeed(-3);
		} else if (tile.collisionSide == tile.BOTTOM) {
			setySpeed(3);
		} else if (tile.collisionSide == tile.RIGHT) {
			setxSpeed(3);
		}
	}
	
	/**
	 * Zet de kleur van een Bal
	 * @param kleur Referentie naar de kleur van de bal
	 */
	public void setKleur(int kleur) {
		this.kleur = kleur;
	}
	
	/**
	 * @return goudenBal Referentie naar de goudenBal
	 */
	public GoudenBalPowerup getGoudenBal() {
		return goudenBal;
	}
	
	/**
	 * Zet de goudenBal van een Bal
	 * @param goudenBal Referentie naar de goudenBal
	 */
	public void setGoudenBal(GoudenBalPowerup goudenBal) {
		this.goudenBal = goudenBal;
	}

	/**
	 * Zet de stickyBal van een Bal
	 * @param stickyBal Referentie naar de stickyBal
	 */
	public void setStickyBal(StickyBalPowerup stickyBal) {
		this.stickyBal = stickyBal;
	}
}