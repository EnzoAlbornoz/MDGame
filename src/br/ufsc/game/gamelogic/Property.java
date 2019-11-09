package br.ufsc.game.gamelogic;

public class Property {
    private PropertyColor color;
    private String label;
    private int rent1;
    private int rent2;
    private int rent3;
    private int fullRent;
    
    public Property(PropertyColor color, String label, int rent1, int rent2, int rent3, int fullRent){
        this.color = color;
        this.label = label;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.fullRent = fullRent;
    }

    public int getRent(){
        return this.rent1;
    }

    public int getFullRent(){
        return this.fullRent;
    }

    public String getLabel(){
        return this.label;
    }
    
    public PropertyColor getColor(){
        return this.color;
    }
    
}