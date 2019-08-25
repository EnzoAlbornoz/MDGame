package br.ufsc.game.engine.states;

import java.util.Stack;

// Singleton pattern
public class GameSceneManager {
	
	// Singleton Holder
	private static GameSceneManager singleton;
	private Stack<GameScene> currentStates;
	// Singleton Constructor
	private GameSceneManager() {
		currentStates = new Stack<>();
	}
	
	// Factory Function
	public static synchronized GameSceneManager getInstance() {
		if(singleton == null) {
			singleton = new GameSceneManager();
		}
		return singleton;
	}
	
	// Class Interface
	
	public GameScene popState() {
		currentStates.peek().exiting();
		GameScene ret = currentStates.pop();
		if(!currentStates.isEmpty()) {
			currentStates.peek().revealed();
		}
		return ret;
	}
	
	public GameScene peekState() {
		return currentStates.peek();
	}
	
	public void pushState(GameScene state) {
		if(!currentStates.isEmpty()) {
			currentStates.peek().obscuring();
		}
		currentStates.push(state);
		state.entering();
	}
	
	public void switchState(GameScene state) {
		currentStates.peek().exiting();
		currentStates.pop();
		currentStates.push(state);
		state.entering();
	}
//	public void render(Graphics2D g) {
//		
//	}
//	
//	public void update() {
//		
//	}
}
