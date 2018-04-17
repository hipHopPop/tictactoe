package hhp.tictactoe.learning.supervised.smart.positionmatch;

import static hhp.tictactoe.learning.supervised.smart.startingmatch.Trainer.train;
import static hhp.tictactoe.learning.supervised.smart.startingmatch.Utility.humanInput;
import static hhp.tictactoe.learning.supervised.smart.startingmatch.Utility.machineLearningInput;
import static hhp.tictactoe.learning.supervised.smart.startingmatch.Utility.randomInput;
import static hhp.tictactoe.learning.supervised.smart.startingmatch.Utility.turn;
import static hhp.util.Printer.drawBoard;
import static hhp.util.ResultCheck.checkTie;
import static hhp.util.ResultCheck.endGame;
import static hhp.util.ResultCheck.winYet;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import hhp.tictactoe.learning.supervised.smart.startingmatch.classification.Classifier;

public class TicTacToe {
	private static final String[] BLANKS = new String[] { "b", "b", "b", "b", "b", "b", "b", "b", "b" };
	public static int[] boardArray 	= { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	public static String X 			= "x";
	public static String O 			= "o";
	public static String B 			= "b";
	
	public static void play(URL gameImagesURL, boolean doBackPropagation) throws Exception{}//end ML vs ML	}//end function
	
	//===========================================================================================================================
	
	public static void main(String[] args) throws Exception {}
}