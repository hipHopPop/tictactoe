package hhp.tictactoe.backpropagation;

import java.net.URL;

public class TicTacToe {

	public static void playStartingMatch(URL gameDataURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.simple.smart.startingmatch.TicTacToe.play(gameDataURL, doBackPropagation);
	}

	public static void playPositionMatch(URL gameDataURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.simple.smart.positionmatch.TicTacToe.play(gameDataURL, doBackPropagation);
	}

	public static void playSupervisedNonImage(URL gameDataURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.learning.supervised.TicTacToe.play(gameDataURL, doBackPropagation);
	}
}