package br.ufsc.game.gamelogic;

import java.util.ArrayList;

import br.ufsc.game.network.PlayerPacket;

/**
 * RentCard
 */
public class RentCard extends Card {

	// Variables
	protected ArrayList<PropertyColor> colors;
	protected boolean isSingleTarget;
	// Constructor
	public RentCard(int id,String label,int value, State[] neededStates,PropertyColor[] colors) {
		super(id, label, value, neededStates);
		this.colors = new ArrayList<PropertyColor>(colors.length);
		for (PropertyColor color : colors) {
			this.colors.add(color);
		}
		isSingleTarget = this.colors.size() > 2;
	}
	// Interface
	@Override //by 'you' I mean the player that is making the play right at this moment
	public void applyEffect(PlayerPacket playerPacket,int targetProperty, int yourProperty, int selectedPlayer) {

		//set up some references
		ArrayList<Player> players = playerPacket.getGameField().getPlayers();
		Player you = players.get(0);
		ArrayList<PropertyGroup> yourProperties = you.getZone().getProperties();
		
		//check if the player select a valid property
		boolean cheating = true;
		PropertyColor chosenColor = yourProperties.get(yourProperty).getColor();
		for (int c = 0; c < colors.size(); c++) {
			if (chosenColor == colors.get(c)){
				cheating = false;
			}
		}
		if (cheating) {
			System.out.println("Player selected color that wasn't at rent card");
			return; //for now, the player looses the action and nothing else happens.
		}

		//rentMountant is (the money you will make during this method execution)/targetQty
		int rentMountant = yourProperties.get(yourProperty).getRentMountant();
		
		// rent can be spread or single Target. Anyway, lets loop through all the possible targets
		for( int k = 1; k < players.size(); k++){ //start at 1 and dont steal yourself!

			// check if you should take this player's money
			if ( (!isSingleTarget) || players.get(k).getId()==selectedPlayer ) {
				takeMoney(rentMountant, players, k); //k is the index of the player you want to take money from
			}
		}
	}
	// Methods
	public ArrayList<PropertyColor> getColors(){
		return colors;
	}

	public static void takeMoney(int rentMountant, ArrayList<Player> players, int targetIndex){
		Player you = players.get(0);
		ArrayList<PropertyGroup> yourProperties = you.getZone().getProperties();

		// mountant is the money you will take from this player (remember u can also steal properties)
		int mountant = rentMountant;
				
		int targetBank = players.get(targetIndex).getMoney();
		targetBank -= mountant;

		ArrayList<PropertyGroup> targetProperties = 
			players.get(targetIndex).getZone().getProperties();
		int i = 0;
		// if the player had enough money, targetBank >= 0, otherwise, lets take his properties
		while (targetBank < 0 || i >= targetProperties.size()-1){

			//propQty is the number of target's Proprieties of the color correspondent to 'i'
			int propQty = targetProperties.get(i).getPropQty(); 
			if ( propQty > 0){ // you can't rob someone who has nothing
				// take his propriety and add to your zone
				targetProperties.get(i).setPropQty(propQty-1);
				yourProperties.get(i).setPropQty(yourProperties.get(i).getPropQty()+1);

				int propPrice = targetProperties.get(i).getPropPrice();
				mountant -= propPrice; //you earn less money, cause you got a property
				targetBank += propPrice; //his debt is forgiven by the cost of the property
			}
			i++;
		}
		//update the bank of both players involved
		int win = you.getMoney()+mountant;
		win = win >= 0 ? win : 0;
		int loose = targetBank;
		loose = loose >= 0 ? loose : 0;
		you.getZone().setBank(win);
		players.get(targetIndex).getZone().setBank(loose);
	}
}