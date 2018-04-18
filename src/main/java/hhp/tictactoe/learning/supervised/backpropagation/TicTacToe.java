package hhp.tictactoe.learning.supervised.backpropagation;

import java.net.URL;

public class TicTacToe {

	public static void playStartingMatch(URL gameImagesURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.learning.supervised.smart.startingmatch.TicTacToe.play(gameImagesURL, doBackPropagation);
	}

	public static void playPositionMatch(URL gameImagesURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.learning.supervised.smart.positionmatch.TicTacToe.play(gameImagesURL, doBackPropagation);
	}
}