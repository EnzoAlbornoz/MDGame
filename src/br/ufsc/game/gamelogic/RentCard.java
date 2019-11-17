package br.ufsc.game.gamelogic;

import java.util.ArrayList;

/**
 * RentCard
 */
public class RentCard extends Card {

	// Variables
	protected ArrayList<PropertyColor> colors;
	// Constructor
	public RentCard(int id,String label,int value, State[] neededStates,PropertyColor[] colors) {
		super(id, label, value, neededStates);
		this.colors = new ArrayList<PropertyColor>(colors.length);
		for (PropertyColor color : colors) {
			this.colors.add(color);
		}
		type = Type.rentCard;
	}
	// Interface
	@Override
	public void applyEffect(int targetPropertiy, int yourProperty, int selectedPlayer) {
		// TODO Auto-generated method stub
		
	}
	// Methods
	public ArrayList<PropertyColor> getColors(){
		return colors;
	}

	
}