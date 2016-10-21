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
    	
    	//moet er later weer uit en dan in de klasse steen
    	vergrotePedelPowerup = new VergrotePedelPowerup(this,200,200);
    	this.addGameObject(vergrotePedelPowerup);
    	
    	bal = new Bal(this);    
    	
    	float x = 10;
    	float y = 100;
    	
    	for(int i = 0; i < 10; i++) {
    		
    		Steen s = new Steen(this, 120, x, y);
    		addGameObject(s);    		
    		x += 55;    		
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
