package br.ufsc.game.gamelogic;

import java.util.ArrayList;

import br.ufsc.game.network.NetGamesInterface;
import br.ufsc.game.network.PlayerPacket;
import br.ufsc.game.network.SerializablePacket;

public class FSMGame {
    protected State currentState;
    protected GameField gameField;
    protected int actionsQty;
    protected int yourProperty;
    protected int targetsProperty;
    protected boolean store;
    protected boolean use;
    protected Card lastUsedCard;
    protected int clientId;
    protected int selectedPlayerId;
    protected boolean actionsEnabled;
    protected PlayerInterface playerInterface;
    protected NetGamesInterface netGamesInterface;
    protected Player you;

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
        currentState = State.Start;
        lastUsedCard = new MoneyCard(666, "back", 666, new ArrayList<State>());
        actionsEnabled = clientId == 1; // quero depois trocar esse enabled por verificar estado ao inves de verificar essa booleana (Cainã)

        //instantiate an enormous part of the game
        gameField = new GameField(playersQuantity);
        
        for (int i = 0; i < gameField.players.size(); i++ ){
            if(gameField.players.get(i).getId()==clientId){
                you = gameField.players.get(i); i = 99999; //break loop
            }
        }

        //remember you once did something that was right: (if it's still working)
        log("ClientId: "+ clientId);
        log("playersQuantity: "+playersQuantity);

