package br.ufsc.game.engine.logic;

import java.awt.event.MouseEvent;

import br.ufsc.game.engine.inputs.Mouse;
import br.ufsc.game.engine.interfaces.GameAction;
import br.ufsc.game.engine.interfaces.Updatable;

/**
 * GameButton
 */
public class GameButton extends GameImage implements Updatable {
	// Variables
		// Public
		// Private
	protected GameAction btnAction;
	// Constructor
	public GameButton(String filePath) {
		super(filePath);
		this.btnAction = new GameAction(){
		
			@Override
			public void doAction(Object[] args) {
				System.out.println("Not Implemented yet!");
			}
		};
	}

	public GameButton(String filePath, GameAction onClickAction) {
		super(filePath);
	}
	// Interface
	public void setOnClick(GameAction action) {
		this.btnAction = action;
	}

	public void onClick() {
		this.btnAction.doAction(null);
	}

	public void update() {
		if (Mouse.getInstance().isMouseButtonPressing(MouseEvent.BUTTON1)) {
			if(Mouse.getInstance().isOverGameObject(this)) {
				this.onClick();
			}
		}
	}
	// Methods
	
}