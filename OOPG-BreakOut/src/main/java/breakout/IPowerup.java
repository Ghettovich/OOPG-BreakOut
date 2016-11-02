package breakout;
/**
 * Interface voor de powerups
 * @author Bram Bergervoet
 */
public interface IPowerup {
	
	/**
	 * De Powerup wordt uitgevoerd
	 */
	public void doePowerup();
	
	/**
	 * De powerup wordt uitgevoerd
	 * @param bal Referentie naar de bal die de powerup moet uitvoeren
	 * @param peddel Referentie naar de peddel die de powerup moet uitvoeren
	 */
	public void doePowerup(Bal bal, Peddel peddel);
}
