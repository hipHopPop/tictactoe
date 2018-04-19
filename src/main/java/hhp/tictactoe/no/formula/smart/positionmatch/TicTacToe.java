package hhp.tictactoe.no.formula.smart.positionmatch;

import static hhp.tictactoe.no.formula.smart.positionmatch.Trainer.train;
import static hhp.tictactoe.no.formula.smart.positionmatch.Utility.humanInput;
import static hhp.tictactoe.no.formula.smart.positionmatch.Utility.machineLearningInput;
import static hhp.tictactoe.no.formula.smart.positionmatch.Utility.randomInput;
import static hhp.tictactoe.no.formula.smart.positionmatch.Utility.turn;
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

import hhp.tictactoe.no.formula.smart.positionmatch.classification.Classifier;

public class TicTacToe {
	private static final String[] BLANKS = new String[] { "b", "b", "b", "b", "b", "b", "b", "b", "b" };
	public static int[] boardArray 	= { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	public static String X 			= "x";
	public static String O 			= "o";
	public static String B 			= "b";
	
	public static void play(URL gameImagesURL, boolean doBackPropagation) throws Exception{
		//loadGameImages(gameImagesURL);
		Classifier classifier = train(gameImagesURL);
		
		int inputq = 0;
		//H v H, M v M, R v R, H v M, H v R, M v R
		String[] q = {"Player vs Machine L", "Random AI vs MachineL", "MachineL vs MachineL"};
	    inputq = JOptionPane.showOptionDialog(null,"What gamemode do you want to play?","TicTacToeTacToe",-1,-1,null,q,q[0]) ;
	    drawBoard(boardArray);

	    //==========================
	    //Player vs Machine Learning
	    //==========================
	    if(inputq == 0) {
	    	//JOptionPane.showMessageDialog(null, "You chose Player vs Machine Learning");
			for (int x = 0; x < 10; x++) {
				List<String> gameData 	= new ArrayList<>(Arrays.asList(BLANKS));
				boardArray 				= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
			    drawBoard(boardArray);
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
					//---------------------------
		    		//Machine Learning's turn now
					int location1 = machineLearningInput(gameData, classifier, X);
		        	gameData.set(location1, X);
		        	timePlayed++;
		    		turn(boardArray, location1, X);
		    		drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray,1)) {
		    			if (doBackPropagation) { classifier.increaseWeight(gameData); }
		    			endGame("Machine Learning"); 
		    			break;
		    		}
					if (timePlayed >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
					//------------
		    		//human's turn
					int location2 = humanInput(gameData);
		        	gameData.set(location2, O);
		        	timePlayed2++;
		    		turn(boardArray, location2, O);
		    		drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray, 2)) {
		    			if (doBackPropagation) { classifier.decreaseWeight(gameData); }
		    			endGame("Human"); 
		    			break;
		    		}
					if (timePlayed2 >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
		    	}
		    }
	    }
	    //=============================
	    //Machine Learning vs Random AI
	    //=============================
	    else if (inputq == 1) {
	    	//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Random AI");
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				List<String> gameData 	= new ArrayList<>(Arrays.asList(BLANKS));
				boardArray 				= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
			    drawBoard(boardArray);
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//-----------------------
		    		//Machine Learning's turn
					int location1 = machineLearningInput(gameData, classifier, X);
		        	gameData.set(location1, X);
		        	timePlayed++;
		    		turn(boardArray, location1, X);
		    		//drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray,1)) {
			    		drawBoard(boardArray);
		    			if (doBackPropagation) { classifier.increaseWeight(gameData); }
		    			c1++;
						break;
		    		}
		    		if(timePlayed >= 3 && checkTie(boardArray)) { t++; break; }
		    		//--------------------
		    		//Random AI's turn now
		    		int location2 = randomInput(gameData);
					gameData.set(location2, O);
		        	timePlayed2++;
		    		turn(boardArray, location2, O);
		    		//drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray,2) == true) {
			    		drawBoard(boardArray);
		    			if (doBackPropagation) { classifier.decreaseWeight(gameData); }
		    			c2++;
						break;
		    		}
		    		if(timePlayed2 >= 3 && checkTie(boardArray)) { t++; break; }
		    	}
			}
			JOptionPane.showMessageDialog(null, "The Game Tied " + t + " times\nMachine Learning Won " + c1
					+ " times\nRandom AI Won " + c2 + " times");
	    }//end ML vs Rand
	    //====================================
	    //Machine Learning vs Machine Learning
	    //====================================
		else if (inputq == 2) {
			//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
			Classifier classifier2 = classifier.clone();
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				List<String> gameData 	= new ArrayList<>(Arrays.asList(BLANKS));
				boardArray 				= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					//---------------
					//player 1's turn
					int location1 = machineLearningInput(gameData, classifier, X);
		        	gameData.set(location1, X);
					timePlayed++;
		    		turn(boardArray, location1, X);
					//drawBoard(boardArray);
					if (timePlayed >= 3 && winYet(boardArray, 1)) {
						drawBoard(boardArray);
		    			if (doBackPropagation) { classifier.increaseWeight(gameData); classifier2.decreaseWeight(gameData); }
						c1++;
						break;
					}
					if (timePlayed >= 3 && checkTie(boardArray)) { t++; break; }
					//---------------
					//player 2's turn
					int location2 = machineLearningInput(gameData, classifier2, O);
		        	gameData.set(location2, O);
		        	timePlayed2++;
		    		turn(boardArray, location2, O);
					//drawBoard(boardArray);
					if (timePlayed2 >= 3 && winYet(boardArray, 2)) {
						drawBoard(boardArray);
		    			if (doBackPropagation) { classifier.decreaseWeight(gameData); classifier2.increaseWeight(gameData); }
						c2++;
						break;
					}
					if (timePlayed2 >= 3 && checkTie(boardArray)) { t++; break; }
				} //end for
			}
			JOptionPane.showMessageDialog(null, "The Game Tied " + t + " times\nMachine Learning 1 Won " + c1
				+ " times\nMachine Learning 2 Won " + c2 + " times");
		}//end else if
	}//end ML vs ML	}//end function
	
	//===========================================================================================================================
	
	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<>();

		URL resource = TicTacToe.class.getResource("/supervised/tic-tac-toe.data.txt");
		URI uri = resource.toURI();
		try (Stream<String> stream = Files.lines(Paths.get(uri))) {
			list = stream.filter(line -> line.endsWith("positive"))
					// .map(String::toUpperCase)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		list.forEach(System.out::println);

		List<String> samples = Arrays.asList(new String[] { "b,b,o,x,x,x,o,b,b,positive", "x,x,b,b,x,o,o,o,x,positive",
				"x,o,x,x,x,o,x,o,o,positive" });

		String X = "x";
		String O = "o";
		String B = "b";
		List<String> lst = Arrays.asList(new String[] { X, O });
		String collect = lst.stream().collect(Collectors.joining(","));
		System.out.println(collect);

		List<String> shortList = samples.stream().filter(line -> line.startsWith(collect)).collect(Collectors.toList());
		shortList.forEach(System.out::println);
	}
}