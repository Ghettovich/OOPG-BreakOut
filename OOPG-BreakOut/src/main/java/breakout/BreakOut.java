package breakout;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.TextObject;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PApplet;


public class BreakOut extends GameEngine {
	
	private TextObject dashboardText;
	private Bal bal;
	private Peddel peddel;
	//moet er later weer uit
	private VergrotePedelPowerup vergrotePedelPowerup;
	private StickyBalPowerup stickyBalPowerup;
	
	private int worldWidth = 900;
	private int worldHeight = 600;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(new String[]{"breakout.BreakOut"});
	}

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
	@Override
	public void setupGame() {
		// TODO Auto-generated method stub
        
        //initializeSound();
        createDashboard(worldWidth, 100);        
        initializeTileMap();
        //initializePersistence();

        createObjects();
        createViewWithoutViewport(worldWidth, worldHeight);
        //createViewWithViewport(worldWidth, worldHeight, 800, 800, 1.1f);
		
	}
 
    /**
     * Creeërt de view zonder viewport
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm 
     */ 
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        //view.setBackground(loadImage("src/main/java/breakout/media/Penguins.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }
	
    private void createObjects() {
		// TODO Auto-generated method stub 
    	peddel = new Peddel(this,"Bram", 20,100);
    	this.addGameObject(peddel);
    	    	
    	bal = new Bal(this, peddel);    
    	addGameObject(bal);
    	peddel.setBal(bal);
    	
    	System.out.println("peddel y = " + peddel.getY() + " y peddel + hoogte = " +  peddel.getHeight());
    	System.out.println("peddel x = " + peddel.getX() + " x peddel + breedte = " + (peddel.getX() + peddel.getBreedte()));
    	
    	int kleur =0xFFCD5A4A;
    	
    	tekenRodeStenen(15, kleur, 40, 100);
    	
    	kleur = 0xFFD79B1C;
    	//tekenStenen(15, kleur, 40, 135);   	
    	
    	kleur = 0xFF4146E9;
    	tekenStenen(15, kleur, 40, 170);   	
    	
    	kleur =  0xFFEDE84F;
    	//tekenStenen(15, kleur, 40, 205);
    	
    	kleur =  0xFFEDE84F;
    	//tekenStenen(15, kleur, 40, 240);
    	
	}
    
    private void tekenStenen(int aantal, int kleur, float startX, float startY) {
    	
    	for(int i = 0; i < aantal; i++) {
		
			Steen s = new Steen(this, kleur, startX, startY);	
			startX += 55;  
			addGameObject(s);
    	}    	
    }   
    
    private void tekenRodeStenen(int aantal, int kleur, float startX, float startY) {
    	
    	for(int i = 0; i < aantal; i++) {
		
			RodeSteen s = new RodeSteen(this, kleur, startX, startY);	
			startX += 55;  
			addGameObject(s);
    	}    	
    } 

	private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("Pinguins enzo");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }
	
    private void initializeTileMap() {
        /* TILES */
        Sprite muurSprite = new Sprite("src/main/java/nl/han/ica/waterworld/media/boards.jpg");
        TileType<BoardsTile> muurTileType = new TileType<>(BoardsTile.class, muurSprite);

        TileType[] tileTypes = { muurTileType };
        int tileSize=30;
        int tilesMap[][]={
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0},
                { 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
