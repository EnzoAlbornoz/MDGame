package br.ufsc.game.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import br.ufsc.game.engine.config.GameSettings;

/**
 * GameWindow
 */
public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
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
	
	// Constructor
	private GameWindow(GameSettings gs) {
		this.gSettings = gs;
		// Configure Window
		setTitle(gs.getTitle());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
		setResizable(false);
		// Configure Content
		this.canvas = new Canvas();
		canvas.setSize((int) gs.getWidth(), (int) gs.getHeight());
		add(canvas);
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
		// Configure Graphics
		this.canvas.createBufferStrategy(2);
		this.buffer = canvas.getBufferStrategy();
		this.graphics = (Graphics2D) buffer.getDrawGraphics();

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
	// Methods
	
	
}