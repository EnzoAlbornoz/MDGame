package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PlayerZone{
    protected int bank;
    protected ArrayList<PropertyGroup> properties;

    public ArrayList<PropertyGroup> getProperties(){
        return this.properties;
    }

    public int getBank(){
        return this.bank;
    }

    public void setBank(int money){ //NOVO METODO
        bank = money;
    }

    public int howManyPropCards(PropertyColor c) {
        for (PropertyGroup propertyGroup : properties) {
            if (propertyGroup.getColor() == c) {
                return propertyGroup.getPropCards().size();
            }
        }
        return 0;
    }
}