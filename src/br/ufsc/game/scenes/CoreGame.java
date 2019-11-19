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

    boolean[] iconSelected = {false,false,false,false,false,false};//melhor sobrar que faltar
    String[] cardPaths = {"blabla","blabla","blabla","blabla","blabla","blabla","blabla","blabla"};

    public CoreGame(NetGamesInterface nGamesInterface) {
        super();

        playerInterface = new PlayerInterface(nGamesInterface, this);
        nGamesInterface.setFSMGame(playerInterface.getFSMGame());

        this.gameExtras.put("ngInterface", nGamesInterface);
        this.gameObjects.put("lastUsedCard", new GameImage("/br/ufsc/game/resources/images/back.png"));
        this.gameObjects.put("endTurn", new GameButton("/br/ufsc/game/resources/images/endTurn.png"));
        for (int i = 0; i < 7; i++) {
            ArrayList<Integer> id = new ArrayList<>();
            id.add(i);
            this.gameObjects.put("card" + i, new GameButton("/br/ufsc/game/resources/images/back.png"));
            ((GameButton) gameObjects.get("card"+i)).setOnClick(new GameAction() {
                @Override
                public void doAction(Object[] args) {
                    playerInterface.useCard(id.get(0));
                }
            });
        }
        for (int i =1; i < 5; i++) {
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
            ArrayList<Integer> id = new ArrayList<>();
            id.add(i);
            this.gameObjects.put("prop" + i,
                new GameButton("/br/ufsc/game/resources/images/prop0" + i + ".png"));
            ((GameButton) gameObjects.get("prop" + i)).setOnClick(new GameAction() {
                @Override
                public void doAction(Object[] args) {
                    playerInterface.selectProperty(id.get(0));
                }
            });
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
            if (selected != iconSelected[i]){
                if (selected) {
                    path = "/br/ufsc/game/resources/images/player" + i + "selected.png";
                } else {
                    path = "/br/ufsc/game/resources/images/player" + i + ".png";
                }
                try { icon.loadImage(path);
                } catch (IOException e) { e.printStackTrace(); }
                iconSelected[i] = selected;
            }
        }

        
        for (int i =0; i < 7; i++) {
            String cardName = playerInterface.witchCardIsThis(i);
            String path = "/br/ufsc/game/resources/images/" + cardName + ".png";
            GameButton card = (GameButton) gameObjects.get("card"+i);
            if (!path.equals(cardPaths[i])){
                log("carta na mao at "+i+": " +path);
                try { card.loadImage(path);
                } catch (IOException e) { e.printStackTrace();}
                cardPaths[i]=path;
            }
        } 
        
        //positioning playerIcon
        x = 1280-177; int y = 25;
        for (int i =1; i < 5; i++) {
            int m$ = playerInterface.getPlayersMoney(i);
            if (m$ > -1){
                gameObjects.get("playerIcon"+i).setX(x);
                gameObjects.get("playerIcon"+i).setY(y);
                y += 100;
            } else {
                gameObjects.get("playerIcon"+i).setX(x+999999); //some com ele
            }
        }

    }

    @Override
    public void draw(Graphics2D g) {
    	int x, y;
		gameDrawables.stream().sorted((d1,d2)->{return ((GameObject)d1).getZ() - ((GameObject)d2).getZ();}).forEach((dObject) -> dObject.draw(g));

        g.setColor(Color.white);
        g.setFont(new Font("ComicSans", Font.BOLD, 27));
        int clientId = playerInterface.getClientID();
        g.drawString("You Are: P"+clientId, 10, 45);
        g.drawString("State: "+ playerInterface.getState(),500,45);
        g.drawString("->", 1050, (playerInterface.getClientIDofPlayer0()-1)*100+75); // +/- 100 to change player
        
        x = 1280-75; y = 85;
        for (int i =1; i < 5; i++) {
            int m$ = playerInterface.getPlayersMoney(i);
            if (m$ > -1){
                g.drawString("$"+m$+"m", x, y);
            }
            y+=100;
        }
        
        int xi = 230;
        x = xi; y = 70;
        for (int i =0; i < 10; i++) {
            int propQty = playerInterface.HowManyPropCards(i);
            int dyi = 35, dy=dyi;
            
            if (propQty > 50){ //50 is added if complete: its like a code to not make other method
                g.drawString("completo", x, y+dy); dy+=dyi;
                propQty -= 50;
            }
        	g.drawString("x"+propQty, x+35, y+dy); dy+=dyi;
        	//g.drawString("casa", x, y+dy); dy+=dyi;
        	//g.drawString("hotel", x, y+dy);
	        x += 150;
	        if(i==4) {
	        	y+=200;
	        	x=xi;
	        	dy=dyi;
	        }
        }
        
    }
    
    public boolean useOrStoreCard(){
        int resposta = JOptionPane.showConfirmDialog(null, "Use Card? If not, it will be sold.");
		return (resposta == JOptionPane.YES_OPTION);
    }

    void log(String s){
        System.out.println(s);
    }
}