        // start playing
        isItMyTurn();
    }
    void isItMyTurn(){
        boolean is = gameField.getPlayers().get(0).id == clientId;
        log("is it my turn? 0 id: "+gameField.getPlayers().get(0).id+"; clientId: "+clientId);
        if (is){
            if(actionsQty >= 3){
                buyCards();
            }
            actionsEnable(true);
            currentState = State.SelectCard;
        } else {
            currentState = State.WaitForPlays;
        }
        log("Current State -> "+currentState);
    }
    public void buyCards(){
        this.gameField.getPlayers().get(0).addCards(this.gameField.getDeck());
        log("cards in hand: "+gameField.getPlayers().get(0).getHand().getCards().size());
    }
    public void endTurn(){
        //update state
        setState(State.WaitForPlays);
        actionsQty = 3;

        //reorder players
        Player currentPlayer = gameField.getPlayers().remove(0);
        ArrayList<Player> players = gameField.getPlayers();
        players.add(currentPlayer);
        this.gameField.setPlayers(players);

        sendPlay();
    }
    
    public void actionsEnable(boolean a){ //eu acho que eh melhor saber pelo estado se as ações estão habilitadas ou não, na verdade (Cainã)
        actionsEnabled = a;
    }
   
    public void receivePlay(PlayerPacket playerPacket){
        log("receivedPlay");
        copyGameField(playerPacket.getGameField());
        copyLastCardUsed(playerPacket.getLastUsedCard());

        log("actionsQty: "+actionsQty);
        log("lastUsedCard: "+lastUsedCard.getLabel());
        log("yourMoney: "+you.getMoney());
        log("deck: "+gameField.getDeck());
        log("deckSize : "+gameField.getDeck().getCards().size());

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
    public int getClientIDofPlayer0(){
        return gameField.getPlayers().get(0).id;
    }

    public int getPlayersMoney(int id){
        for (int i = 0; i < gameField.getPlayers().size(); i++){
            Player p = gameField.getPlayers().get(i);
            if (p.getId() == id){
                return p.getMoney();
            }
        }
        return -1;
    }
    Player getPlayerById(int id){
        Player player = gameField.getPlayers().get(0);
        for (int i = 0; i < gameField.getPlayers().size(); i++){
            Player p = gameField.getPlayers().get(i);
            if (p.getId() == selectedPlayerId){
                player = p; i = 999999; //break loop
            }
        }
        return player;
    }
    public int howManyPropCards(int i) {
        Player player = getPlayerById(selectedPlayerId);
        PropertyGroup pg =player.getZone().getProperties().get(i);
        int propQty = player.getZone().getProperties().get(i).getPropQty();
        boolean completa = propQty >= pg.getNeeded();
        //return 50 extra if is complete, its like a code to not make other method
        propQty = completa ? propQty+50 : propQty;
        return propQty;
    }
    public String witchCardIsThis(int index){ //index could be called posInHand
        return you.wichCardIsThis(index);
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
        if( currentState != State.SelectCard ){ return; }
        if(index >= gameField.players.get(0).hand.getCards().size()) { return; }

        ArrayList<Card> cardsInHand = gameField.players.get(0).hand.getCards();

        Card card = cardsInHand.get(index);
        setLastUsedCard(card);

        cardsInHand.remove(index);

        setState(State.UseOrStore);
        playerInterface.useOrStoreCard();

    }
    public void setLastUsedCard(Card card){
        this.lastUsedCard = card;
    }
    public void store(boolean willStore){
        if (willStore){
            Player currentPlayer = gameField.getPlayers().get(0);
            int money = currentPlayer.getZone().getBank();
            money += lastUsedCard.getValue();
            currentPlayer.getZone().setBank(money);
            decreaseActions();
        } else {
            setState(State.SelectYourProperty);
            if ( ! lastUsedCard.getNeededStates().contains(State.SelectYourProperty)){
                selectYourProperty(PropertyColor.joker);//im selecting an arbitrary color
            }
        }
    }

    public void selectYourProperty(PropertyColor c){
        if (currentState != State.SelectYourProperty) return;

        //saving myProperty selected
        ArrayList<PropertyGroup> properties = gameField.players.get(0).zone.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i).getColor() == c){
                //yourProperty = properties.get(i);
                yourProperty = i;
                i = properties.size();
            }
        }
        //updating state
        setState(State.SelectTargetPlayer);

        //if it does not need next state, call next state without waiting for input
        ArrayList<State> needed = lastUsedCard.neededStates;
        boolean needs = needed.contains(State.SelectTargetPlayer);
        if ( ! needs ){
            selectTargetPlayer(clientId); //select myself as target player once that it target isn't needed
        }
    }
    // means i wanna go to selectTargetProperty state and I just selected my string targetPlayer==id (if needed)
    public void selectTargetPlayer(int id){ //maybe it could be always integer or always String
        if (currentState != State.SelectTargetPlayer) return;
        
        // saving player target selected
        selectedPlayerId = id;

        //update state
        setState(State.SelectTargetProperty);
        ArrayList<State> needed = lastUsedCard.neededStates;
        boolean needs = needed.contains(State.SelectTargetProperty);
        if ( ! needs){
            selectTargetProperty(PropertyColor.joker); //select joker as targetProperty, since it isn't needed
        }
    }

    public void selectTargetProperty(PropertyColor c){
        if (currentState != State.SelectTargetProperty) return;
        
        //saving targetProperty selected
        ArrayList<PropertyGroup> properties = gameField.players.get(0).zone.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i).getColor() == c){
                //targetsProperty = properties.get(i);
                targetsProperty = i;
                i = properties.size();
            }
        }

        PlayerPacket playerPacket = new PlayerPacket(lastUsedCard, gameField);
        lastUsedCard.applyEffect(playerPacket, targetsProperty, yourProperty, selectedPlayerId);

        decreaseActions();
    }

    public void decreaseActions(){ 
        actionsQty -= 1;
        if (actionsQty > 0){
            setState(State.SelectCard);
            sendPlay();
        } else {
            endTurn();
        }
    }
    public void setSelectedPlayer(int pid) {
        if (pid <= gameField.getPlayers().size()){
            selectedPlayerId = pid;
        }
    }

    void setState(State state){
        log("Update state from *"+currentState+"* to *"+state+"*.");
        currentState = state;
    }
    void sendPlay(){
        PlayerPacket playerPacket = new PlayerPacket(this.lastUsedCard, this.gameField);
        SerializablePacket serializablePacket = new SerializablePacket(playerPacket);
        netGamesInterface.sendPlay(serializablePacket);
        //when receive play, it checks for gameEnded, and to make evident serialization errors, call:
        netGamesInterface.receberJogada(serializablePacket);
    }

	public int getClientId() {
		return clientId;
    }
    
    public State getState(){
        return currentState;
    }

	public int getHouseQty(int i) {
        Player selectedPlayer = getPlayerById(selectedPlayerId);
		return selectedPlayer.getZone().getProperties().get(i).getHouseQty();
    }
    public int getHotelQty(int i) {
        Player selectedPlayer = getPlayerById(selectedPlayerId);
		return selectedPlayer.getZone().getProperties().get(i).getHotelQty();
	}
}