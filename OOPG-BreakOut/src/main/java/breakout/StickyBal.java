package breakout;

public class StickyBal extends Bal {

	int aantalKeerVasthouden;
	
	
	
	public StickyBal(BreakOut breakout, Peddel peddel) {
		// TODO Auto-generated constructor stub
		super(breakout, peddel);
		aantalKeerVasthouden = 3;		
	}

	public int getAantalKeerVasthouden() {
		return aantalKeerVasthouden;
	}	

	public void setAantalKeerVasthouden(int aantalKeerVasthouden) {
		this.aantalKeerVasthouden = aantalKeerVasthouden;
	}
		
	public boolean powerUpActief() {
		if(aantalKeerVasthouden == 0) {
			return false;
		} return true;
	}
	

}
