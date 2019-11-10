package br.ufsc.game.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import br.ufsc.game.network.PlayerPacket;

public class FSMGame {
    //comment means it was not implemented yet
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

    public void buyCards(){
        this.gameField.setDeck(this.gameField.getPlayers().get(0).addCards(this.gameField.getDeck()));
    }
    
    public void endTurn(){
        Player currentPlayer = gameField.getPlayers().remove(0);
        ArrayList<Player> players = gameField.getPlayers();
        players.add(currentPlayer);
        this.gameField.setPlayers(players);
        currentState = State.WaitForPlays;
        //playerInterface.endTurn(); CHAMAR QUANDO ESTIVER IMPLEMENTADO
    }
    
    public void actionsEnable(boolean a){ //eu acho que eh melhor saber pelo estado se as ações estão habilitadas ou não, na verdade (Cainã)
        actionsEnabled = a;
    }
   
    public void updateInformation(PlayerPacket pc){
        this.gameField = pc.getGameField();
        this.lastUsedCard = pc.getLastUsedCard();
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
                if (pg.getNeeded() == pg.getPropCards().size()) {
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

    public boolean isIconSelected(String id){
        return id.equals(""+selectedPlayerId);
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

        //TODO: should applyCardEffect now
    }

    public void decreaseActions(){ 
        actionsQty -= 1;
        if (actionsQty > 0){
            currentState = State.SelectCard;
            //enviarJogada()
        } else {
            currentState = State.EndTurn;
            endTurn();
        }
    }
    public void setSelectedPlayer(int pid) {
        selectedPlayerId = pid;
    }
    boolean isItMyTurn(){
        return gameField.getPlayers().get(0).id == clientId;
    }
}