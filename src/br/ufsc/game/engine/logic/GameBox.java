package br.ufsc.game.engine.logic;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.interfaces.Drawable;

/**
 * GameBox
 */
public class GameBox extends GameObject implements Updatable, Drawable {

	// Variables
	// Private
	protected HashMap<String, GameObject> gMap;
	// Constructor

	public GameBox(int posX, int posY, int zIndex) {
		super(posX, posY, 0, 0, zIndex);
		gMap = new HashMap<>();
	}

	public GameBox(int posX, int posY) {
		this(posX, posY, 100);
	}

	public GameBox() {
		this(0, 0);
	}

	// Interface
	public void add(String key, GameObject gameObject) {
		gMap.putIfAbsent(key, gameObject);
		this.updateBounds();
	}

	// Methods
	private void updateBounds() {
		ArrayList<Integer> dim = new ArrayList<>(2);
		dim.add(this.width);
		dim.add(this.height);
		gMap.forEach((k,go) -> {
			Integer maxWidth = dim.get(0);
			Integer maxHeight = dim.get(1);
			if (maxWidth < go.getX() + go.getWidth()) {
				maxWidth = go.getX() + go.getWidth();
				dim.set(0, maxWidth);
			}
			if (maxHeight < go.getY() + go.getHeight()) {
				maxHeight = go.getY() + go.getHeight();
				dim.set(1, maxHeight);
			}
		});
	}

	@Override
	public void update() {
		gMap.forEach((k,go) -> {
			if( go instanceof Updatable) {
				((Updatable) go).update();
			}
		});
	}

	@Override
	public void draw(Graphics2D g) {
		gMap.entrySet().stream().sorted((etrA,etrB)->{
			return Integer.compare(
				etrA.getValue().getZ(),
				etrB.getValue().getZ());
		}).forEach((entry)->{
			if (entry.getValue() instanceof Drawable) {
				((Drawable) entry.getValue()).draw(g);
			}
		});
	}

	// private void 
}