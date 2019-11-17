package br.ufsc.game.gamelogic;

import java.util.ArrayList;

/**
 * Card
 */
public abstract class Card {

	public enum Type {
		moneyCard, propertyCard, rentCard, actionCard;
	}

	// Variables
	protected int id;
	protected String label;
	protected int value;
	protected ArrayList<State> neededStates;
	protected Type type;

	// Constructor
	public Card(int id,String label,int value, State[] neededStates) {
		this.id = id;
		this.label = label;
		this.value = value;
		this.neededStates = new ArrayList<State>(neededStates.length);
		for (State state : neededStates) {
			this.neededStates.add(state);
		}
	}

	public Card(int id, String label, int value, ArrayList<State> neededStates){
		this(id,label,value,neededStates.toArray(new State[0]));
	}
	// Interface
	public ArrayList<State> getNeededStates() {
		return neededStates;
	}

	public int getValue() {
		return this.value;
	}

	public int getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}

	public boolean hasState(State state) {
		return this.neededStates.contains(state);
	}

	public Type getType(){
		return type;
	}

	public abstract void applyEffect(int targetProperty,int yourProperty, int selectedPlayer);
	// Methods
}