package br.ufsc.game.engine.states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import br.ufsc.game.engine.interfaces.Drawable;
import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameObject;

public abstract class GameScene {

	// Variables
		// Public
		// Protected
		protected HashMap<String,GameObject> gameObjects;
		protected ArrayList<Drawable> gameDrawables;
		protected ArrayList<Updatable> gameUpdatables;

	// Constructor
	protected GameScene() {
		this.gameObjects = new HashMap<>();
		this.gameDrawables = new ArrayList<>();
		this.gameUpdatables = new ArrayList<>();
	}

	// Class Interface
	public abstract void entering();
	
	public abstract void exiting();
	
	public abstract void revealed();
	
	public abstract void obscuring();
	
	public abstract void update();
	
	public abstract void draw(Graphics2D g);
	// Methods
	protected abstract void loaded();
}
