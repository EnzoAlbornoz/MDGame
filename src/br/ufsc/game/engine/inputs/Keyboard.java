package br.ufsc.game.engine.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import br.ufsc.game.engine.interfaces.Updatable;

/**
 * Keyboard
 */
public class Keyboard implements KeyListener,Updatable {
	// Singleton
	private static Keyboard singleton;
	public static Keyboard getInstance() {
		if (singleton == null) {
			new Keyboard();
		}
		return singleton;
	}
	// Variables
		// Privates
	private HashMap<Integer,Boolean> keysNow;
	private HashMap<Integer,Boolean> keysLast;
	// Constructor
	private Keyboard() {
		// Init
		keysNow = new HashMap<>();
		keysLast = new HashMap<>();
		loadKeys();
		// Singleton
		singleton = this;
	}
	// Interface
	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysNow.computeIfPresent(e.getKeyCode(), (key,val) -> true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysNow.computeIfPresent(e.getKeyCode(), (key,val) -> false);
	}

	public boolean isKeyPressed(int keyEventKey) {
		return (keysNow.get(keyEventKey));
	}

	public boolean isKeyPressing(int keyEventKey) {
		if(Boolean.logicalAnd(keysNow.get(keyEventKey), !(keysLast.get(keyEventKey).booleanValue()))) {
			System.out.println("Button '" + KeyEvent.getKeyText(keyEventKey) + "' pressed");
		}
		return (keysNow.get(keyEventKey).booleanValue() && !keysLast.get(keyEventKey).booleanValue());
	}

	public boolean isKeyReleased(int keyEventKey) {
		return (!keysNow.get(keyEventKey).booleanValue());
	}

	public boolean isKeyReleasing(int keyEventKey) {
		return (!keysNow.get(keyEventKey).booleanValue() && keysLast.get(keyEventKey).booleanValue());
	}

	public void update() {
		keysNow.forEach((key,val)-> {
			keysLast.replace(key, val);
		});
	}

	// Methods
	private void loadKeys() {
		for (Field field : KeyEvent.class.getDeclaredFields()) {
			try {
				
				if (Modifier.isStatic(field.getModifiers()) && 
					field.getType() == int.class && 
					field.getName().startsWith("VK")) {

					// Key constant found !
					field.setAccessible(true);
					keysNow.put((int) field.get(null), false);
					keysLast.put((int) field.get(null), false);
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}