package br.ufsc.game.gamelogic;

/**
 * State
 */
public enum State {

	IsItMyTurn,
	BuyCards,
	SelectCard,
	UseCard,
	UseOrStore,
	SelectYourProperty,
	SelectTargetPlayer,
	SelectTargetProperty,
	ApplyCardEffects,
	EndTurn,
	WaitForPlays

}