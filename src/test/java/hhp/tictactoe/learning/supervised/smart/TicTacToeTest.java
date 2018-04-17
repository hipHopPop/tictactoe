package hhp.tictactoe.learning.supervised.smart;

import java.net.URL;

import org.junit.Test;


public class TicTacToeTest {

	@Test
	public void test() throws Exception {
		URL resource = TicTacToe.class.getResource("/supervised/tic-tac-toe.data.txt");
		TicTacToe.play(resource);
	}

}
