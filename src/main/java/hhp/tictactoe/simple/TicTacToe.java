package hhp.tictactoe.simple;

import static hhp.tictactoe.simple.Sorter.sortByW;
import static hhp.tictactoe.simple.Utility.dataInput;
import static hhp.tictactoe.simple.Utility.humanInput;
import static hhp.tictactoe.simple.Utility.nodeWeights;
import static hhp.tictactoe.simple.Utility.nodeWeights2;
import static hhp.tictactoe.simple.Utility.output;
import static hhp.tictactoe.simple.Utility.randomOutput;
import static hhp.tictactoe.simple.Utility.reweightSortAndStore;
import static hhp.tictactoe.simple.Utility.turn;
import static hhp.util.Printer.drawBoard;
import static hhp.util.ResultCheck.checkTie;
import static hhp.util.ResultCheck.endGame;
import static hhp.util.ResultCheck.winYet;

import java.io.File;

import javax.swing.JOptionPane;

public class TicTacToe {
	//computer goes first
	//0 -> blank
	//1 -> O (human 1)
	//2 -> X (computer)
	//3 -> 1 
	//4 -> 2
	//...
	final static int turns 			= 9;
	public static int[] boardArray 	= { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	public static int[] used 		= { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	static int location;
	
	public static void play(File weightsDbFile, File weightsDb2File){
		int inputq = 0;
		//H v H, M v M, R v R, H v M, H v R, M v R
		String[] q = {"Player vs  Random AI", "Player vs Player", "Random AI vs Random AI", "Player vs Machine L", "Random AI vs MachineL", "MachineL vs MachineL"};
	    inputq = JOptionPane.showOptionDialog(null,"What gamemode do you want to play?","TicTacToeTacToe",-1,-1,null,q,q[0]) ;
	    drawBoard(boardArray);

	    //==================
	    //Human vs Random AI
	    //==================
	    if(inputq == 0) {
			for (int x = 0; x < 10; x++) {
				boardArray 		= new int[]{ 3, 4, 5, 6, 7, 8, 9, 10, 11 };
				used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				int timePlayed 	= 0;
				int timePlayed2 = 0;
			    drawBoard(boardArray);
				//JOptionPane.showMessageDialog(null, "You chose Human vs AI");
		    	for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//------------
		    		//human's turn
					location 		= humanInput(used);
		        	used[location] 	= 1;
		        	timePlayed++;
		    		turn(boardArray, location, 1);
		    		drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray, 1) == true) { endGame("Player 1");  break; }
		    		if(timePlayed >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
		    		//---------------
		    		//Computer's turn
					location 		= randomOutput(used);
		        	used[location] 	= 2;
		        	timePlayed2++;
		    		turn(boardArray, location, 2);
		    		drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray,2) == true) { endGame("Random AI"); break; }
		    		if(timePlayed2 >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
		    	}// end AI vs Player
			}
	    }
	    //==============
	    //Human vs Human
	    //==============
	    else if (inputq == 1) {
			used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1};
			int timePlayed 	= 0;
			int timePlayed2 = 0;
			//JOptionPane.showMessageDialog(null, "You chose Human vs Human");
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//--------------
	    		//human 1's turn
	    		location 		= humanInput(used);
	        	used[location] 	= 1;
	        	timePlayed++;
	    		turn(boardArray, location, 1);
	    		drawBoard(boardArray);
	    		if(timePlayed >= 3 && winYet(boardArray, 1) == true) { endGame("Player 1");  break; }
	    		if(timePlayed >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
	    		//--------------
	    		//Human 2's turn
				location 		= humanInput(used);
	        	used[location] 	= 2;
	        	timePlayed2++;
	    		turn(boardArray, location, 2);
	    		drawBoard(boardArray);
	    		if(timePlayed2 >= 3 && winYet(boardArray,2) == true) { endGame("Player 2"); break; }
	    		if(timePlayed2 >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
	    	}
	    
	    } //end Player v Player
	    //======================
	    //Random AI vs Random AI
	    //======================
	    else if (inputq == 2) { 
			used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1};
			int timePlayed 	= 0;
			int timePlayed2 = 0;
			//JOptionPane.showMessageDialog(null, "You chose Random AI vs Random AI");
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//------------
	    		//human's turn
				location 		= randomOutput(used);
	        	used[location] 	= 1;
	        	timePlayed++;
	    		turn(boardArray, location, 1);
	    		drawBoard(boardArray);
	    		if(timePlayed >= 3 && winYet(boardArray, 1) == true) { endGame("Random AI 1"); break; }
	    		if(timePlayed >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
	    		//-------------------
	    		//Computer's turn now
				location 		= randomOutput(used);
	        	used[location] 	= 2;
	        	timePlayed2++;
	    		turn(boardArray, location, 2);
	    		drawBoard(boardArray);
	    		if(timePlayed2 >= 3 && winYet(boardArray,2) == true) { endGame("Random AI 2"); break; }
	    		if(timePlayed2 >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
	    	}
	    	
	    } //end Random AI vs Random AI
	    //==========================
	    //Player vs Machine Learning
	    //==========================
	    else if (inputq == 3) {
			for (int x = 0; x < 10; x++) {
				boardArray 		= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
			    drawBoard(boardArray);
				//JOptionPane.showMessageDialog(null, "You chose Player vs Machine Learning");
				nodeWeights 	= dataInput(weightsDbFile);
				//printArr(nodeWeights);
				used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1};
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
					//------------
		    		//human's turn
					location 		= humanInput(used);
		        	used[location] 	= 1;
		        	timePlayed++;
		    		turn(boardArray, location, 1);
		    		drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray, 1) == true) {
		    			reweightSortAndStore(weightsDbFile, nodeWeights, 2, used, false);
		    			endGame("Human"); 
		    			break;
		    		}
					if (timePlayed >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
					//---------------------------
		    		//Machine Learning's turn now
					sortByW(nodeWeights);
					location 		= output(nodeWeights,used);
		        	used[location] 	= 2;
		        	timePlayed2++;
		    		turn(boardArray, location, 2);
		    		drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray,2) == true) {
		    			reweightSortAndStore(weightsDbFile, nodeWeights, 2, used, true);
		    			endGame("Machine Learning"); 
		    			break;
		    		}
					if (timePlayed2 >= 3 && checkTie(boardArray)) { endGame("Tie"); break; }
		    	}
		    }
	    }
	    //=============================
	    //Machine Learning vs Random AI
	    //=============================
	    else if (inputq == 4) {
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				boardArray 		= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
				nodeWeights 	= dataInput(weightsDbFile);
				used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1};
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Random AI");
				//printArr(nodeWeights);
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//-----------------------
		    		//Machine Learning's turn
					sortByW(nodeWeights);
					location 		= output(nodeWeights,used);
		        	used[location] 	= 1;
		        	timePlayed++;
		    		turn(boardArray, location, 1);
		    		drawBoard(boardArray);
		    		if(timePlayed >= 3 && winYet(boardArray,1) == true) {
		    			reweightSortAndStore(weightsDbFile, nodeWeights, 1, used, true);
		    			c1++;
						break;
		    		}
		    		if(timePlayed >= 3 && checkTie(boardArray)) { t++; break; }
		    		//--------------------
		    		//Random AI's turn now
					location 		= randomOutput(used);
		        	used[location] 	= 2;
		        	timePlayed2++;
		    		turn(boardArray, location, 2);
		    		drawBoard(boardArray);
		    		if(timePlayed2 >= 3 && winYet(boardArray,1) == true) {
		    			reweightSortAndStore(weightsDbFile, nodeWeights, 1, used, false);
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
		else if (inputq == 5) {
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				boardArray 		= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
				used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				int timePlayed 	= 0;
				int timePlayed2 = 0;
				//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
				nodeWeights 	= dataInput(weightsDbFile);
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
						reweightSortAndStore(weightsDbFile, nodeWeights, 1, weightsDb2File, nodeWeights2, 2, used);
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
						reweightSortAndStore(weightsDb2File, nodeWeights2, 2, weightsDbFile, nodeWeights, 1, used);
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
}