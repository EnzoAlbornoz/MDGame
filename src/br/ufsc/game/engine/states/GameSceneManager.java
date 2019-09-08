package br.ufsc.game.engine.states;

import java.util.Stack;

// Singleton pattern
public class GameSceneManager {
	
	// Singleton Holder
	private static GameSceneManager singleton;
	private Stack<GameScene> currentState;
	// Singleton Constructor
	private GameSceneManager() {
		currentState = new Stack<>();
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
		currentState.peek().exiting();
		GameScene ret = currentState.pop();
		if(!currentState.isEmpty()) {
			currentState.peek().revealed();
		}
		return ret;
	}
	
	public GameScene peekState() {
		return currentState.peek();
	}
	
	public void pushState(GameScene state) {
		if(!currentState.isEmpty()) {
			currentState.peek().obscuring();
		}
		currentState.push(state);
		state.entering();
	}
	
	public void switchState(GameScene state) {
		currentState.peek().exiting();
		currentState.pop();
		currentState.push(state);
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
