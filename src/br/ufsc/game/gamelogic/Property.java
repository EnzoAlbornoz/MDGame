package br.ufsc.game.gamelogic;

public class Property {
    private PropertyColor color;
    private String label;
    private int rent1;
    private int fullRent;
    
    public Property(PropertyColor color, String label){
        this.color = color;
        this.label = label;
        switch (color) {
            case brown:
                this.rent1 = 1;
                this.fullRent = 2;                
                break;
            case darkblue:
                this.rent1 = 3;
                this.fullRent = 8;                
                break;
            case green:
                this.rent1 = 2;
                this.fullRent = 7;                
                break;
            case lightblue:
                this.rent1 = 1;
                this.fullRent = 3;                
                break;
            case orange:
                this.rent1 = 1;
                this.fullRent = 5;                
                break;
            case purple:
                this.rent1 = 1;
                this.fullRent = 4;                
                break;
            case railroad:
                this.rent1 = 1;
                this.fullRent = 4;                
                break;
            case red:
                this.rent1 = 2;
                this.fullRent = 6;                
                break;
            case utility:
                this.rent1 = 1;
                this.fullRent = 2;                
                break;
            case yellow:
                this.rent1 = 2;
                this.fullRent = 6;                
                break;
            default:
                this.rent1 = 0;
                this.fullRent = 0;
            
        }
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