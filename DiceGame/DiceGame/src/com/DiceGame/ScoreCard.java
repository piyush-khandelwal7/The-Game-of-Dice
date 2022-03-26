package com.DiceGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreCard {
	ArrayList<Integer> score = new ArrayList<Integer>();
	ArrayList<Integer> rank = new ArrayList<Integer>();


	int currrank = 1;
	private static int target;
	
	public ScoreCard(int players, int target){
		ScoreCard.target = target;
		

		for(int i = 1 ; i <= players ; i++ ) {
			score.add(0);
			rank.add(0);

		}
	}
	
	public int addScore(int id , int s) {
		score.set(id-1, s + score.get(id-1));
		if(score.get(id-1) >= ScoreCard.target) {
			rank.set(id -1 ,  currrank);
			currrank++;
			System.out.println("Player-"+id +" has finished the game");
		}
		return score.get(id-1);
	}
	
	public void printScoreCard()
	{
		ArrayList<List<Integer>> sorval = new ArrayList<List<Integer>>();
		for (int i = 1 ;i<=score.size(); i++) {
			List<Integer> l = new ArrayList<Integer>();
			l.add(i);
			if(rank.get(i-1) !=0) {
				l.add(Integer.MAX_VALUE - rank.get(i-1));
			}
			else {
				l.add(score.get(i-1));
			}
			sorval.add(l);
		}
		
		Collections.sort(sorval, new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1,
            					List<Integer> o2)
            {
                return (o1.get(1).compareTo(o2.get(1)));
            }
        });
		
		
		System.out.println("    ");
		System.out.println("Player " +"    " + "score  "   + "rank");
		int j = score.size();
		for (int i = score.size() ;i>=1; i--) {
			int rank = j-i+1;
            System.out.println("Player-" + sorval.get(i-1).get(0) +" --> " + score.get(sorval.get(i-1).get(0)-1) + "     " + rank  );
		}
		System.out.println("    ");

	}
}
