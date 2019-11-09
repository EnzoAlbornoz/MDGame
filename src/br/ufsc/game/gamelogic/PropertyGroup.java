package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PropertyGroup {
    protected PropertyColor color;
    protected ArrayList<PropertyCard> propCards;
    protected int houseQty;
    protected int hotelQty;
    protected int neededQty;


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