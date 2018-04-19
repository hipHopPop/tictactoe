package hhp.tictactoe.no.formula.backpropagation;

import java.net.URL;

public class TicTacToe {

	public static void playStartingMatch(URL gameImagesURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.no.formula.smart.startingmatch.TicTacToe.play(gameImagesURL, doBackPropagation);
	}

	public static void playPositionMatch(URL gameImagesURL) throws Exception {
		boolean doBackPropagation = true;
		hhp.tictactoe.no.formula.smart.positionmatch.TicTacToe.play(gameImagesURL, doBackPropagation);
	}
}