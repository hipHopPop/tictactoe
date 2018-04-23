package hhp.tictactoe.learning.supervised;

import java.net.URL;

import org.junit.Test;


public class TicTacToeTest {

	@Test
	public void test() throws Exception {
		URL resource = hhp.tictactoe.learning.supervised.TicTacToe.class
				.getResource("/supervised/tic-tac-toe.data.txt");
		boolean doBackPropagation = false;
		hhp.tictactoe.learning.supervised.TicTacToe.play(resource, doBackPropagation);
	}

	@Test
	public void testWithBackPropagation() throws Exception {
		URL resource = hhp.tictactoe.backpropagation.TicTacToe.class
				.getResource("/supervised/tic-tac-toe.data.txt");
		hhp.tictactoe.backpropagation.TicTacToe.playSupervisedNonImage(resource);
	}

}
