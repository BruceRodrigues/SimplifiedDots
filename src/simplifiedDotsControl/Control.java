package simplifiedDotsControl;

import simplifiedDots.Game;
import simplifiedDots.InvalidMovementException;
import simplifiedDots.Movement;
import simplifiedDots.Position;
import simplifiedDots.Stripe;
import simplifiedDotsGUI.Window;

public class Control implements IControl{
	
	private final int GAME_SIZE;
	private Window view;
	private Game game;
	
	public Control() {
		this.GAME_SIZE = 5;
	}
	
	public void initialize() {
		this.game = new Game(this.GAME_SIZE);
		this.view = new Window(this.GAME_SIZE);
		this.view.setVisible(true);
		this.view.addListener(this);
	}
	
	
	@Override
	public void onClick(Position a, Position firstPosition) {
		try {
			//TODO convert view's position to game's position
			game.play(new Movement(a,Stripe.DOWN)); 
		} catch (InvalidMovementException ex) {
			this.view.invalidMovement(a);
		}
	}
	
	public void renderGame() {
		
	}
	
}
