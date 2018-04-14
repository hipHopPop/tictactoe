package com.hhp.game.util;

import java.util.Random;

import com.hhp.game.TicTacToe;

public class RandomAI{
	//TODO: -done
	
	public static int raiOutput() {
		int output;
		Random rand = new Random();
		
		do {
			output = rand.nextInt(9) + 0;
		}
		while(TicTacToe.used[output] == output);
		
		return output; 
	}
}
