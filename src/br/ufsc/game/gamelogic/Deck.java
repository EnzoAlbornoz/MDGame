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
            this.cards.add(new MoneyCard(cardId++, "money1", 1, []));
        }

        for (int i = 0; i < 5; i++){  // 5 cartas de 2M
            this.cards.add(new MoneyCard(cardId++, "money2", 2, []));
        }

        for (int i = 0; i < 3; i++){  // 3 cartas de 3M
            this.cards.add(new MoneyCard(cardId++, "money3", 3, []));
        }

        for (int i = 0; i < 3; i++){  // 3 cartas de 4M
            this.cards.add(new MoneyCard(cardId++, "money4", 4, []));
        }

        // 2 cartas de 5M
        this.cards.add(new MoneyCard(cardId++, "money5", 5, []));
        this.cards.add(new MoneyCard(cardId++, "money5", 5, []));	
        
        // 1 carta de 10M
        this.cards.add(new MoneyCard(cardId++, "money10", 10, []));
    
        // CRIANDO TÍTULOS DE POSSE
    
        // Titulos de cor marrom
        // Baltic Avenue
        ArrayList<Property> thisProperties = new ArrayList<Property>();
        Property property = new Property(brown, "Baltic Avenue",1,2,2,2);
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, [], thisProperties));

        // Mediterranean Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(brown, "Mediterranean Avenue",1,2,2,2);
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 1, [], thisProperties));


        // Titulos de cor azul
        // Boardwalk
        thisProperties = new ArrayList<Property>();
        property = new Property(blue, "Boardwalk",3,8,8,8);
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, [], thisProperties));

        // Park Place
        thisProperties = new ArrayList<Property>();
        property = new Property(blue, "Park Place",3,8,8,8);
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, [], thisProperties));

        // Titulos de cor verde
        // North Caroline Avenue
        thisProperties = new ArrayList<Property>();
        property = new Property(green, "North Caroline Avenue",2,4,7,7);
        thisProperties.add(property);
        this.cards.add(new PropertyCard(cardId++, "propertyCard", 4, [], thisProperties));
    
    
    }
	// Interface
	public Card removeFromDeck() {
		return cards.pop();
	}
	// Methods
}