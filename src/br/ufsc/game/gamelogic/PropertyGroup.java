package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PropertyGroup {
    protected PropertyColor color;
    protected ArrayList<PropertyCard> propCards; // acho q simplificaria tirar isso
    protected int propQty; // e usar soh isso
    protected int houseQty;
    protected int hotelQty;
    protected int neededQty;

    public PropertyGroup(PropertyColor color){
        this.color = color;
        propCards = new ArrayList<>();
        propQty = 0;
        houseQty = 0;
        hotelQty = 0;
        switch (color)
        {
            case brown:
            case darkblue:
            case utility: neededQty = 2; break;

            case green:
            case lightblue:
            case orange: 
            case purple:
            case red:
            case yellow: neededQty = 3; break;

            case railroad: neededQty = 4; break;

            case joker: neededQty = -1; break;
        }
    }

    public int getPropQty(){
        return this.propQty;
    }
    public void setPropQty(int qty){
        propQty = qty;
    }
    public int getHotelQty(){
        return this.hotelQty;
    }

    public int getHouseQty(){
        return this.houseQty;
    }

    public void setHotelQty(int qty){
        this.hotelQty = qty;
    }

    public void setHouseQty(int qty){
        this.houseQty = qty;
    }

    public int getNeeded(){
        return this.neededQty;
    }

    public PropertyColor getColor(){
        return this.color;
    }

    public ArrayList<PropertyCard> getPropCards(){
        return this.propCards;
    }
}