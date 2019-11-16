package br.ufsc.game.network;

import br.ufsc.game.gamelogic.Card;
import br.ufsc.game.gamelogic.GameField;
import br.ufsc.inf.leobr.cliente.Jogada;
/**
 * PlayerPacket
 */
public class PlayerPacket{

	// Variables
	protected Card lastUsedCard;
	protected GameField gameField;
	// Constructor
	public PlayerPacket(Card lastUsedCard, GameField gameField) {
		this.lastUsedCard = lastUsedCard;
		this.gameField = gameField;
	}
	// Interface
	public void calculatePlay() {

	}

	public Card getLastUsedCard() {
		return this.lastUsedCard;
	}

	public GameField getGameField() {
		return this.gameField;
	}
	// Methods
}