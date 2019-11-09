package br.ufsc.game.gamelogic;

/**
 * MoneyCard
 */
public class MoneyCard extends Card{

	public MoneyCard(int id, String label, int value, State[] neededStates) {
		super(id, label, value, neededStates);
	}

	@Override
	public void applyEffect(int targetPropertiy, int yourProperty, int selectedPlayer) {
		// TODO Auto-generated method stub

	}
	
}