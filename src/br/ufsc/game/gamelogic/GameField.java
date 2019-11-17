package br.ufsc.game.gamelogic;

import java.util.ArrayList;

/**
 * GameField
 */
public class GameField {

	protected Deck deck;
	protected ArrayList<Player> players;

	//constructed to be used when deserializing
	public GameField(Deck vdeck, int playersQuantity){
		deck = vdeck;

		//below is garbage
		players = new ArrayList<>();
		for (int i=1; i <= playersQuantity; i ++){
			Player p = new Player("player"+i, i);
			players.add(p);
			p.addCards(deck);
		}
		//above is garbage
	}

	public GameField(int playersQuantity){
		deck = new Deck();
		players = new ArrayList<>();
		for (int i=1; i <= playersQuantity; i ++){
			Player p = new Player("player"+i, i);
			players.add(p);
			p.addCards(deck);
		}
	}

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