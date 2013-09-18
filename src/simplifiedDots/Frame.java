package simplifiedDots;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	public List<Board> createAllPossibilities(Board board) {
		List<Board> possibilities = new ArrayList<Board>();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				for (int pos = 0; pos < 4; pos++) {
					Board cloneBoard = board.clone();
					Stripe stripe = Stripe.UP;
					switch(pos) {
						case 0: stripe = Stripe.UP; break;
						case 1: stripe = Stripe.RIGHT; break;
						case 2: stripe = Stripe.DOWN; break;
						case 3: stripe = Stripe.LEFT; break;
					}
					cloneBoard.mark(new Position(i,j), stripe, 'x');
					possibilities.add(cloneBoard);
				}
			}
		}
		return possibilities;
	}

}
