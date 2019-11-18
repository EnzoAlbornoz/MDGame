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
        PropertyCard card = (PropertyCard) playerPacket.getLastUsedCard();
        ArrayList<PropertyGroup> yourProperties =
            playerPacket.getGameField().getPlayers().get(0).getZone().getProperties();
        PropertyGroup selectedProperty = yourProperties.get(yourProperty);
        
        boolean cheating = true;
        for (int k = 0; k < card.getProperties().size(); k++){
            if (card.getProperties().get(k).getColor() == selectedProperty.getColor()){
                cheating = false;
            }
        }
        if (cheating) {
            PropertyColor validColor = card.getProperties().get(0).getColor();
            int j = 0;
            while(validColor != yourProperties.get(j).getColor()){
                j++;
            }
            selectedProperty = yourProperties.get(j);
        }

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