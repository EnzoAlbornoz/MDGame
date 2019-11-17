package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PropertyGroup {
    protected PropertyColor color;
    //protected ArrayList<PropertyCard> propCards; // acho q simplificaria tirar isso
    protected int propQty; // e usar soh isso
    protected int houseQty;
    protected int hotelQty;
    protected int neededQty;

    public PropertyGroup(PropertyColor color){
        this.color = color;
        //propCards = new ArrayList<>();
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

    /*
    public ArrayList<PropertyCard> getPropCards(){
        return this.propCards;
    }
    */

    public int getRentMountant(){
        int mountant = 0;
        switch (color)
        {
            case utility: //equal brown
            case brown:
                if(propQty == 1){
                    mountant = 1;
                } else if (propQty >= 2){
                    mountant = 2;
                } break;
            case darkblue:
                if(propQty == 1){
                    mountant = 3;
                } else if (propQty >= 2){
                    mountant = 8;
                } break;
            case green:
                if(propQty == 1){
                    mountant = 2;
                } else if (propQty == 2){
                    mountant = 4;
                } else if (propQty >= 3){
                    mountant = 7;
                } break;
            case lightblue:
                if(propQty == 1){
                    mountant = 1;
                } else if (propQty == 2){
                    mountant = 2;
                } else if (propQty >= 3){
                    mountant = 3;
                } break;
            case orange:
                if(propQty == 1){
                    mountant = 1;
                } else if (propQty == 2){
                    mountant = 3;
                } else if (propQty >= 3){
                    mountant = 5;
                } break;
            case purple:
                if(propQty == 1){
                    mountant = 1;
                } else if (propQty == 2){
                    mountant = 2;
                } else if (propQty >= 3){
                    mountant = 4;
                } break;
            case red:
                if(propQty == 1){
                    mountant = 2;
                } else if (propQty == 2){
                    mountant = 3;
                } else if (propQty >= 3){
                    mountant = 6;
                } break;
            case yellow:
                if(propQty == 1){
                    mountant = 2;
                } else if (propQty == 2){
                    mountant = 4;
                } else if (propQty >= 3){
                    mountant = 6;
                } break;
            case railroad:
                if(propQty == 1){
                    mountant = 1;
                } else if (propQty == 2){
                    mountant = 2;
                } else if (propQty == 3){
                    mountant = 3;
                } else if (propQty >= 4){
                    mountant = 4;
                } break;

            case joker: // does not make sense
        }
        return mountant;
    }

    public int getPropPrice(){
        int propPrice = 2;
        switch (color)
        {
            case brown:
            case lightblue:
                propPrice = 1; break;
                
            case red:
            case yellow: 
                propPrice = 3; break;

            case darkblue:
            case green:
                propPrice = 4; break;
            
            default:
                propPrice = 2; break;
            /*
            case utility: case orange: case purple: case railroad:
                propPrice = 2; break;
            */
        }
        return propPrice;
    }
}