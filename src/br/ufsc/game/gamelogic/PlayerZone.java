package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PlayerZone{
    protected int bank;
    protected ArrayList<PropertyGroup> properties;

    public PlayerZone(){
        bank = 0;
        properties = new ArrayList<>();
        properties.add(new PropertyGroup(PropertyColor.brown));
        properties.add(new PropertyGroup(PropertyColor.darkblue));
        properties.add(new PropertyGroup(PropertyColor.utility));
        properties.add(new PropertyGroup(PropertyColor.green));
        properties.add(new PropertyGroup(PropertyColor.lightblue));
        properties.add(new PropertyGroup(PropertyColor.orange));
        properties.add(new PropertyGroup(PropertyColor.purple));
        properties.add(new PropertyGroup(PropertyColor.red));
        properties.add(new PropertyGroup(PropertyColor.yellow));
        properties.add(new PropertyGroup(PropertyColor.railroad));
        //joker ficou de fora: não faria nenhum sentido aqui.
    }

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
                return propertyGroup.getPropQty();
                //return propertyGroup.getPropCards().size();
            }
        }
        return 0;
    }

}