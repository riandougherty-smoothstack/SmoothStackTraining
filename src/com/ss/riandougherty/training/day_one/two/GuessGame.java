package com.ss.riandougherty.training.day_one.two;

import java.util.concurrent.ThreadLocalRandom;

public final class GuessGame {
	private final IntRange range;
	private final int maxNumGuesses, tolerance;
	
	private int currentNGuesses, answer;
	private GameState gameState;
	
	/**
	 * Resets the game.
	 */
	public void reset() {
		currentNGuesses = 0;
		gameState = GameState.GAME_RUNNING;
		
		answer = ThreadLocalRandom.current().nextInt(range.getLowerBound(), range.getUpperBound() + 1);
	}
	
	/**
	 * @param range - the @IntRange of guesses allowed.
	 * @param maxNumGuesses - the maximum number of guesses allowed to be made before the game ends.
	 * @param tolerance - Range tolerance the user's guess is able to fall within without ending the game. 
	 */
	public GuessGame(final IntRange range, final int maxNumGuesses, final int tolerance) {
		this.range = range;
		this.maxNumGuesses = maxNumGuesses;
		this.tolerance = tolerance;
		
		reset();
	}
	
	/**
	 * @return The @IntRange of guesses allowed.
	 */
	public IntRange getRange() {
		return range;
	}
	
	/**
	 * @return Number of guesses made already.
	 */
	public int getCurrentNumberGuesses() {
		return currentNGuesses;
	}
	
	/**
	 * @return Number of guesses able to be made before the game ends.
	 */
	public int getMaxNumGuesses() {
		return maxNumGuesses;
	}
	
	/**
	 * @return Remaining number of guesses.
	 */
	public int getRemainingGuesses() {
		return maxNumGuesses - currentNGuesses;
	}
	
	/**
	 * @return Range tolerance the user's guess is able to fall within without ending the game.
	 */
	public int getTolerance() {
		return tolerance;
	}
	
	/**
	 * @return The answer if the game is over.
	 * @throws IllegalGameStateException if the game is not over.
	 */
	public int getAnswer() throws IllegalGameStateException {
		if(gameState == GameState.GAME_RUNNING) {
			throw new IllegalGameStateException("The game is not over.");
		}
		
		return answer;
	}
	
	/**
	 * @return The current GameState.
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * @return true if game is running, false otherwise.
	 */
	public boolean isGameRunning() {
		return gameState == GameState.GAME_RUNNING;
	}
	
	/**
	 * 
	 * @param guess - guess to make.
	 * @return The current @GameState.
	 * @throws IllegalGameStateException if game is not running.
	 * @throws IllegalArgumentException if guess is out of range.
	 */
	public GameState makeGuess(final int guess) throws IllegalGameStateException, IllegalArgumentException {
		if(!isGameRunning()) {
			throw new IllegalGameStateException("Game must be reset before guessing again.");
		}
		
		if(!range.isInRange(guess)) {
			throw new IllegalArgumentException("Guess is out of range.");
		}
		
		currentNGuesses++;
		
		if(Math.abs(answer - guess) > tolerance) {
			if(currentNGuesses >= maxNumGuesses) {
				gameState = GameState.GAME_OVER_LOSE;
			}
		} else {
			gameState = GameState.GAME_OVER_WIN;
		}
		
		return gameState;
	}
}
