package br.ufsc.game.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Deck
 */
public class Deck {
	// Variables
	protected Stack<Card> cards;
	// Constructor
    public Deck() {   // popular o deck com as cartas
        this.cards = new Stack<>();
        int cardId = 0;

        //  CRIANDO CARTAS DINHEIRO
        ArrayList<State> moneyStates = new ArrayList<State>();
        for (int i = 0; i < 6; i++){  // 6 cartas de 1M
            this.cards.add(new MoneyCard(cardId++, "money1", 1, moneyStates));
        }

        for (int i = 0; i < 5; i++){  // 5 cartas de 2M
            this.cards.add(new MoneyCard(cardId++, "money2", 2, moneyStates)));
        }

        for (int i = 0; i < 3; i++){  // 3 cartas de 3M
            this.cards.add(new MoneyCard(cardId++, "money3", 3, moneyStates));
        }

        for (int i = 0; i < 3; i++){  // 3 cartas de 4M
            this.cards.add(new MoneyCard(cardId++, "money4", 4, moneyStates));
        }

        // 2 cartas de 5M
        this.cards.add(new MoneyCard(cardId++, "money5", 5, moneyStates));
        this.cards.add(new MoneyCard(cardId++, "money5", 5, moneyStates));	
        
        // 1 carta de 10M
        this.cards.add(new MoneyCard(cardId++, "money10", 10, moneyStates));
    
        // CRIANDO TÍTULOS DE POSSE
    
