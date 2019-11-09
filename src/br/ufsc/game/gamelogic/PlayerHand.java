package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class PlayerHand {
    protected ArrayList<Card> cards;

    public ArrayList<Card> getCards(){
        return this.cards;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void remove(Card card) {
        this.cards.remove(card);
    }

}