package breakout;

import breakout.tile.MurenTile;
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
import processing.core.PApplet;


public class BreakOut extends GameEngine {
	
	private Sound backgroundSound;
	private TextObject eindSchermTekst, beginSchermTekst, dashboardText, uitleg,startTekst;
	private Bal bal;
	private Peddel peddel;	
	private int worldWidth = 900;
	private int worldHeight = 600;
	private int huidigeScherm = 0;

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
        
		initializeTileMap();
	    createObjects();
	    createDashboard(worldWidth, 100);
	    if(huidigeScherm==0){
	    	maakBeginScherm(worldWidth,worldHeight);
	    }
	    else {
	    	initializeSound();
	    }
        createViewWithoutViewport(worldWidth, worldHeight);
	}
 
    /**
     * Creeërt de view zonder viewport
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm 
     */ 
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }
    
    private void initializeSound() {
        backgroundSound = new Sound(this, "src/main/java/breakout/media/jackrabbit.mp3");
        backgroundSound.loop(-1);
    }
	
    private void createObjects() {
    	peddel = new Peddel(this,"Speler", 20,100);
    	this.addGameObject(peddel);
    	    	
    	bal = new Bal(this, peddel);    
    	addGameObject(bal);
    	peddel.setBal(bal);
    	
    	int kleur =0xFFCD5A4A;    	
    	tekenRodeStenen(peddel, 15, kleur, 40, 100);
    	
    	kleur = 0xFFD79B1C;
    	tekenStenen(peddel, 15, kleur, 40, 135);   	
    	
    	kleur = 0xFF4146E9;
    	tekenStenen(peddel, 15, kleur, 40, 170);   	
    	
    	kleur =  0xFFEDE84F;
    	tekenStenen(peddel, 15, kleur, 40, 205);
    	
    	kleur =  0xFFEDE84F;
    	tekenStenen(peddel, 15, kleur, 40, 240);
    	
	}
    
    private void tekenStenen(Peddel peddel, int aantal, int kleur, float startX, float startY) {
    	for(int i = 0; i < aantal; i++) {
			Steen s = new Steen(peddel, this, kleur, startX, startY);			
			startX += 55;  
			addGameObject(s);
    	}    	
    }   
    
    private void tekenRodeStenen(Peddel peddel,int aantal, int kleur, float startX, float startY) {
    	for(int i = 0; i < aantal; i++) {
			Steen s = new RodeSteen(peddel, this, kleur, startX, startY);
			startX += 55;  
			addGameObject(s);
    	}    	
    } 
    
    private void maakBeginScherm(int breedte, int hoogte){
    	Dashboard beginScherm = new Dashboard(175,10,breedte, hoogte);
    	Dashboard uitlegInScherm = new Dashboard(20,125,breedte, hoogte);
    	Dashboard startTekstScherm = new Dashboard(130,135,breedte, hoogte);
    	beginSchermTekst = new TextObject("BreakOut",50);
    	uitleg = new TextObject("Pijltjes naar links en rechts zijn voor het bewegen van de peddel. Pijljte omhoog is om de bal af te vuren",16);
    	startTekst = new TextObject("Klik op het scherm om het spel te starten",20);
    	this.deleteAllDashboards();
    	this.deleteAllGameOBjects();
    	beginScherm.addGameObject(beginSchermTekst);
    	uitlegInScherm.addGameObject(uitleg);
    	startTekstScherm.addGameObject(startTekst);
    	addDashboard(beginScherm);
    	addDashboard(uitlegInScherm);
    	addDashboard(startTekstScherm);
    }

	private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(15,10, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("Player: " + peddel.getNaam() + " | Score = " + peddel.getScore(),50);   
        dashboard.addGameObject(dashboardText);                
        addDashboard(dashboard);
    }
	
    private void maakEindScherm(int breedte, int hoogte){
    	Dashboard eindScherm = new Dashboard(175,125,breedte,hoogte);
    	eindSchermTekst= new TextObject(peddel.getNaam() + ": " + peddel.getScore(),50);
    	this.deleteAllDashboards();
    	this.deleteAllGameOBjects();
    	eindScherm.addGameObject(eindSchermTekst);
    	addDashboard(eindScherm);
    }
	
    private void initializeTileMap() {
        /* TILES */
        Sprite muurSprite = new Sprite("src/main/java/nl/han/ica/waterworld/media/boards.jpg");
        TileType<MurenTile> muurTileType = new TileType<>(MurenTile.class, muurSprite);
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
		if(peddel.getLevens() <1){
			maakEindScherm(worldWidth,worldHeight);
		}
	}
	public void mousePressed(){
		if(huidigeScherm == 0){
			huidigeScherm = 1;
			this.deleteAllDashboards();
 		    this.deleteAllGameOBjects();
 		    setupGame();
		}
	}
    public void refreshDasboardText() {
        dashboardText.setText("Player: " + peddel.getNaam() + " | Score = " + peddel.getScore());
    }
}
