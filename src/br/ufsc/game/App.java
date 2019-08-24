package br.ufsc.game;

import br.ufsc.game.engine.Game;
import br.ufsc.game.engine.config.GameSettings;

/**
 * App
 */
public class App {

	public static void main(String[] args) {
		// Enable Better Rendering Performance
		System.setProperty("sun.java2d.opengl", "true");
		GameSettings gs = new GameSettings("E-NGINE Game",1280,720,60);
		Game.setGameSettings(gs);
		Game.getGame().launch();
	}
}