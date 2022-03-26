
package com.DiceGame;

import java.util.Scanner;

public class Game {
	private static int players;
	private static int target;
	
	public Game() {
		System.out.println("Enter number of players->");
		Scanner inp = new Scanner(System.in);
		players = inp.nextInt();
		System.out.println("Enter target points->");
		target = inp.nextInt();
	}
	
	public void startGame() {
		if(players == 0) {
			System.out.println("no of players is zero so exiting the game");
		}
		else {
			PlayGame.start(players,target);
		}
	}
	public static void main(String[] args) {
		System.out.println("Starting the Game...");
		//Initializing the game by taking no of players and target value
		Game g = new Game();
		//starting the game
		g.startGame();
		

	}

}
