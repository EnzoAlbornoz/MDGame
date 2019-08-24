package br.ufsc.game.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import br.ufsc.game.engine.config.GameSettings;

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
		super(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		this.gSettings = gs;
		// Configure Window
		// setIgnoreRepaint(true);
		setTitle(gs.getTitle());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
		setResizable(false);
		// Configure Content
		this.canvas = new Canvas();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(canvas);
		canvas.setSize((int) gs.getWidth(), (int) gs.getHeight());
		
		this.pack();
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
	}

	public GraphicsConfiguration getGraphicsConfiguration() {
		return graphicsConfig;
	}
	// Methods
	
	
}