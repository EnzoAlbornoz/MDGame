package br.ufsc.game.gamelogic;

import java.util.ArrayList;

/**
 * MoneyCard
 */
public class MoneyCard extends Card{

	public MoneyCard(int id, String label, int value, State[] neededStates) {
		super(id, label, value, neededStates);
		type = Type.moneyCard;
	}

	public MoneyCard(int id, String label, int value, ArrayList<State> neededStates) {
		super(id, label, value, neededStates);
		type = Type.moneyCard;
	}

	@Override
	public void applyEffect(int targetPropertiy, int yourProperty, int selectedPlayer) {
		// TODO Auto-generated method stub

	}
	
}