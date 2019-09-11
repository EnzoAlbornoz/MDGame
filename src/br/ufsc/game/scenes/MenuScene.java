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
import br.ufsc.game.engine.states.GameSceneManager;

/**
 * MenuScene
 */
public class MenuScene extends GameScene {

    public MenuScene() {
        super();
        this.gameObjects.put("backgroundImage", new GameImage("/br/ufsc/game/resources/images/BlackBackgroundFelt.jpg"));
        this.gameObjects.put("logo", new GameImage("/br/ufsc/game/resources/images/Logo.png"));
        // this.gameObjects.put("playBtn",new GameButton("src/resources/images/ButtonPlay.png"));
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
        if(Keyboard.getInstance().isKeyPressing(KeyEvent.VK_ENTER)) {
            System.out.println("Moving Scene");
            GameSceneManager.getInstance().pushState(new LoginScene());
        }
        gameUpdatables.forEach((uObject) -> uObject.update());
    }

    @Override
    public void draw(Graphics2D g) {
        gameDrawables.forEach((dObject) -> dObject.draw(g));
        g.setColor(Color.WHITE);
        g.drawString("Press Enter to Play",(int) (Game.getGame().getGameSettings().getWidth()/2), (int) (Game.getGame().getGameSettings().getHeight()/2));
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
        
    	gameObjects.get("logo").setX((int) (Game.getGame().getGameSettings().getWidth()/2) - (gameObjects.get("logo").getWidth()     /2));
        gameObjects.get("logo").setY(100);
        // gameObjects.get("playBtn").setX((int) (Game.getGame().getGameSettings().getWidth()/2) - (gameObjects.get("playBtn").getWidth()  /2));
    	// gameObjects.get("playBtn").setY((int) (Game.getGame().getGameSettings().getWidth()/2) - (gameObjects.get("playBtn").getHeight() /2));
    	
	}

    
}