        ArrayList<State> propertyStates = new ArrayList<>();
        // Titulos de cor marrom
        // Baltic Avenue
        ArrayList<Property> thisProperties = new ArrayList<Property>();
        Property property = new Property(PropertyColor.brown, "Baltic Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, propertyStates, thisProperties));

        // Mediterranean Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.brown, "Mediterranean Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, propertyStates, thisProperties));


        // Titulos de cor azul escuro
        // Boardwalk
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.darkblue, "Boardwalk");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, propertyStates, thisProperties));

        // Park Place
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.darkblue, "Park Place");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, propertyStates, thisProperties));

        // Titulos de cor verde
        // North Caroline Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "North Caroline Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, propertyStates, thisProperties));
    
        // Pacific Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "Pacific Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, propertyStates, thisProperties));
        
        // Pennsylvanya Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "Pennsylvanya Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, propertyStates, thisProperties));

        // Titulos de cor azul claro
        // Connecticut Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Connecticut Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, propertyStates, thisProperties));

        // Oriental Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Oriental Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, propertyStates, thisProperties));
        
        // Vermont Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Vermont Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, propertyStates, thisProperties));
        
        // Titulos de cor laranja
        // New York Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "New York Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, propertyStates, thisProperties));
        
        // St. James Place
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "St. James Place");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, propertyStates, thisProperties));
        
        // Tennessee Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "Tennessee Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, propertyStates, thisProperties));
        

        // Títulos de cor roxa
        // St. Charles Place
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.purple, "St. James Place");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, propertyStates, thisProperties));
        
        // Virginia Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.purple, "Virginia Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, propertyStates, thisProperties));
        
        // States Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.purple, "States Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 2, propertyStates, thisProperties));
        
        // Títulos de cor vermelha
        // Kentucky Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.red, "Kentucky Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, propertyStates, thisProperties));
        
        // Indiana Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.red, "Indiana Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, propertyStates, thisProperties));
        
        // Illinois Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.red, "Illinois Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, propertyStates, thisProperties));
        
        // Títulos de cor amarela
        // Ventnor Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Ventnor Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, propertyStates, thisProperties));

        // Marvin Gardens
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Marvin Gardens");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, propertyStates, thisProperties));

        // Atlantic Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Atlantic Avenue");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 3, propertyStates, thisProperties));

        // Títulos de utilidades
        // Water Works
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.utility, "Water Works");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "utilityProperty", 2, propertyStates, thisProperties));
        
        // Eletric Company
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.utility, "Eletric Company");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "utilityProperty", 2, propertyStates, thisProperties));
        
        // Títulos de ferrovias
        // Short Line
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "Short Line");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "railroadProperty", 2, propertyStates, thisProperties));
        
        // B. & O. Railroad
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "B. & O. Railroad");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "railroadProperty", 2, propertyStates, thisProperties));
        
        // Reading Railroad
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "Reading Railroad");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "railroadProperty", 2, propertyStates, thisProperties));
        
        // Pennsylvania Railroad
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.railroad, "Pennsylvania Railroad");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "railroadProperty", 2, propertyStates, thisProperties));
        
        // Títulos Curinga
        ArrayList<State> wildCardStates = new ArrayList<>();
        wildCardStates.add(State.SelectYourProperty);
        // Darkblue & Green Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.darkblue, "Darkblue Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.green, "Green Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 4, wildCardStates, thisProperties));

        // Lightblue & Brown Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Lightblue Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.brown, "Brown Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 1, wildCardStates, thisProperties));

        // Rainbow Wild Card
        thisProperties = new ArrayList<Property>();
        for (PropertyColor pc : PropertyColor.values()) {
            property = new Property(pc, "Rainbow Wild Card");
            thisProperties.add(property);
        }
        this.cards.add(new PropertyCard(cardId++, "wildCard", 0, wildCardStates, thisProperties));
        this.cards.add(new PropertyCard(cardId++, "wildCard", 0, wildCardStates, thisProperties));
        
        // Orange & Purple Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.orange, "Orange Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.purple, "Purple Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 2, wildCardStates, thisProperties));
        this.cards.add(new PropertyCard(cardId++, "wildCard", 2, wildCardStates, thisProperties));

        // Green & Railroad Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.green, "Green Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.railroad, "Railroad Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 4, wildCardStates, thisProperties));
        
        // Lightblue & Railroad Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.lightblue, "Lightblue Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.railroad, "Railroad Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 4, wildCardStates, thisProperties));
        
        // Utility & Railroad Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.utility, "Utility Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.railroad, "Railroad Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 2, wildCardStates, thisProperties));
        
        // Yellow & Red Wild Card
        thisProperties = new ArrayList<Property>();
        property = new Property(PropertyColor.yellow, "Yellow Property");
        thisProperties.add(property);
        property = new Property(PropertyColor.red, "Red Property");
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "wildCard", 3, wildCardStates, thisProperties));
        this.cards.add(new PropertyCard(cardId++, "wildCard", 3, wildCardStates, thisProperties));
        
        // Cartas de aluguel
        State rentStates[] = {State.SelectYourProperty, State.SelectTargetPlayer};
        // Rainbow RentCard
        /*
        ArrayList<PropertyColor> rentColors = new ArrayList<PropertyColor>();
        for (PropertyColor pc : PropertyColor.values()) {
            rentColors.add(pc);
        }
        */
        PropertyColor rainbowRentColors[] = {PropertyColor.brown, PropertyColor.lightblue, PropertyColor.purple, PropertyColor.red, PropertyColor.green, 
            PropertyColor.darkblue, PropertyColor.railroad, PropertyColor.utility, PropertyColor.yellow, PropertyColor.orange, PropertyColor.joker};

        this.cards.add(new RentCard(cardId++, "rentCard",3, rentStates,rainbowRentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",3, rentStates,rainbowRentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",3, rentStates,rainbowRentColors));

        // Green & Darkblue RentCard
        PropertyColor rentColors[] = {PropertyColor.green, PropertyColor.darkblue};
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));

        // Brown & Lightblue RentCard
        rentColors = {PropertyColor.brown, PropertyColor.lightblue};
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));

        // Purple & Orange RentCard
        rentColors = {PropertyColor.purple, PropertyColor.orange};
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));

        // Railroad & Utility RentCard
        rentColors = {PropertyColor.railroad, PropertyColor.utility};
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));

        // Red & Yellow RentCard
        rentColors = {PropertyColor.red, PropertyColor.yellow};
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));
        this.cards.add(new RentCard(cardId++, "rentCard",1, rentStates,rentColors));
       
        // Cartas de ação
        // Deal Breaker
        GameAction cardAction; // rouba conjunto completo
        State neededStates[] = {State.SelectTargetPlayer, State.SelectTargetProperty};
        this.cards.add(new ActionCard(cardId++, "dealBreaker", 5, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "dealBreaker", 5, neededStates, cardAction));

        // Debt Collector
        // cardAction = new GameAction();  // Rouba 5M de um jogador
        neededStates = {State.SelectTargetPlayer};
        this.cards.add(new ActionCard(cardId++, "debtCollector", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "debtCollector", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "debtCollector", 3, neededStates, cardAction));
        
        /* essa carta nao dah de resolver em uma jogada soh, entao eu tinha pedido para tirar (Cainã)
        // Double the rent!
        // cardAction = new GameAction();  // Dobra aluguel da carta aluguel usada
        State neededStates[] = {State.SelectYourProperty};
        this.cards.add(new ActionCard(cardId++, "doubleTheRent", 1, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "doubleTheRent", 1, neededStates, cardAction));
        */

        // Forced Deal
        // cardAction = new GameAction();  // Troca propriedade com um jogador, n pode ser parte de conjunto completo
        neededStates = {State.SelectYourProperty, State.SelectTargetPlayer, State.SelectTargetProperty};
        this.cards.add(new ActionCard(cardId++, "forcedDeal", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "forcedDeal", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "forcedDeal", 3, neededStates, cardAction));
        
        // Hotel
        // cardAction = new GameAction();  // Aumenta aluguel de um conjunto completo em 4M, exceto railroad e utility
        neededStates = {State.SelectYourProperty};
        this.cards.add(new ActionCard(cardId++, "hotel", 4, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "hotel", 4, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "hotel", 4, neededStates, cardAction));
        
        // House
        // cardAction = new GameAction();  // Aumenta aluguel de um conjunto completo em 3M, exceto railroad e utility
        neededStates = {State.SelectYourProperty};
        this.cards.add(new ActionCard(cardId++, "house", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "house", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "house", 3, neededStates, cardAction));
        
        // It's my Birthday
        neededStates = {};
        // cardAction = new GameAction();  // Todos os jogadores te dão 2M
        this.cards.add(new ActionCard(cardId++, "itsMyBirthday", 2, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "itsMyBirthday", 2, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "itsMyBirthday", 2, neededStates, cardAction));
        
        // Pass Go
        // cardAction = new GameAction();  // Compra 2 cartas do deck
        for (int i = 0; i < 10; i++){
            this.cards.add(new ActionCard(cardId++, "passGo", 1, neededStates, cardAction));
        }
        
        // Sly Deal
        // cardAction = new GameAction();  // Rouba propriedade que n faz parte de conjunto completo
        neededStates = {State.SelectTargetPlayer, SelectTargetProperty};
        this.cards.add(new ActionCard(cardId++, "slyDeal", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "slyDeal", 3, neededStates, cardAction));
        this.cards.add(new ActionCard(cardId++, "slyDeal", 3, neededStates, cardAction));
        

       //  SHUFFLING DECK
        Collections.shuffle(this.cards);
    }
	// Interface
	public Card removeFromDeck() {
		return this.cards.pop();
	}
	// Methods
}