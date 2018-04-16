package hhp.tictactoe.learning.supervised.dumb;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import hhp.tictactoe.learning.supervised.dumb.TicTacToe;

public class TicTacToeTest {
	File weightsDbFile = null;
	File weightsDb2File = null;
	
	@Before
	public void setUp() throws Exception {
		weightsDbFile = new File(TicTacToeTest.class.getResource("/WeightsDatabase.txt").toURI());
		weightsDb2File = new File(TicTacToeTest.class.getResource("/WeightsDatabase2.txt").toURI());
	}

	@Test
	public void test() {
		TicTacToe.play(weightsDbFile, weightsDb2File);
	}

}
