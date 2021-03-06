package br.ufsc.game.engine;


import br.ufsc.game.engine.config.GameSettings;
import br.ufsc.game.engine.graphics.GameWindow;
import br.ufsc.game.engine.inputs.Keyboard;
import br.ufsc.game.engine.inputs.Mouse;
import br.ufsc.game.engine.states.GameScene;
import br.ufsc.game.engine.states.GameSceneManager;

/**
 * Game
 */
public class Game {
	// Singleton Pattern
	private static Game singleton;
	public static Game getGame() {
		return singleton;
	}
	public static Game setupGame(GameSettings gSettings) {
		return new Game(gSettings);
	}
	private GameSettings gSettings;
	// Variables
	// Private
	private GameWindow gWindow;
	private Keyboard gKeyboard;
	private Mouse gMouse;
	private GameSceneManager gStateManager;
	private boolean isRunning;
	// Constructor
	private Game(GameSettings gSettings) {
		this.gSettings = gSettings;
		this.isRunning = false;
		// Build Structs
		this.gWindow = GameWindow.createGameWindow(gSettings);
		this.gKeyboard = Keyboard.getInstance();
		this.gMouse = Mouse.getInstance();
		this.gStateManager = GameSceneManager.getInstance();
		// Singleton Pattern
		singleton = this;
	}
	// Interface
	public void setGameSettings(GameSettings gs) {
		gSettings = gs;
	}
	public GameSettings getGameSettings() {
		return gSettings;
	} 
	public void launch(GameScene initialState) {
		// Launch Game
		if(!this.isRunning) {
			this.isRunning = true;
			this.gStateManager.pushState(initialState);
			this.run();
		}
	}
	// Methods
	private void run() {
		// FPS Lock
		final boolean FPS_LOCK = true;
		boolean do_render = false;
			// Build Based on Minecraft Loop
		// Pre-Allocate Variable
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		// Log Variables
		int frames = 0 ;
		int updates = 0;
		// Main Loop
		while (isRunning) {
			do_render = false;

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				gStateManager.peekState().update();
				gKeyboard.update();
				gMouse.update();
				delta--;
				updates++;
				if(FPS_LOCK) {
					do_render = true;
				}
			}

			if(isRunning && ((FPS_LOCK && do_render) || !FPS_LOCK)) {
				gStateManager.peekState().draw(gWindow.getGameGraphics());
				gWindow.render();
				frames++;
			}

			
			/*
			// Log FPS
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.printf("FPS: %d | UPS: %d\n",frames,updates);
				frames = 0;
				updates = 0;
			}
			*/
		}
	}
}