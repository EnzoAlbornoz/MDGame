package br.ufsc.game.engine;

import java.awt.Color;

import br.ufsc.game.engine.config.GameSettings;
import br.ufsc.game.engine.graphics.GameWindow;

/**
 * Game
 */
public class Game {
    // Singleton Pattern
    private static Game singleton;
    public static Game getGame() {
        if(singleton == null) {
            new Game();
        }
        return singleton;
    }
    public static void setGameSettings(GameSettings gs) {
        gSettings = gs;
    } 
    private static GameSettings gSettings;
    // Variables
        // Private
    private GameWindow gWindow;
    private boolean isRunning;
    // Constructor
    public Game() {
        isRunning = false;
        // Build Structs
        gWindow = GameWindow.createGameWindow(gSettings);
        
        // Singleton Pattern
        singleton = this;
    }
    // Interface
    public void launch() {
        // Launch Game
        if(!isRunning) {
            // Run
            isRunning = true;
            while(true){

                gWindow.getGameGraphics().setColor(Color.RED);
                gWindow.getGameGraphics().fillRect(0, 0,100, 100);
                gWindow.render();
            }
            
        }
    }
    // Methods
    private void run() {

    }
}