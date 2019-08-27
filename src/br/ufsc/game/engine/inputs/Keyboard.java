package br.ufsc.game.engine.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard
 */
public class Keyboard implements KeyListener {
	public enum KeyStates {
		RELEASED,
		PRESSING,
		PRESSED,
		RELEASING
	}
	// Variables
		// Privates
	private KeyStates[] kStates;
	// Constructor
	public Keyboard() {
		
	}
	// Interface
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	public boolean isKeyPressed(KeyEvent e) {
		return false;
	}
	// Methods
}