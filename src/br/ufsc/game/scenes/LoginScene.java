package br.ufsc.game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import br.ufsc.game.engine.Game;
import br.ufsc.game.engine.inputs.Keyboard;
import br.ufsc.game.engine.interfaces.Drawable;
import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameImage;
import br.ufsc.game.engine.states.GameScene;

/**
 * LoginScene
 */
public class LoginScene extends GameScene {

    public LoginScene() {
        super();
        
        this.loaded();
    }

    @Override
    public void entering() {

    }

    @Override
    public void exiting() {

    }

    @Override
    public void revealed() {

    }

    @Override
    public void obscuring() {

    }

    @Override
    public void update() {
        gameUpdatables.forEach((uObject) -> uObject.update());
    }

    @Override
    public void draw(Graphics2D g) {
		gameDrawables.forEach((dObject) -> dObject.draw(g));
		g.setColor(Color.WHITE);
		g.drawString("Not Implemented", (int) (Game.getGame().getGameSettings().getWidth()/2),(int) (Game.getGame().getGameSettings().getHeight()/2));
    }

    @Override
    protected void loaded() {
        gameObjects.forEach((key,gObject) -> {
            if(gObject instanceof Drawable) {
            	System.out.println("Added Drawable");
                this.gameDrawables.add((Drawable) gObject);
            }
            
            if(gObject instanceof Updatable) {
                System.out.println("Added Updatable");
                this.gameUpdatables.add((Updatable) gObject);
            }
		});
		
		System.out.println("Not Implemented!");
        
	}

    
}