package hhp.tictactoe.learning.supervised.smart;

import static hhp.tictactoe.learning.supervised.dumb.util.Printer.drawBoard;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

public class TicTacToe {
	final static int turns 			= 9;
	public static int[] boardArray 	= { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	public static String X 			= "x";
	public static String O 			= "o";
	public static String B 			= "b";
	static int location;
	public static List<String> positiveGames = null;
	public static List<String> negativeGames = null;
	
	public static void play(URL gameImagesURL) throws URISyntaxException{
		loadGameImages(gameImagesURL);
		
		int inputq = 0;
		//H v H, M v M, R v R, H v M, H v R, M v R
		String[] q = {"Player vs Machine L", "Random AI vs MachineL", "MachineL vs MachineL"};
	    inputq = JOptionPane.showOptionDialog(null,"What gamemode do you want to play?","TicTacToeTacToe",-1,-1,null,q,q[0]) ;
	    drawBoard(boardArray);
		        
	    //Player vs Machine Learning
	   /* if(inputq == 0) {
	    	
	    	List<String> used 	= new ArrayList<>(9);
			for (int x = 0; x < 10; x++) {
				boardArray 		= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
			    drawBoard(boardArray);
				//JOptionPane.showMessageDialog(null, "You chose Player vs Machine Learning");
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
					//------------
		    		//human's turn
					location 	= humanInput(used);
		        	used.add(location, X);
		        	timePlayed++;
		    		turn(boardArray, location, X);
		    		drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray, 1) == true) {
		    			reweightSortAndStore(gameImagesURL, nodeWeights, 2, used, false);
		    			endGame("Human"); 
		    			break;
		    		}
					if (checkTie(boardArray)) { endGame("Tie"); break; }
					//---------------------------
		    		//Machine Learning's turn now
					sortByW(nodeWeights);
					location 		= output(nodeWeights,used);
		        	used.add(location, O);
		        	timePlayed2++;
		    		turn(boardArray, location, 2);
		    		drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray,2) == true) {
		    			reweightSortAndStore(gameImagesURL, nodeWeights, 2, used, true);
		    			endGame("Machine Learning"); 
		    			break;
		    		}
					if (checkTie(boardArray)) { endGame("Tie"); break; }
		    	}
		    }
	    }
	    
	    //Machine Learning vs Random AI
	    else if (inputq == 1) {
	    	List<String> used 	= new ArrayList<>(9);
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				boardArray 		= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
				nodeWeights 	= dataInput(gameImagesURL);
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Random AI");
				//printArr(nodeWeights);
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//-----------------------
		    		//Machine Learning's turn
					sortByW(nodeWeights);
					location 		= output(nodeWeights,used);
		        	used.add(location, X);
		        	timePlayed++;
		    		turn(boardArray, location, 1);
		    		drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray,1) == true) {
		    			reweightSortAndStore(gameImagesURL, nodeWeights, 1, used, true);
		    			c1++;
						break;
		    		}
		    		if(timePlayed >= 3 && checkTie(boardArray)) { t++; break; }
		    		//--------------------
		    		//Random AI's turn now
					location 		= randomOutput(used);
		        	used.add(location, O);
		        	timePlayed2++;
		    		turn(boardArray, location, 2);
		    		drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray,1) == true) {
		    			reweightSortAndStore(gameImagesURL, nodeWeights, 1, used, false);
		    			c2++;
						break;
		    		}
		    		if(timePlayed2 >= 3 && checkTie(boardArray)) { t++; break; }
		    	}
			}
			JOptionPane.showMessageDialog(null, "The Game Tied " + t + " times\nMachine Learning Won " + c1
					+ " times\nRandom AI Won " + c2 + " times");
	    }//end ML vs Rand
	    
	    //Machine Learning vs Machine Learning
		else if (inputq == 2) {
	    	List<String> used 	= new ArrayList<>(9);
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				boardArray 		= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
				nodeWeights 	= dataInput(gameImagesURL);
				nodeWeights2 	= dataInput(weightsDb2File);
				//printArr(nodeWeights);
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					//---------------
					//player 1's turn
					sortByW(nodeWeights);
					location = output(nodeWeights, used);
					used[location] = 1;
					timePlayed++;
					turn(boardArray, location, 1);
					drawBoard(boardArray);
					if (timePlayed >= 3 && winYet(boardArray, 1) == true) {
						reweightSortAndStore(gameImagesURL, nodeWeights, 1, weightsDb2File, nodeWeights2, 2, used);
						c1++;
						break;
					}
					if (timePlayed >= 3 && checkTie(boardArray)) { t++; break; }
					//---------------
					//player 2's turn
					sortByW(nodeWeights2);
					location = output(nodeWeights2, used);
					used[location] = 2;
					timePlayed2++;
					turn(boardArray, location, 2);
					drawBoard(boardArray);
					if (timePlayed2 >= 3 && winYet(boardArray, 2) == true) {
						reweightSortAndStore(weightsDb2File, nodeWeights2, 2, gameImagesURL, nodeWeights, 1, used);
						c2++;
						break;
					}
					if (timePlayed2 >= 3 && checkTie(boardArray)) { t++; break; }
				} //end for
			}
			JOptionPane.showMessageDialog(null, "The Game Tied " + t + " times\nMachine Learning 1 Won " + c1
				+ " times\nMachine Learning 2 Won " + c2 + " times");
		}//end else if
*/	}//end ML vs ML	}//end function

	private static void loadGameImages(URL gameImagesURL) throws URISyntaxException {
		try (Stream<String> stream = Files.lines(Paths.get(gameImagesURL.toURI()))) {

			positiveGames = stream
					.filter(line -> line.endsWith("positive"))
					.collect(Collectors.toList());
			negativeGames = stream
					.filter(line -> line.endsWith("negative"))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		//positiveGames.forEach(System.out::println);
		//negativeGames.forEach(System.out::println);
	}
	
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