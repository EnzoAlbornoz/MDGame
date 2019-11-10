package br.ufsc.game.gamelogic;

import java.util.ArrayList;

/**
 * GameField
 */
public class GameField {

	protected Deck deck;
	protected ArrayList<Player> players;

	public ArrayList<Player> getPlayers(){
		return this.players;
	}

	public Deck getDeck(){
		return this.deck;
	}

	public void setDeck(Deck deck){
		this.deck = deck;
	}

	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}

	
}