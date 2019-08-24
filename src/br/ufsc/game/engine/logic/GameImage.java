package br.ufsc.game.engine.logic;

import java.awt.Graphics2D;

import br.ufsc.game.engine.interfaces.Drawable;

/**
 * GameImage
 */
public class GameImage extends GameObject implements Drawable {

	// Variable
	// Constructor
	public GameImage() {
		super(posX, posY, width, height)
	}

	// Interface
	@Override
	public void render(Graphics2D g) {
		
	}
	
	// Methods
}