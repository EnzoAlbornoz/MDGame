package br.ufsc.game.gamelogic;

import java.util.ArrayList;

public class Player {
    protected PlayerHand hand;
    protected PlayerZone zone;
    protected String name;
    protected int id;
    protected int displayColor;

    public Player(String name, int id) {
        this.hand = new PlayerHand();
        this.zone = new PlayerZone();
        this.name = name;
        this.id = id;
        // this.displayColor = 0; possivelmente uma função pra n repetir cor
    }


        public int getId(){
            return this.id;
        }

        public PlayerHand getHand(){
            return this.hand;
        }

        public PlayerZone getZone(){
            return this.zone;
        }

        public int getDisplayColor(){
            return this.displayColor;
        }

        public int getMoney(){
            return this.zone.getBank();
        }

        public void storeCard(Card card){
            this.hand.add(card);
        }

        public int howManyPropCards(PropertyColor c) {
            return this.zone.howManyPropCards(c);
        }

        // Não necessário parametro com numero de cartas
        public Deck addCards(Deck deck){ // Necessário jogador ter visão do deck, ou retornar quantidade adicionada
            if (this.hand.getCards().size() == 0){
                for (int i = 0; i < 5; i++) {
                    this.storeCard(deck.removeFromDeck());
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    this.storeCard(deck.removeFromDeck());
                }
            }
            return deck;
        }

        // esse metodo eh usado no UpdateUI, o propósito original na verdade era retornar o path da imagem da carta,
        // para que a UI soubesse que imagem mostrar. Mas de repente eh melhor mesmo retornar a label e colocar na cena
        //uma estrutura de dados pra saber o path de acordo com a label
        public String wichCardIsThis(int posInHand){ 
            return this.hand.getCards().get(posInHand).getLabel();
        }
}