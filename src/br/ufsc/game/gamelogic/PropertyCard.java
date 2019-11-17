package br.ufsc.game.gamelogic;

import java.util.ArrayList;

import br.ufsc.game.network.PlayerPacket;

public class PropertyCard extends Card {
    protected ArrayList<Property> properties;
    
    public PropertyCard(int id, String label, int value, State[] neededStates, ArrayList<Property> properties) {
        super(id, label, value, neededStates);
        this.properties = properties;
    }
    public PropertyCard(int id, String label, int value, ArrayList<State> neededStates, ArrayList<Property> properties) {
        super(id,label,value,neededStates);
        this.properties = properties;
    }

	@Override
	public void applyEffect(PlayerPacket playerPacket,int targetPropertiy, int yourProperty, int selectedPlayer) {
		// TODO Auto-generated method stub
        PropertyCard card = (PropertyCard) playerPacket.getLastUsedCard();
        PropertyGroup selectedProperty =
            playerPacket.getGameField().getPlayers().get(0).getZone().getProperties().get(yourProperty);
        for (int i = 0; i < card.getProperties().size(); i++){
            if(selectedProperty.getColor() == card.getProperties().get(i).getColor()){
                selectedProperty.setPropQty(selectedProperty.getPropQty()+1);
                i = 999999; //break loop
            }
        }
    }
    
    public ArrayList<Property> getProperties(){
        return this.properties;
    }
    
}