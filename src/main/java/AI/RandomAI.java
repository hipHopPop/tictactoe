package AI;

import java.util.Random;

import Game.TicTacToe;

public class RandomAI{
	//TODO: -done
	
	public static int raiOutput() {
		int output;
		Random rand = new Random();
		
		do {
			output = rand.nextInt(9) + 0;
		}
		while(TicTacToe.usedArray[output] == true);
		
		return output; 
	}
}
