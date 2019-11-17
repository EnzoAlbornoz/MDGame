package br.ufsc.game.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import br.ufsc.game.network.NetGamesInterface;
import br.ufsc.game.network.PlayerPacket;
import br.ufsc.game.network.SerializablePacket;

public class FSMGame {
    protected State currentState;
    protected GameField gameField;
    protected int actionsQty;
    protected PropertyGroup yourProperty;
    protected PropertyGroup targetsProperty;
    protected boolean store;
    protected boolean use;
    protected Card lastUsedCard;
    protected int clientId;
    protected int selectedPlayerId;
    protected boolean actionsEnabled;
    protected PlayerInterface playerInterface;
    protected NetGamesInterface netGamesInterface;

    void log(String string){
        System.out.println("logger says:" + string);
    }

    public GameField getGameField(){
        return gameField;
    }

    public FSMGame(NetGamesInterface nGamesInterface, PlayerInterface vplayerInterface){ //playerNumber serah o numero desse jogador, tipo se ele eh o 1, eh o primeiro a jogar
        // set up variables
        clientId = nGamesInterface.getPlayerId();
        int playersQuantity = nGamesInterface.getPlayersQuantity();
        netGamesInterface = nGamesInterface;
        actionsQty = 3;
        selectedPlayerId = clientId;
        playerInterface = vplayerInterface; // essa interface aponta de volta pra FSMGame
        currentState = State.IsItMyTurn;
        lastUsedCard = new MoneyCard(666, "label", 666, new ArrayList<State>());
        actionsEnabled = clientId == 1; // quero depois trocar esse enabled por verificar estado ao inves de verificar essa booleana (Cainã)

        //instantiate an enormous part of the game
        gameField = new GameField(playersQuantity);
        
        //remember you once did something that was right: (if it's still working)
        log("ClientId: "+ clientId);
        log("playersQuantity: "+playersQuantity);

        // start playing
        isItMyTurn();
    }
    void isItMyTurn(){
        boolean is = gameField.getPlayers().get(0).id == clientId;
        log("0 id: "+gameField.getPlayers().get(0).id+"; clientId: "+clientId);
        if (is){
            if(actionsQty >= 3){
                gameField.getPlayers().get(0).addCards(gameField.getDeck());
                log("cards in hand: "+gameField.getPlayers().get(0).getHand().getCards().size());
            }
            actionsEnable(true);
            currentState = State.SelectCard;
        } else {
            currentState = State.WaitForPlays;
        }
        log("Current State -> "+currentState);
    }
    public void buyCards(){ //talvez não precise desse método
        this.gameField.setDeck(this.gameField.getPlayers().get(0).addCards(this.gameField.getDeck()));
    }
    public void endTurn(){
        actionsQty = 3;
        Player currentPlayer = gameField.getPlayers().remove(0);
        ArrayList<Player> players = gameField.getPlayers();
        players.add(currentPlayer);
        this.gameField.setPlayers(players);
        currentState = State.WaitForPlays;
        PlayerPacket playerPacket = new PlayerPacket(this.lastUsedCard, this.gameField);
        SerializablePacket serializablePacket = new SerializablePacket(playerPacket);
        netGamesInterface.sendPlay(serializablePacket);
    }
    
    public void actionsEnable(boolean a){ //eu acho que eh melhor saber pelo estado se as ações estão habilitadas ou não, na verdade (Cainã)
        actionsEnabled = a;
    }
   
    public void receivePlay(PlayerPacket playerPacket){
        copyGameField(playerPacket.getGameField());
        copyLastCardUsed(playerPacket.getLastUsedCard());
        isItMyTurn();
        if (gameEnded()){
            //maneira provisoria de acabar a partida. O ideal seria o caso de uso endMatch
            netGamesInterface.finalizarPartidaComErro("A partida acabou. O último a jogar venceu!");
        }
    }

    public void copyGameField(GameField gf){
        this.gameField = gf;
    }
    
    public void copyLastCardUsed(Card c){
        this.lastUsedCard = c;
    }

    public boolean gameEnded(){  
        for (Player player : gameField.getPlayers()) {
            int completedGroups = 0;

            for (PropertyGroup pg : player.getZone().getProperties()) { 
                //log("at gameEnded(): pgColor: "+pg.getColor()+"; needed: "+pg.getNeeded()+"; qty: "+pg.getPropQty());
                if (pg.getPropQty() >= pg.getNeeded()) {
                    completedGroups++;
                }
            }
            if (completedGroups >= 3) {
                return true;
            }
        }
        return false;
    }

