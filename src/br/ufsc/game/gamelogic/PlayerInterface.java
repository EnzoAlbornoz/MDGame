package br.ufsc.game.gamelogic;

import java.awt.Graphics2D;
import java.util.HashMap;

import br.ufsc.game.engine.interfaces.Drawable;
import br.ufsc.game.engine.interfaces.Updatable;
import br.ufsc.game.engine.logic.GameObject;

/**
 * PlayerInterface
 */
public class PlayerInterface /*implements Updatable, Drawable*/ {

	protected HashMap<String, GameObject> problemDomain;
	FSMGame fsmGame;

	public PlayerInterface(int clientId, int playersQuantity){
		this.fsmGame = new FSMGame(clientId, playersQuantity);
	}

	public FSMGame getFSMGame(){
		return fsmGame;
	}

	/*
	public PlayerInterface(FSMGame fsmGame){
		this.fsmGame = fsmGame;
	}
	*/

	public boolean doesEndTurnBtnAppear(){
		return fsmGame.doesEndTurnButtonAppear();
		//return true;
	}
	
	public void endTurn(){
		if(fsmGame != null){
			fsmGame.endTurn();
		}
	}
}