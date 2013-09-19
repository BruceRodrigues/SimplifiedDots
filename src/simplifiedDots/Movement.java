package simplifiedDots;

public class Movement {

	private Position _position;
	private Stripe _stripe;
	
	public Movement(Position p, Stripe s) {
		_position = p;
		_stripe = s;
	}
	
	public Position position() { return _position; }
	public Stripe stripe() { return _stripe; }
	
}
