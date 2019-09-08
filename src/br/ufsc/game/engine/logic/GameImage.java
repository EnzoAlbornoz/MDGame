package br.ufsc.game.engine.logic;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.ufsc.game.engine.interfaces.Drawable;

/**
 * GameImage
 */
public class GameImage extends GameObject implements Drawable {

	// Variables
	protected BufferedImage image;
	protected double rotation;
	// Constructor
	public GameImage(String filePath) {
		try {
			loadImage(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Interface
	@Override
	public void draw(Graphics2D g) {
		AffineTransform transform = new AffineTransform();

		transform.rotate(-rotation,this.width/2,this.height/2);
		int newY = (int) (this.posX * Math.sin(rotation) + this.posY * Math.cos(rotation) );
		int newX = (int) (this.posX * Math.cos(rotation) - this.posY * Math.sin(rotation) );

		g.setTransform(transform);

		g.drawImage(this.image,newX,newY,width,height,(ImageObserver) null);
	}
	
	// Methods
	public void loadImage(String filePath) throws IOException {
		// Load Image
		File fileSRC = new File(filePath);
		BufferedImage source = ImageIO.read(fileSRC);
		// Create Optimized Image
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		this.image = gc.createCompatibleImage(source.getWidth(), source.getHeight(), Transparency.BITMASK);
		// Copy Source to Optimized Image
		this.image.getGraphics().clearRect(0, 0, image.getWidth(), image.getHeight());
		this.image.getGraphics().drawImage(source,0,0,null);
		// Set Aux Info
		this.width = source.getWidth();
		this.height = source.getHeight();
	}

	public BufferedImage getImage() {
		return image;
	}
}