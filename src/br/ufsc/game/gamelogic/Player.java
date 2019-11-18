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
        public Deck addCards(Deck deck){
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
            if(this.hand.getCards() == null){
                log("cards is null at Player.witchCardIsThis!!!");
                return "back";
            }

            if(this.hand == null){
                log("hand is null at Player.witchCardIsThis!!!");
                return "back";
            }

            if(posInHand>=0 && posInHand < hand.getCards().size()){
                PlayerHand h = this.hand;
                log(h);
                ArrayList<Card> cds = h.getCards();
                log(cds);
                Card c = cds.get(posInHand);
                log(c);
                String label = c.getLabel();
                log(label);
                return label;
            } else {
                return "back";
            }
        }

        void log(Object s){
            //System.out.println(s);
        }
}