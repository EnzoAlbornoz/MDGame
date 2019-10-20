package br.ufsc.game.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import br.ufsc.game.engine.config.GameSettings;
import br.ufsc.game.engine.inputs.Keyboard;
import br.ufsc.game.engine.inputs.Mouse;

/**
 * GameWindow
 */
public class GameWindow extends JFrame {
	// Singleton Pattern
	private static GameWindow singleton;
	public static GameWindow getInstance() {
		return singleton;
	}
	public static GameWindow createGameWindow(GameSettings gs) {
		return new GameWindow(gs);
	}
	// Variables
		// Private
			// Config
	private GameSettings gSettings;
			// Graphics
	private Canvas canvas;
	private Graphics2D graphics;
	private BufferStrategy buffer;
	private GraphicsConfiguration graphicsConfig;
	
	// Constructor
	private GameWindow(GameSettings gs) {
		this.gSettings = gs;
		// Configure Window
		setIgnoreRepaint(true);
		setTitle(gs.getTitle());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
		// Add Listeners
		// Configure Content
		this.canvas = new Canvas();
		this.canvas.setSize((int) gs.getWidth(), (int) gs.getHeight());
		this.add(canvas);
		this.pack();
		this.setResizable(false);
		// Add listeners
			// Keyboard
		this.canvas.addKeyListener(Keyboard.getInstance());
		addKeyListener(Keyboard.getInstance());
			// Mouse
				// Mouse Keys
		this.canvas.addMouseListener(Mouse.getInstance());
		addMouseListener(Mouse.getInstance());
				// Mouse Motion
		this.canvas.addMouseMotionListener(Mouse.getInstance());
		addMouseMotionListener(Mouse.getInstance());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		// Configure Graphics
		this.canvas.createBufferStrategy(2);
		this.buffer = canvas.getBufferStrategy();
		this.graphics = (Graphics2D) buffer.getDrawGraphics();
		this.graphicsConfig = canvas.getGraphicsConfiguration();

		// Singleton
		singleton = this;
	}
	// Interface
	public Graphics2D getGameGraphics() {
		return graphics;
	}

	public void clear(Color c) {
		graphics.setColor(c);
		graphics.fillRect(0, 0, (int) gSettings.getWidth(), (int) gSettings.getHeight());
	}

	public void render() {
		// Show on Screen
		graphics.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
		this.graphics = (Graphics2D) buffer.getDrawGraphics();
		clear(Color.BLACK);
	}

	public GraphicsConfiguration getGraphicsConfig() {
		return graphicsConfig;
	}
	// Methods
	
	
}