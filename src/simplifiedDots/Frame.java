package simplifiedDots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame {
	
	
	public List<HashMap<Position,Cell>> createAllPossibilities(HashMap<Position,Cell> initialPosition) {
		List<HashMap<Position,Cell>> possibilities = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			for (Map.Entry<Position,Cell> entry : initialPosition.entrySet()) {
				Cell cell = entry.getValue().clone();
				Position position = entry.getKey();
				if(!cell.isFilled()) {
					switch(i) {
					case 0:
						if(!cell.isFilled(Stripe.UP)) {
							HashMap<Position,Cell> possibility = new HashMap<Position,Cell>(initialPosition);
							possibility.get(position).mark(Stripe.UP, 'a');
							possibilities.add(possibility);
						}
					case 1:
						if(!cell.isFilled(Stripe.RIGHT)) {
							HashMap<Position,Cell> possibility = new HashMap<Position,Cell>(initialPosition);
							possibility.get(position).mark(Stripe.RIGHT, 'a');
							possibilities.add(possibility);
						}
					case 2:
						if(!cell.isFilled(Stripe.DOWN)) {
							HashMap<Position,Cell> possibility = new HashMap<Position,Cell>(initialPosition);
							possibility.get(position).mark(Stripe.DOWN, 'a');
							possibilities.add(possibility);
						}
					case 3:
						if(!cell.isFilled(Stripe.LEFT)) {
							HashMap<Position,Cell> possibility = new HashMap<Position,Cell>(initialPosition);
							possibility.get(position).mark(Stripe.LEFT, 'a');
							possibilities.add(possibility);
						}
					}
				}
			}
		}
		return possibilities;
	}

}
