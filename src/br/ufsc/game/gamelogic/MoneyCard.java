package br.ufsc.game.gamelogic;

import java.util.ArrayList;

import br.ufsc.game.network.PlayerPacket;

/**
 * MoneyCard
 */
public class MoneyCard extends Card{

	public MoneyCard(int id, String label, int value, State[] neededStates) {
		super(id, label, value, neededStates);
	}

	public MoneyCard(int id, String label, int value, ArrayList<State> neededStates) {
		super(id, label, value, neededStates);
	}

	@Override
	public void applyEffect(PlayerPacket playerPacket,int targetPropertiy, int yourProperty, int selectedPlayer) {
		int qtyToAdd = playerPacket.getLastUsedCard().getValue();
		int currentMoney = playerPacket.getGameField().getPlayers().get(0).getZone().getBank();
		playerPacket.getGameField().getPlayers().get(0).getZone().setBank(currentMoney+qtyToAdd);
	}
	
}