package br.ufsc.game.engine.logic;

/**
 * GameObject
 */
public class GameObject {

	// Variables
		// Private
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int zIndex;
	// Constructor
	
	public GameObject(int posX,int posY, int width, int height,int zIndex) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.zIndex = zIndex;
	}

	public GameObject(int posX,int posY, int width, int height) {
		this(posX, posY, width, height, 100);
	}

	public GameObject() {
		this(0,0,0,0);
	}
	// Interface
	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public int getZ() {
		return zIndex;
	}

	public void setX(int posX) {
		this.posX = posX;
	}

	public void setY(int posY) {
		this.posY = posY;
	}

	public void setZ(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	// Methods
}