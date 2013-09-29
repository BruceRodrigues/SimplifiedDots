package simplifiedDotsControl;

import simplifiedDots.Cell;
import simplifiedDots.Game;
import simplifiedDots.InvalidMovementException;
import simplifiedDots.Movement;
import simplifiedDots.Position;
import simplifiedDots.Stripe;
import simplifiedDotsGUI.Window;
import simplifiedDotsIA.IAPlayer;

public class Control implements IControl{
	
	private final int GAME_SIZE;
	private Window view;
	private Game game;
	
	public Control() {
		this.GAME_SIZE = 3;
		this.initialize();
	}
	
	private void initialize() {
		this.game = new Game(this.GAME_SIZE);
		this.view = new Window(this.GAME_SIZE);
		this.view.setVisible(true);
		this.view.addListener(this);
		
		//Players
		IAPlayer p1 = new IAPlayer("pc", this.game);
		IAPlayer p2 = new IAPlayer("pc2", this.game);
		this.game.addPlayer(p1);
		this.game.addPlayer(p2);
	}
	
	
	@Override
	public void onClick(Position a, Position firstPosition) {
		try {
			//TODO convert view's position to game's position
			game.play(this.dotToMovement(a, firstPosition)); 
			this.renderGame();
		} catch (InvalidMovementException ex) {
			this.view.invalidMovement(a);
		}
	}
	
	public void renderGame() {
		for (int i = 0; i < this.GAME_SIZE; i++) {
			for (int j =0; j < this.GAME_SIZE; j++) {
				Cell cell = this.game.getCell(new Position(i,j));
				Position b = new Position(i,j);;
				if (cell.isFilled(Stripe.UP)) {
					this.view.drawLine(b,cellToDot(Stripe.UP,b));
				} if(cell.isFilled(Stripe.RIGHT)) {
//					this.view.drawLine(new Position(i,j),b);
				} if (cell.isFilled(Stripe.DOWN)) {
					b = new Position(i-1,j);
//					this.view.drawLine(new Position(i,j),b);
				} if (cell.isFilled(Stripe.LEFT)) {
					this.view.drawLine(b,cellToDot(Stripe.LEFT,b));
				}
			}
		}
	}
	
	public Position cellToDot(Stripe stripe, Position p) {
		Integer x = null;
		Integer y = null;
		switch(stripe) {
			case UP:
				y = p.y();
				x = p.x()+1;
				break;
			case RIGHT:
				break;
			case DOWN:
				break;
			case LEFT:
				x = p.x();
				y = p.y()+1;
				break;
			default:
				break;
		}
		return new Position(x,y);
	}
	
	public Movement dotToMovement(Position a, Position b) {
		Stripe stripe = null;
		Integer x = null;
		Integer y = null;
		x = Math.min(a.x(), b.x());
		y =  Math.min(a.y(), b.y());
		if(a.x() == b.x()) {
			stripe = Stripe.LEFT;
		} else if (a.y() == b.y()) {
			stripe = Stripe.UP;
		}
		if(a.x() == this.GAME_SIZE) {
			x = this.GAME_SIZE -1;
			stripe = Stripe.RIGHT;
		}
		if(a.y() == this.GAME_SIZE) {
			y = this.GAME_SIZE -1;
			stripe = Stripe.DOWN;
		}
		Position position = new Position(x,y);
		return new Movement(position,stripe);
	}
	
	//TODO
	//Just for test
	public Cell getCell(Position p) {
		return this.game.getCell(p);
	}
	
}
