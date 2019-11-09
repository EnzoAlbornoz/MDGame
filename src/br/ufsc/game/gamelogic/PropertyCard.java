package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PropertyCard extends Card {
    protected ArrayList<Property> properties;
    
    public PropertyCard(int id, String label, int value, State[] neededStates, ArrayList<Property> properties) {
        super(id, label, value, neededStates);
        this.properties = properties;
	}

	@Override
	public void applyEffect(int targetPropertiy, int yourProperty, int selectedPlayer) {
		// TODO Auto-generated method stub
		
    }
    
    public ArrayList<Property> getProperties(){
        return this.properties;
    }
    
}