package simplifiedDotsCommandLine;

import java.io.IOException;

import simplifiedDots.Movement;
import simplifiedDots.Player;
import simplifiedDots.Position;
import simplifiedDots.Stripe;

public class CommandLinePlayer implements Player {
	
	private String _name;
	
	public CommandLinePlayer(String name) {
		_name = name;
	}

	public char getInitial() {
		return _name.charAt(0);
	}

	public String getName() {
		return _name;
	}

	public Movement getNextMovement() {
		boolean error = false;
		int x = 0;
		int y = 0;
		Stripe stripe = null;
		
		do {
			error = false;
			System.out.print(">");
			
			byte[] buffer = new byte[100];
			try {
				System.in.read(buffer);
			} catch (IOException e) {
				System.out.println("Erro ao ler seu nome.");
				e.printStackTrace();
				System.exit(1);
			}
			
			String command = new String(buffer);
			String[] commands = command.split(" ");
			try {
				x = Integer.parseInt(commands[0]);
				y = Integer.parseInt(commands[1]);
				switch(commands[2].toUpperCase().charAt(0)) {
				case 'U':
					stripe = Stripe.UP;
					break;
				case 'D':
					stripe = Stripe.DOWN;
					break;
				case 'L':
					stripe = Stripe.LEFT;
					break;
				case 'R':
					stripe = Stripe.RIGHT;
					break;
				default:
					throw new Exception();
				}
			} catch(Exception e) {
				System.out.println(
					"ERRO: Formato esperado: 'x y [UP|DOWN|LEFT|RIGHT]'");
				error = true;
			}
		} while(error);
		
		return new Movement(new Position(x, y), stripe);
	}

}
