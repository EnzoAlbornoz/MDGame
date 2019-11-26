package br.ufsc.game.network;

import br.ufsc.game.gamelogic.Card;
import br.ufsc.game.gamelogic.GameField;
/**
 * PlayerPacket
 */
public class PlayerPacket {

	// Variables
	protected Card lastUsedCard;
	protected GameField gameField;
	// Constructor
	public PlayerPacket(Card lastUsedCard, GameField gameField) {
		this.lastUsedCard = lastUsedCard;
		this.gameField = gameField;
	}

	public Card getLastUsedCard() {
		return this.lastUsedCard;
	}

	public GameField getGameField() {
		return this.gameField;
	}
	// Methods
}