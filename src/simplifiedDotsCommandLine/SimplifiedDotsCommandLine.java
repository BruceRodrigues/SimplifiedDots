package simplifiedDotsCommandLine;

import java.io.IOException;

import simplifiedDots.Game;
import simplifiedDots.InvalidMovementException;
import simplifiedDots.Player;
import simplifiedDotsIA.IAPlayer;
import simplifiedDotsIA.IAPlayerPruning;

public class SimplifiedDotsCommandLine {

	public static void main(String[] args) {
		System.out.print("Escreva seu nome: ");
		byte[] buffer = new byte[100];
		try {
			System.in.read(buffer);
		} catch (IOException e) {
			System.out.println("Erro ao ler seu nome.");
			e.printStackTrace();
			System.exit(1);
		}
		String name = new String(buffer);

		System.out.print("Jogar contra [A]MinMax ou [B]MinMax com poda? ");
		try {
			System.in.read(buffer);
		} catch (IOException e) {
			System.out.println("Erro ao ler tipo de jogador");
			e.printStackTrace();
			System.exit(1);
		}
		char playerType = new String(buffer).toUpperCase().charAt(0);
		
		Game game = new Game(2);
		
		Player player1 = new CommandLinePlayer(name);
		Player player2 = null;
		if(playerType == 'A')
			player2 = new IAPlayer("IA Player", game);
		else
			player2 = new IAPlayerPruning("IA Player", game);
		
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		while(!game.isOver()) {
			game.print();
			try {
				game.play(game.currentPlayer().getNextMovement());
			} catch (InvalidMovementException e) {
				e.printStackTrace();
			}
			System.out.println("##########################################");
		}
		game.print();
		System.out.println("##########################################");
		System.out.println("O jogo terminou.");
	}
	
}
