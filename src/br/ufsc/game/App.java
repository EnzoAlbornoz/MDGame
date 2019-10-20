package br.ufsc.game;

import br.ufsc.game.engine.Game;
import br.ufsc.game.engine.config.GameSettings;
import br.ufsc.game.scenes.*;
/**
 * App
 */
public class App {

	public static void main(String[] args) {
		// Enable Better Rendering Performance
		// System.setProperty("sun.java2d.opengl", "true");
		GameSettings gs = new GameSettings("E-NGINE Game",1280,720,60);
		//Game.setupGame(gs).launch(new MenuScene());
		Game.setupGame(gs).launch(new MenuScene());
	}
}