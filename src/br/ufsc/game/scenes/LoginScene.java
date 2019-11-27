package br.ufsc.game.scenes;

import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JOptionPane;

import br.ufsc.game.engine.Game;
import br.ufsc.game.engine.inputs.Mouse;
import br.ufsc.game.engine.interfaces.Drawable;
import br.ufsc.game.engine.interfaces.GameAction;
import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameButton;
import br.ufsc.game.engine.logic.GameImage;
import br.ufsc.game.engine.logic.GameObject;
import br.ufsc.game.engine.states.GameScene;
import br.ufsc.game.engine.states.GameSceneManager;
import br.ufsc.game.network.NetGamesInterface;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;

/**
 * LoginScene
 */
public class LoginScene extends GameScene {


    public LoginScene() {
        super();
        
        this.gameObjects.put("backgroundImage", new GameImage("/br/ufsc/game/resources/images/BlackBackgroundFelt.jpg"));
        this.gameObjects.put("loginBtn",new GameButton("/br/ufsc/game/resources/images/ButtonLogin.png"));
        this.gameObjects.put("logoutBtn",new GameButton("/br/ufsc/game/resources/images/ButtonLogout.png"));
        this.gameObjects.put("beginBtn",new GameButton("/br/ufsc/game/resources/images/ButtonBegin.png"));
        this.gameExtras.put("ngInterface", new NetGamesInterface());
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

        if(((NetGamesInterface) gameExtras.get("ngInterface")).isMatchRunning()) {
            GameSceneManager.getInstance().pushState(new CoreGame((NetGamesInterface) gameExtras.get("ngInterface")));
        }
    }

    @Override
    public void draw(Graphics2D g) {
		gameDrawables.stream().sorted((d1,d2)->{return ((GameObject)d1).getZ() - ((GameObject)d2).getZ();}).forEach((dObject) -> dObject.draw(g));
		g.setColor(Color.BLUE);
        g.fillRect(Mouse.getInstance().getPosition().x,Mouse.getInstance().getPosition().y,5,5);
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
        
        
		
        gameObjects.get("loginBtn").setX((int) (Game.getGame().getGameSettings().getWidth()/2) 
            - (gameObjects.get("loginBtn").getWidth()  /2));
        gameObjects.get("loginBtn").setY((int) (Game.getGame().getGameSettings().getHeight()/2) 
            - (gameObjects.get("loginBtn").getHeight() /2));
        gameObjects.get("beginBtn").setX((int) (Game.getGame().getGameSettings().getWidth()/2) 
            - (gameObjects.get("beginBtn").getWidth()  /2));
        gameObjects.get("beginBtn").setY((int) gameObjects.get("loginBtn").getY() 
            + (gameObjects.get("loginBtn").getHeight() /2)
            + 25);
        gameObjects.get("logoutBtn").setX((int) (Game.getGame().getGameSettings().getWidth()/2) 
            - (gameObjects.get("logoutBtn").getWidth()  /2));
        gameObjects.get("logoutBtn").setY((int) gameObjects.get("beginBtn").getY() 
            + (gameObjects.get("logoutBtn").getHeight() /2)
            + 25);
        

        gameObjects.get("backgroundImage").setZ(1);;


        ((GameButton) gameObjects.get("logoutBtn")).setOnClick(new GameAction() {
        
            @Override
            public void doAction(Object[] args) {
                ((NetGamesInterface) gameExtras.get("ngInterface")).disconnect();
            }
        });

        ((GameButton) gameObjects.get("loginBtn")).setOnClick(new GameAction() {
        
            @Override
            public void doAction(Object[] args) {
                boolean preguicaDeDigitarNome = false;
                if (preguicaDeDigitarNome){
                    ((NetGamesInterface) gameExtras.get("ngInterface")).joinSession("a");
                } else {
                    String playerName = "";

                    String message = "Por favor insira seu nome";
                    while(playerName.isEmpty()) {
                        playerName = JOptionPane.showInputDialog(null, message, "Login", JOptionPane.QUESTION_MESSAGE);
                    }
                    if(playerName != null) {
                        ((NetGamesInterface) gameExtras.get("ngInterface")).joinSession(playerName);
                    }
                }
            }
        });

        ((GameButton) gameObjects.get("beginBtn")).setOnClick(new GameAction(){
        
            @Override
            public void doAction(Object[] args) {
                if(((NetGamesInterface) gameExtras.get("ngInterface")).isConnected()) {
                    boolean preguicaDeDizerQSao2Player = true;
                    if(preguicaDeDizerQSao2Player){
                        try {
                            ((NetGamesInterface) gameExtras.get("ngInterface")).beginMatch(2);
                        } catch (NaoConectadoException e) {}
                        return;
                    }
                    
                    int playersNum = 0;
                    do {
                        String message = "Insira a quantidade de jogadores que irão jogar [2 - 5]";
                        String pn = JOptionPane.showInputDialog(null,
                            message, 
                            "Configuração de Partida",
                            JOptionPane.QUESTION_MESSAGE);
                            try {
                                playersNum = Integer.parseInt(pn);
                                
                            } catch (IllegalArgumentException e) {
                                message = "Entrada Inválida - Necessário ser [2 - 5]";
                                JOptionPane.showMessageDialog(null, 
                                    message, 
                                    "Configuração da Partida - ERRO", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                    }while(playersNum == 0);
                    try {
                        ((NetGamesInterface) gameExtras.get("ngInterface")).beginMatch(playersNum);
                    } catch (NaoConectadoException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
	}

    
}