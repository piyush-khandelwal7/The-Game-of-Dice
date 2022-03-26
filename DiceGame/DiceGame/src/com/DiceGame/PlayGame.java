package com.DiceGame;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {
	private static int target ;
	private static int players ;
	private static int completedPlayer = 0;
	
	
	//using player class which stores last no that player got , if penalty is applied on them , if they have completed the game
	static class Player   
	{   
		int pid;   
		int lastturn;   
		boolean penalty;   
		boolean completed;  

		Player(int pid, int lastturn, boolean penalty, boolean completed)   
		{   
		this.pid = pid;   
		this.lastturn = lastturn;   
		this.penalty = penalty;   
		this.completed = completed;   
		}   
	}   
	
	private static ArrayList<Player> gamedata = new ArrayList<Player>();
	private static ScoreCard sc;

	//function to assign random order to the players 
	
	public static void RandomizeOrder(){
		
		 System.out.println("Randomizing the order of players");

		 ArrayList<Integer> v = new ArrayList<>();
		 for(int i = 1 ; i <= PlayGame.players ; i++) {
			 v.add(i);
		 }
		 for(int i = 1 ; i <= PlayGame.players ; i++) {
			    Random rand = new Random();
				int r = rand.nextInt(v.size()) + 1;
				PlayGame.gamedata.add(new Player(v.get(r-1),-1,false,false));
				v.remove(r-1);
				
		 }
		 

	}
	
	//function which starts the game
	
	public static void start(int player , int target) {
		PlayGame.sc = new ScoreCard(player , target);
		setTarget(target);
		setPlayers(player);
		PlayGame.RandomizeOrder();
		PlayGame.play();				
	}
	
	//function to get random no which represent the thrown dice value
	public static int throwDice() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
	
	//function which controls which player is going throw the dice.
	public static void play() {

		Scanner inp = new Scanner(System.in);

		while(PlayGame.completedPlayer < PlayGame.players){
			for(int i = 0 ; i<PlayGame.gamedata.size(); i++) {
				Player p = PlayGame.gamedata.get(i);
				if(p.penalty) {
					p.penalty = false;
				}
				else if(!p.completed) {
					System.out.println("Player-" + p.pid + " its your turn (press ‘r’ to roll the dice)");
					inp.next();
					int diceValue = PlayGame.throwDice();
					if(diceValue == 6) {
						System.out.println("You rolled a 6. you get an extra roll");
						System.out.println("Press a key play the dice");
						inp.next();
						int diceValue2 = PlayGame.throwDice();
						System.out.println("You rolled "+ diceValue2);
						int total = PlayGame.sc.addScore(p.pid, 6 + diceValue2);
						if(total >= PlayGame.target) {
							p.completed = true;
							PlayGame.completedPlayer++;
						}

					}
					else if(diceValue == 1 && p.lastturn == 1) {
						System.out.println("You rolled consecutive 1s. Your next turn will be skipped");
						int total = PlayGame.sc.addScore(p.pid, 1);
						p.penalty = true;
						if(total >= PlayGame.target) {
							p.completed = true;
							PlayGame.completedPlayer++;
						}

					}
					else {
						System.out.println("You rolled "+ diceValue);
						p.lastturn = diceValue;
						int total = PlayGame.sc.addScore(p.pid, diceValue);
						if(total >= PlayGame.target) {
							p.completed = true;
							PlayGame.completedPlayer++;
						}

						
					}
					sc.printScoreCard();
				}
				
			}
		}
		System.out.println("Game finished. Here is the final scorecard");
		sc.printScoreCard();
		inp.close();
		
	}
	
	public static void setPlayers(int players) {
		PlayGame.players = players;
	}


	public static void setTarget(int target) {
		PlayGame.target = target;
	}
}
