package br.ufsc.game.network;

import java.util.ArrayList;
import java.util.Stack;

import br.ufsc.game.gamelogic.Card;
import br.ufsc.game.gamelogic.Deck;
import br.ufsc.game.gamelogic.GameField;
import br.ufsc.game.gamelogic.MoneyCard;
import br.ufsc.game.gamelogic.Player;
import br.ufsc.game.gamelogic.PropertyGroup;
import br.ufsc.game.gamelogic.State;
import br.ufsc.inf.leobr.cliente.Jogada;

public class SerializablePacket implements Jogada {
    
    protected ArrayList<Integer> ids;
    protected ArrayList<ArrayList<Integer>> playerZones;
    int playersQty;
    int idOfPlayer0;
    
    public SerializablePacket(PlayerPacket p){
        ids = new ArrayList<>();
        playerZones = new ArrayList<>();

        // ids list starts with lastUsedCard
        Card c = p.getLastUsedCard();
        int cidx = p.getGameField().getDeck().getCards().size();

        //and then continues with all the deck
        while(cidx > 0){ // c is null when removed from empty deck
            cidx--;
            ids.add(c.getId());
            c = p.getGameField().getDeck().getCards().get(cidx);
        }
        
        for(int g = 0; g < 5; g++){
            log(">>> cardId: "+ids.get(g));
        }

        //serializing playerZones
        for (Player player : p.getGameField().getPlayers() ) {//foreach player
            ArrayList<Integer> zone = new ArrayList<>(); //create array<int> that represents the zone
            zone.add(player.getZone().getBank()); //first put his money
            for (PropertyGroup pGroup: player.getZone().getProperties()){ //then all the propQtys
                zone.add(pGroup.getPropQty());
            }
            playerZones.add(zone); //save it in the attribute
            //TODO: deserealize hotels and houses
        }

        playersQty = p.gameField.getPlayers().size();
        idOfPlayer0 = p.gameField.getPlayers().get(0).getId();
    }

    public PlayerPacket generatePlayerPacket(GameField gameField){
        Stack<Card> cards = new Stack<>();
        ArrayList<Player> players = gameField.getPlayers();
       
        for(int g = 0; g < 5; g++){
            log(">>> cardId: "+ids.get(g));
        }

        log("deserealizando deck");
        //deserializing deck
        Deck deck = new Deck(); //create a new deck with all the cards of the game
        while (ids.size()>0){ //while there are saved ids
            //log("size: "+ids.size());
            int id = ids.remove(ids.size()-1);
            for (int i = 0; i < deck.getCards().size(); i++) {
                //log("i: "+i);
                if (deck.getCards().get(i).getId() == id){ //search in the created deck for an equal card to copy
                    //log("i push: "+i);
                    cards.push(deck.getCards().get(i));
                    i = deck.getCards().size();// break loop
                }
            }
        }
        Deck deck2 = new Deck(cards); // i hope it does not loses any reference
        for(int g = deck2.getCards().size()-1; g > deck2.getCards().size()-6; g--){
            log(">>> deck2cardId.get("+g+"): "+deck2.getCards().get(g).getId());
        }

        Card lastUsedCard = deck2.removeFromDeck();
        gameField.setDeck(deck2);

        log("tirando players indesejados");
        //remove players with index too high. (Game is starting with more players than it should for
        //the players that do not start in position 0)
        while(players.size() > playersQty){
            int max = -1;
            int index = -1;
            for (int i = 0; i < players.size(); i++){
                if(max < players.get(i).getId()){
                    max = players.get(i).getId();
                    index = i;
                }
            }
            players.remove(index);
        }

        log("ajeitando ordem dos jogadores");
        //fix order: rotate left until player0 match proper id
        while(players.get(0).getId() != idOfPlayer0){
            players.add(players.remove(0));
        }

        log("copiando playerZones");
        //for each player, set his zone
        for (int i = 0; i < players.size(); i++){
            ArrayList<Integer> zone = playerZones.remove(0);//this zone came from other player

            //set his bank
            int money = zone.remove(0);
            players.get(i).getZone().setBank(money);

            //set his properties
            ArrayList<PropertyGroup> properties = players.get(i).getZone().getProperties();
            for(int j=0; j < properties.size(); j++){
                int qty = zone.remove(0);
                properties.get(j).setPropQty(qty);
            }

        }

        //dont change the hands, because the hands of other players dont matter much
        //and your hand should be changed only by you when you play.

        PlayerPacket p = new PlayerPacket(lastUsedCard, gameField);

        log("player at 0: "+p.gameField.getPlayers().get(0).getId());
        log("player at 1: "+p.gameField.getPlayers().get(1).getId());
        
        log("deserealizado com sucesso kkk");
        return p;
    }

    void log(String s){
        System.out.println(s);
    }
}