    //public PlayerPacket calculatePlay(PlayerPacket p){}   // acho que n precisa

    public boolean isIconSelected(int id){
        return id == selectedPlayerId;
    }

    //return player at position 0 index for the UI to calculate arrow position
    public int calculateArrowPosition(){
        return gameField.getPlayers().get(0).id;
    }

    public int getPlayersMoney(int id){
        Player player = gameField.getPlayers()
            .stream().filter((p)->(p.getId() == id))
            .collect(Collectors.toList())
            .get(0);
        if(player != null) {
            return player.getMoney();
        } 
        return -1;
    }
    public int howManyPropCards(int id) {
        // Feito :D
        Player player = gameField.getPlayers()
            .stream().filter((p)->(p.getId() == id))
            .collect(Collectors.toList())
            .get(0);
        if(player != null) {
            int totalCards = 0;
            for (PropertyColor pc : PropertyColor.values()) {   // n deveria ser foreach ?
                totalCards += player.howManyPropCards(pc);
            }
            return totalCards;
        } 
        return 0;
    }
    public String witchCardIsThis(int index){ //index could be called posInHand
        return gameField.players.get(0).wichCardIsThis(index);
    }
    public String getLastUsedCard(){
        return lastUsedCard.label;
    }
    public String getPlayerID(){
        return ""+clientId; //acho que pode mudar o tipo de retorno para int
    }
    public boolean doesEndTurnButtonAppear(){
        return currentState == State.SelectCard;
    }
    public void useCard(int index){
        Card card = gameField.players.get(0).hand.getCards().get(index);
        setLastUsedCard(card);

        currentState = State.Store;
        //playerInterface.useOrStoreCard(); TODO: CHAMAR ESSE METODO QUANDO ESTIVER IMPLEMENTADO

    }
    public void setLastUsedCard(Card card){
        this.lastUsedCard = card;
    }
    public void store(boolean willStore){ //DIAGRAMA: parametro nao estava no diagrama de classes
        Player currentPlayer = gameField.getPlayers().get(0);
        int money = currentPlayer.getZone().getBank();
        money += lastUsedCard.getValue();
        currentPlayer.getZone().setBank(money);
        decreaseActions();
    }

    // when I wanna go to the selectTatgetPlayer state and I just selected my propertyColor (if needed)
    public void selectTargetPlayer(PropertyColor c){ //could be called selectYourProperty
        //saving myProperty selected
        ArrayList<PropertyGroup> properties = gameField.players.get(0).zone.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i).getColor() == c){
                yourProperty = properties.get(i);
                i = properties.size();
            }
        }
        //updating state
        currentState = State.SelectTargetPlayer;
        ArrayList<State> needed = lastUsedCard.neededStates;
        boolean needs = needed.contains(State.SelectTargetPlayer);
        if ( ! needs ){
            selectTargetProperty(""+clientId); //select myself as target player once that it target isn't needed
        }
    }
    // means i wanna go to selectTargetProperty state and I just selected my string targetPlayer==id (if needed)
    public void selectTargetProperty(String id){ //maybe it could be always integer or always String
        // saving player target selected
        selectedPlayerId = Integer.parseInt(id);

        //update state
        ArrayList<State> needed = lastUsedCard.neededStates;
        boolean needs = needed.contains(State.SelectTargetProperty);
        if ( ! needs){
            setTargetProperty(PropertyColor.joker); //select joker as targetProperty, since it isn't needed
        }
    }

    public void setTargetProperty(PropertyColor c){
        //saving targetProperty selected
        ArrayList<PropertyGroup> properties = gameField.players.get(0).zone.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i).getColor() == c){
                targetsProperty = properties.get(i);
                i = properties.size();
            }
        }

        //TODO: deveria chamar applyCardEffect() agora
    }

    public void decreaseActions(){ 
        actionsQty -= 1;
        if (actionsQty > 0){
            currentState = State.SelectCard;
            //playerInterface.sendPlay();
        } else {
            currentState = State.EndTurn;
            endTurn();
        }
    }
    public void setSelectedPlayer(int pid) {
        selectedPlayerId = pid;
    }
}