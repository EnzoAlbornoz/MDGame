package br.ufsc.game.network;

import java.util.ArrayList;

import br.ufsc.game.gamelogic.Card;
import br.ufsc.game.gamelogic.GameField;
import br.ufsc.game.gamelogic.MoneyCard;
import br.ufsc.game.gamelogic.State;
import br.ufsc.inf.leobr.cliente.Jogada;

public class SerializablePacket implements Jogada {
    
    protected int id;
	protected String label;
	protected int value;
    protected ArrayList<State> neededStates;
    int playersQty;
    
    public SerializablePacket(PlayerPacket p){
        Card c = p.getLastUsedCard();
        id = c.getId();
        label = c.getLabel();
        value = c.getValue();
        neededStates = c.getNeededStates();
        playersQty = p.gameField.getPlayers().size();
    }

    public PlayerPacket generatePlayerPacket(){

        GameField gameField = new GameField(playersQty); // essa linha eh 100% provisoria.

        Card lastUsedCard = new MoneyCard(id, label, value, neededStates); // essa tah 100% pronta (Cainã)

        PlayerPacket p = new PlayerPacket(lastUsedCard, gameField);

        return p;
    }
}