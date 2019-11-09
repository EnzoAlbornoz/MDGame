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

    public void setSelectedPlayer(int pid) {
        selectedPlayerId = pid;
    }

    public void buyCards(){
        this.gameField.setDeck(this.gameField.getPlayers().get(0).addCards(this.gameField.getDeck()));
    }
    
    public void endTurn(){
        Player currentPlayer = gameField.getPlayers().remove(0);
        ArrayList<Player> players = gameField.getPlayers();
        players.add(currentPlayer);
        this.gameField.setPlayers(players);
    }
    
    //public void disableActions(boolean a){} // I suggest change name to ActionsEnable, since there is a boolean parameter
   
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
    //public boolean isIconSelected(String id){}
    //public int calculateArrowPosition() informa a interface onde ela deve posicionar a seta (em pixels de y), de acordo com quem eh a vez

    public int getPlayersMoney(int id){
        Player player = gameField.getPlayers()
            .stream().filter((p)->(p.getId() == id))
            .collect(Collectors.toList())
            .get(0);
        if(player != null) {
            return player.getMoney();
        } 
        return 0;
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
    //public string witchCardIsThis(int index){} acho q repassa pro player
    public String getLastUsedCard(){
        return lastUsedCard.label;
    }
    public String getPlayerID(){
        return ""+clientId; //acho que pode mudar o tipo de retorno para int
    }
    public boolean doesEndTurnButtonAppear(){
        return currentState == State.SelectCard;
    }
    //public void useCard(int index){}
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
    public void selectTargetPlayer(PropertyColor c){
        //yourProperty = c;
        ArrayList<PropertyGroup> properties = gameField.players.get(0).zone.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i).getColor() == c){
                yourProperty = properties.get(i);
                i = properties.size();
            }
        }
        currentState = State.SelectTargetPlayer;
        
        //TODO: verificar se a carta precisa do próximo estado, para pular ele ou não, e talz... se puder deixem q eu faço (Cainã)
    }

    //public void selectTargetProperty(String id){} // means i wanna go to selectTargetProperty state and I just selected my string targetPlayer==id (if needed)
    //public void setTargetProperty(PropertyColor c){}
    public void decreaseActions(){ 
        actionsQty -= 1;
        if (actionsQty > 0){
            currentState = State.SelectCard;
            //enviarJogada()
        } else {
            currentState = State.EndTurn;
            //endTurn() //jogada serah enviada no endTurn
        }
    }
    //void setSelectedPlayer(int pid){}
    boolean isItMyTurn(){
        return gameField.getPlayers().get(0).id == clientId;
    }
}