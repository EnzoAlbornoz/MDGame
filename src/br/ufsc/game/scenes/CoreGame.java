package br.ufsc.game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.ufsc.game.engine.interfaces.Drawable;
import br.ufsc.game.engine.interfaces.GameAction;
import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameButton;
import br.ufsc.game.engine.logic.GameImage;
import br.ufsc.game.engine.logic.GameObject;
import br.ufsc.game.engine.states.GameScene;
import br.ufsc.game.gamelogic.PlayerInterface;
import br.ufsc.game.network.NetGamesInterface;

/**
 * MenuScene
 */
public class CoreGame extends GameScene {

    public CoreGame(NetGamesInterface nGamesInterface) {
        super();

        playerInterface = new PlayerInterface(nGamesInterface);
        nGamesInterface.setFSMGame(playerInterface.getFSMGame());

        this.gameExtras.put("ngInterface", nGamesInterface);
        this.gameObjects.put("lastUsedCard", new GameImage("/br/ufsc/game/resources/images/house.png"));
        this.gameObjects.put("endTurn", new GameButton("/br/ufsc/game/resources/images/endTurn.png"));
        for (int i = 0; i < 7; i++) {
            this.gameObjects.put("card" + i, new GameButton("/br/ufsc/game/resources/images/house.png"));
        }
        for (int i = 1; i < 5; i++) {
            ArrayList<Integer> id = new ArrayList<>();
            id.add(i);
            this.gameObjects.put("playerIcon" + i,
                    new GameButton("/br/ufsc/game/resources/images/player" + i + ".png"));
            ((GameButton) gameObjects.get("playerIcon" + i)).setOnClick(new GameAction() {
                @Override
                public void doAction(Object[] args) {
                    playerInterface.setSelectedPlayer(id.get(0));
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            this.gameObjects.put("prop" + i, new GameButton("/br/ufsc/game/resources/images/prop0" + i + ".png"));
        }

        this.loaded();
    }

    // atributoooooooo
    PlayerInterface playerInterface;

    @Override
    public void entering() {
        JOptionPane.showMessageDialog(null, "Jogo Iniciado");
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
    protected void loaded() {
        gameObjects.forEach((key,gObject) -> {
            if(gObject instanceof Drawable) {
            	//System.out.println("Added Drawable");
                this.gameDrawables.add((Drawable) gObject);
            }
            
            if(gObject instanceof Updatable) {
                //System.out.println("Added Updatable");
                this.gameUpdatables.add((Updatable) gObject);
            }
        });
        int x, y;
        //positioning last used card
        x = 10; y = 70;
        gameObjects.get("lastUsedCard").setX(x);
        gameObjects.get("lastUsedCard").setY(y);
        
        //positioning endTurnBtn
        x = 10; y = 356;
        gameObjects.get("endTurn").setX(x);
        gameObjects.get("endTurn").setY(y);
        
        //positioning cards
        x = 10; y = 440;
        for (int i =0; i < 7; i++) {
	        gameObjects.get("card"+i).setX(x);
	        gameObjects.get("card"+i).setY(y);
	        x += 180;
        }
        //positioning playerIcon
        x = 1280-177; y = 25;
        for (int i = 1; i < 5; i++) {
        	gameObjects.get("playerIcon"+i).setX(x);
	        gameObjects.get("playerIcon"+i).setY(y);
	        y += 100;
        }
        
        //positioning playerZones
        int xi = 230;
        x = xi; y = 70;
        for (int i =0; i < 10; i++) {
	        gameObjects.get("prop"+i).setX(x);
	        gameObjects.get("prop"+i).setY(y);
	        x += 150;
	        if(i==4) {
	        	y+=200;
	        	x=xi;
	        }
        }

        //defining endTurnBtn Action
        ((GameButton) gameObjects.get("endTurn")).setOnClick(new GameAction() {
            @Override
            public void doAction(Object[] args) {
                playerInterface.endTurn();
            }
        });
        
	}

    @Override
    public void update() {
        gameUpdatables.forEach((uObject) -> uObject.update());

        // endTurnBtn
        boolean appear = playerInterface.doesEndTurnBtnAppear();
        int x = appear ? 10 : 99999999;
        gameObjects.get("endTurn").setX(x);

        // playerImg icons
        
        for (int i = 1; i < 5; i++) {
            GameButton icon = (GameButton) gameObjects.get("playerIcon"+i);
            boolean selected = playerInterface.isIconSelected(i);
            String path;
            if (selected) {
                path = "/br/ufsc/game/resources/images/player" + i + "selected.png";
            } else {
                path = "/br/ufsc/game/resources/images/player" + i + ".png";
            }
            try { icon.loadImage(path);
            } catch (IOException e) { e.printStackTrace(); }
        }
        
    }

    @Override
    public void draw(Graphics2D g) {
    	int x, y;
		gameDrawables.stream().sorted((d1,d2)->{return ((GameObject)d1).getZ() - ((GameObject)d2).getZ();}).forEach((dObject) -> dObject.draw(g));

        g.setColor(Color.white);
        g.setFont(new Font("ComicSans", Font.BOLD, 27));
        g.drawString("You Are: P2", 10, 45);
        g.drawString("->", 1050, (playerInterface.getClientIDofPlayer0()-1)*100+75); // +/- 100 to change player
        
        x = 1280-75; y = 85;
        for (int i =0; i < 4; i++) {
        	g.drawString("$0m", x, y);
        y+=100;
        }
        
        int xi = 230;
        x = xi; y = 70;
        for (int i =0; i < 10; i++) {
	        //gameObjects.get("prop"+i).setX(x);
	        //gameObjects.get("prop"+i).setY(y);
        	int dyi = 35, dy=dyi;
        	g.drawString("x3", x+35, y+dy); dy+=dyi;
        	g.drawString("completo", x, y+dy); dy+=dyi;
        	g.drawString("casa", x, y+dy); dy+=dyi;
        	g.drawString("hotel", x, y+dy);
	        x += 150;
	        if(i==4) {
	        	y+=200;
	        	x=xi;
	        	dy=dyi;
	        }
        }
        
    }
    
}