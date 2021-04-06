package com.ss.riandougherty.training.day_one.two;

import java.util.Scanner;

public final class GuessProgram {
	private GuessProgram() {}
	
	public static void main(final String[] args) {
		GuessGame game = new GuessGame(new IntRange(1, 100), 5, 10);
		
		final Scanner sc = new Scanner(System.in);
		
		GameState gs;
		int answer = 0;
		
		do {
			int guess;
			
			guess = sc.nextInt();
			// ide complains if this is un-initialized.
			// Even though it is impossible for the game state to be over and the getAnswer() function throws an exception.
			
			try {
				gs = game.makeGuess(guess);
				
				if(!game.isGameRunning()) { 
					answer = game.getAnswer();
				}
			} catch(final Exception e) {
				System.err.println("ERROR: " + e.getMessage() + "\nExiting...");
				
				sc.close();
				System.exit(1);
				return;
			}
			
			final StringBuilder sb = new StringBuilder();
			
			switch(gs) {
			case GAME_RUNNING:
				sb.append("You guessed incorrectly. You have ");
				sb.append(game.getRemainingGuesses());
				sb.append(" guesses left.");
				
				break;
			case GAME_OVER_LOSE:
				sb.append("Sorry. It was ");
				sb.append(answer);
				sb.append(" :\\.");
				
				break;
			case GAME_OVER_WIN:
				sb.append("You won! ");
				
				if(guess == answer) {
					sb.append("A perfect guess of ");
				} else {
					sb.append("Your answer was within ");
					
					int diff = Math.abs(answer - guess);
					sb.append(diff);
					sb.append(" number");
					if(diff > 1) {
						sb.append('s');
					}
					
					sb.append(" of the answer: ");
				}
				sb.append(answer);
				sb.append('.');
				
				break;
			}
			
			System.out.println(sb.toString());
		} while(game.isGameRunning());
		
		sc.close();
	}
}
