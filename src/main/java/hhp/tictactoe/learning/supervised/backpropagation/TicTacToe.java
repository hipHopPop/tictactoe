package hhp.tictactoe.learning.supervised.backpropagation;

import java.net.URL;

public class TicTacToe {

	public static void play(URL gameImagesURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.learning.supervised.smart.startingmatch.TicTacToe.play(gameImagesURL, doBackPropagation);
	}
}