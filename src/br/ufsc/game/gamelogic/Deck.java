package br.ufsc.game.gamelogic;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Deck
 */
public class Deck {
	// Variables
	protected Stack<Card> cards;
	// Constructor
    public Deck() {   // popular o deck com as cartas
        int cardId = 0;

        //  CRIANDO CARTAS DINHEIRO
        for (int i = 0; i < 6; i++){  // 6 cartas de 1M
            this.cards.add(new MoneyCard(cardId++, "money1", 1, List.of(State)));
        }

        for (int i = 0; i < 5; i++){  // 5 cartas de 2M
            this.cards.add(new MoneyCard(cardId++, "money2", 2, List.of(State)));
        }

        for (int i = 0; i < 3; i++){  // 3 cartas de 3M
            this.cards.add(new MoneyCard(cardId++, "money3", 3, List.of(State)));
        }

        for (int i = 0; i < 3; i++){  // 3 cartas de 4M
            this.cards.add(new MoneyCard(cardId++, "money4", 4, List.of(State)));
        }

        // 2 cartas de 5M
        this.cards.add(new MoneyCard(cardId++, "money5", 5, List.of(State)));
        this.cards.add(new MoneyCard(cardId++, "money5", 5, List.of(State)));	
        
        // 1 carta de 10M
        this.cards.add(new MoneyCard(cardId++, "money10", 10, List.of(State)));
    
        // CRIANDO TÍTULOS DE POSSE
    
        // Titulos de cor marrom
        // Baltic Avenue
        ArrayList<Property> thisProperties = new ArrayList<Property>();
        Property property = new Property(PropertyColor.brown, "Baltic Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, List.of(State), thisProperties));

        // Mediterranean Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.brown, "Mediterranean Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, List.of(State), thisProperties));


        // Titulos de cor azul escuro
        // Boardwalk
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.darkblue, "Boardwalk");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, List.of(State), thisProperties));

        // Park Place
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.darkblue, "Park Place");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, List.of(State), thisProperties));

        // Titulos de cor verde
        // North Caroline Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "North Caroline Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, List.of(State), thisProperties));
    
        // Pacific Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "Pacific Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, List.of(State), thisProperties));
        
        // Pennsylvanya Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "Pennsylvanya Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, List.of(State), thisProperties));

        // Titulos de cor azul claro
        // Connecticut Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Connecticut Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, List.of(State), thisProperties));

        // Oriental Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Oriental Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, List.of(State), thisProperties));
        
        // Vermont Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Vermont Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, List.of(State), thisProperties));
        

        // Titulos de cor laranja
        // New York Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "New York Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // St. James Place
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "St. James Place");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Tennessee Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "Tennessee Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        

        // Títulos de cor roxa
        // St. Charles Place
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.purple, "St. James Place");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Virginia Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.purple, "Virginia Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // States Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.purple, "States Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Títulos de cor vermelha
        // Kentucky Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.red, "Kentucky Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, List.of(State), thisProperties));
        
        // Indiana Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.red, "Indiana Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, List.of(State), thisProperties));
        
        // Illinois Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.red, "Illinois Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, List.of(State), thisProperties));
        
        // Títulos de cor amarela
        // Ventnor Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Ventnor Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, List.of(State), thisProperties));

        // Marvin Gardens
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Marvin Gardens");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, List.of(State), thisProperties));

        // Atlantic Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Atlantic Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, List.of(State), thisProperties));

        // Títulos de utilidades
        // Water Works
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.utility, "Water Works");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Eletric Company
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.utility, "Eletric Company");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Títulos de ferrovias
        // Short Line
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "Short Line");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // B. & O. Railroad
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "B. & O. Railroad");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Reading Railroad
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "Reading Railroad");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Pennsylvania Railroad
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "Pennsylvania Railroad");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, List.of(State), thisProperties));
        
        // Títulos Curinga
        // Darkblue & Green Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.darkblue, "Darkblue Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.green, "Green Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 4, List.of(State), thisProperties));

        // Lightblue & Brown Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Lightblue Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.brown, "Brown Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 1, List.of(State), thisProperties));

        // Rainbow Wild Card
        thisProperties = new ArrayList<Property>();
        for (PropertyColor pc : PropertyColor.values()) {
            property = new Property(pc, "Rainbow Wild Card");
            thisProperties.add(property);
        }
        this.cards.add(new PropertyCard(cardId++, "wildCard", 0, List.of(State), thisProperties));
        this.cards.add(new PropertyCard(cardId++, "wildCard", 0, List.of(State), thisProperties));
        
        // Orange & Purple Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "Orange Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.purple, "Purple Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 2, List.of(State), thisProperties));
        this.cards.add(new PropertyCard(cardId++, "wildCard", 2, List.of(State), thisProperties));

        // Green & Railroad Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "Green Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.railroad, "Railroad Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 4, List.of(State), thisProperties));
        
        // Lightblue & Railroad Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Lightblue Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.railroad, "Railroad Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 4, List.of(State), thisProperties));
        
        // Utility & Railroad Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.utility, "Utility Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.railroad, "Railroad Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 2, List.of(State), thisProperties));
        
        // Yellow & Red Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Yellow Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.red, "Red Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 3, List.of(State), thisProperties));
        this.cards.add(new PropertyCard(cardId++, "wildCard", 3, List.of(State), thisProperties));
        
        // Cartas de aluguel
        // Rainbow RentCard
        ArrayList<PropertyColor> rentColors = new ArrayList<PropertyColor>();
        for (PropertyColor pc : PropertyColor.values()) {
            rentColors.add(pc);
        }
        State neededStates[] = {State.SelectPropertyColor, State.SelectTargetPlayer};
        this.cards.add(new RentCard(cardId++, "rentCard",3, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",3, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",3, neededStates,rentColors));

        State neededStates[] = {State.SelectPropertyColor};
        // Green & Darkblue RentCard
        PropertyColor rentColors[] = {PropertyColor.green, PropertyColor.darkblue};
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));

        // Brown & Lightblue RentCard
        PropertyColor rentColors[] = {PropertyColor.brown, PropertyColor.lightblue};
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));

        // Purple & Orange RentCard
        PropertyColor rentColors[] = {PropertyColor.purple, PropertyColor.orange};
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));

        // Railroad & Utility RentCard
        PropertyColor rentColors[] = {PropertyColor.railroad, PropertyColor.utility};
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));

        // Red & Yellow RentCard
        PropertyColor rentColors[] = {PropertyColor.red, PropertyColor.yellow};
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, neededStates,rentColors));

    }
	// Interface
	public Card removeFromDeck() {
		return cards.pop();
	}
	// Methods
}