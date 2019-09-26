package br.ufsc.game.engine.inputs;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameObject;

/**
 * Mouse
 */
public class Mouse implements MouseListener, MouseMotionListener, Updatable {
	// Singleton
	private static Mouse singleton;
	public static Mouse getInstance() {
		if (singleton == null) {
			new Mouse();
		}
		return singleton;
	}
	// Variables
		// Private
		private Point mousePos;
		private HashMap<Integer,Boolean> keysNow;
		private HashMap<Integer,Boolean> keysLast;
		// Public
	// Constructor
	private Mouse() {
		// Init
		keysNow = new HashMap<>();
		keysLast = new HashMap<>();
		mousePos = new Point(0,0);
		loadKeys();
		// Singleton
		singleton = this;
	}
	// Interface

	public boolean isMouseButtonPressed(int mouseEventKey) {
		Boolean ret = keysNow.get(mouseEventKey);
		return (ret == null ? false : ret.booleanValue() );
	}

	public boolean isMouseButtonPressing(int mouseEventKey) {
		return (keysNow.get(mouseEventKey).booleanValue() && !keysLast.get(mouseEventKey).booleanValue());
	}

	public boolean isMouseButtonReleased(int mouseEventKey) {
		Boolean ret = keysNow.get(mouseEventKey);
		return (ret == null ? true : !ret.booleanValue() );
	}

	public boolean isMouseButtonReleasing(int mouseEventKey) {
		return (!keysNow.get(mouseEventKey).booleanValue() 
				&& keysLast.get(mouseEventKey).booleanValue());
	}

	public boolean isOverGameObject(GameObject gameObject) {
		return isOverArea(gameObject.getX(), gameObject.getY(), 
				gameObject.getWidth(), gameObject.getHeight());
	}

	public boolean isOverArea(int x, int y, int width, int height) {
		// Stability warranty
		Point mp = mousePos.getLocation();
		// Do calc
		boolean xIntercept = ((mp.x >= x)
				&& (mp.x <= (x + width)));
		boolean yIntercept = ((mp.y >= y)
				&& (mp.y <= (y + height)));
		return (xIntercept && yIntercept);
	}

	public Point getPosition() {
		return mousePos.getLocation();
	}

	@Override
	public void update() {
		keysNow.forEach((key,val)-> {
			keysLast.replace(key, val);
		});
	}
	// Implement Methods
	private void loadKeys() {
		for (Field field : MouseEvent.class.getDeclaredFields()) {
			try {
				
				if (Modifier.isStatic(field.getModifiers()) && 
					field.getType() == int.class && 
					field.getName().matches("^BUTTON\\d$")) {

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

	@Override
	public void mouseDragged(MouseEvent e) {
		// Updates mouse movement
		mousePos.setLocation(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Updates mouse movement
		mousePos.setLocation(e.getPoint());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Do not trigger
	}

	@Override
	public void mousePressed(MouseEvent e) {
		keysNow.replace(e.getButton(),true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		keysNow.replace(e.getButton(),false);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mousePos.setLocation(e.getPoint());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mousePos.setLocation(e.getPoint());
	}

	
}