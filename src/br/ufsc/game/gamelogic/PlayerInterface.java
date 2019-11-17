package br.ufsc.game.gamelogic;

import java.awt.Graphics2D;
import java.util.HashMap;

import br.ufsc.game.engine.interfaces.Drawable;
import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameObject;
import br.ufsc.game.network.NetGamesInterface;

/**
 * PlayerInterface
 */
public class PlayerInterface /*implements Updatable, Drawable*/ {

	protected HashMap<String, GameObject> problemDomain;
	FSMGame fsmGame;

	public PlayerInterface(NetGamesInterface nGamesInterface){
		this.fsmGame = new FSMGame(nGamesInterface, this);
	}

	public FSMGame getFSMGame(){
		return fsmGame;
	}

	public boolean doesEndTurnBtnAppear(){
		return fsmGame.doesEndTurnButtonAppear();
		//return true;
	}
	
	public void endTurn(){
		if(fsmGame != null){
			fsmGame.endTurn();
		}
	}

	public boolean isIconSelected(int id) {
		return fsmGame.isIconSelected(id);
	}
}