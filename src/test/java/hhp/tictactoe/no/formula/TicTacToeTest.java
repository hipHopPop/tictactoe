package hhp.tictactoe.no.formula;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public class TicTacToeTest {

	@Test
	public void testDumb() throws URISyntaxException {
		File weightsDbFile = new File(TicTacToeTest.class.getResource("/WeightsDatabase.txt").toURI());
		File weightsDb2File = new File(TicTacToeTest.class.getResource("/WeightsDatabase2.txt").toURI());
		hhp.tictactoe.no.formula.simple.TicTacToe.play(weightsDbFile, weightsDb2File);
	}

	@Test
	public void testStartingMatch() throws Exception {
		URL resource = hhp.tictactoe.no.formula.smart.startingmatch.TicTacToe.class
				.getResource("/supervised/tic-tac-toe.data.txt");
		boolean doBackPropagation = false;
		hhp.tictactoe.no.formula.smart.startingmatch.TicTacToe.play(resource, doBackPropagation);
	}

	@Test
	public void testStartingMatchWithBackPropagation() throws Exception {
		URL resource = hhp.tictactoe.no.formula.backpropagation.TicTacToe.class
				.getResource("/supervised/tic-tac-toe.data.txt");
		hhp.tictactoe.no.formula.backpropagation.TicTacToe.playStartingMatch(resource);
	}

	@Test
	public void testPositionMatch() throws Exception {
		URL resource = hhp.tictactoe.no.formula.smart.positionmatch.TicTacToe.class
				.getResource("/supervised/tic-tac-toe.data.txt");
		boolean doBackPropagation = false;
		hhp.tictactoe.no.formula.smart.positionmatch.TicTacToe.play(resource, doBackPropagation);
	}

	@Test
	public void testPositionMatchWithBackPropagation() throws Exception {
		URL resource = hhp.tictactoe.no.formula.backpropagation.TicTacToe.class
				.getResource("/supervised/tic-tac-toe.data.txt");
		hhp.tictactoe.no.formula.backpropagation.TicTacToe.playPositionMatch(resource);
	}

}
