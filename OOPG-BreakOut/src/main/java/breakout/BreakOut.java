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
	
	private int worldWidth=1000;
    private int worldHeight=768;
	
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
    	
    	bal = new Bal();
    	this.addGameObject(bal);
    	peddel = new Peddel("Bram", 20,100);
    	this.addGameObject(peddel);
    	
		System.out.println("sup with it");
	}

	private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }
	
    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/waterworld/media/boards.jpg");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);

        TileType[] tileTypes = { boardTileType };
        int tileSize=50;
        int tilesMap[][]={
                {0,-1,0,0,0,0,0,0,0,0},
                {-1,-1,0,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,0,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,0,-1},
                {-1,-1,-1, 0, 0, 0, 0,-1,0 , 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
