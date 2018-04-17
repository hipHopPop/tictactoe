package hhp.tictactoe.learning.supervised.backpropagation;

import java.net.URL;

import org.junit.Test;

import hhp.tictactoe.learning.supervised.backpropagation.TicTacToe;


public class TicTacToeTest {

	@Test
	public void test() throws Exception {
		URL resource = TicTacToe.class.getResource("/supervised/tic-tac-toe.data.txt");
		TicTacToe.play(resource);
	}

}
