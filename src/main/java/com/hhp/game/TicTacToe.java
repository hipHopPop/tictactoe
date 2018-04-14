package com.hhp.game;

import static com.hhp.game.util.Printer.drawBoard;
import static com.hhp.game.util.Printer.printArr;
import static com.hhp.game.util.ResultCheck.checkTie;
import static com.hhp.game.util.ResultCheck.endGame;
import static com.hhp.game.util.ResultCheck.winYet;
import static com.hhp.game.util.Sorter.reSort;
import static com.hhp.game.util.Sorter.sortByW;
import static com.hhp.game.util.Utility.dataInput;
import static com.hhp.game.util.Utility.dataOutput;
import static com.hhp.game.util.Utility.humanInput;
import static com.hhp.game.util.Utility.nodeWeights;
import static com.hhp.game.util.Utility.nodeWeights2;
import static com.hhp.game.util.Utility.output;
import static com.hhp.game.util.Utility.randomOutput;
import static com.hhp.game.util.Utility.reweight;
import static com.hhp.game.util.Utility.reweightSortAndStore;
import static com.hhp.game.util.Utility.turn;

import java.io.File;
import java.io.IOException;

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
		        
	    if(inputq == 0) { //Human 1 vs Random AI 
			for (int x = 0; x < 10; x++) {
				boardArray 	= new int[]{ 3, 4, 5, 6, 7, 8, 9, 10, 11 };
				used 		= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				int timesUsed = 0;
			    drawBoard(boardArray);
				//JOptionPane.showMessageDialog(null, "You chose Human vs AI");
		    	for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//human's turn
					//JOptionPane.showMessageDialog(null, "Player 1's turn");
					location 		= humanInput(used);
		        	used[location] 	= location;
		        	timesUsed++;
		    		turn(boardArray, location, 2);
		    		//JOptionPane.showMessageDialog(null, "You placed an 'X' at "+location);
		    		drawBoard(boardArray);
		    		if(winYet(boardArray,2) == true) {
		    			endGame("Player"); 
		    			break;
		    		}
		    		//Computer's turn now
					//JOptionPane.showMessageDialog(null, "Computer 1's turn");
					location 		= randomOutput();
		        	used[location] 	= location;
		        	timesUsed++;
		    		turn(boardArray, location, 1);
		    		//JOptionPane.showMessageDialog(null, "Computer 1 placed an 'O' at "+location);
		    		drawBoard(boardArray);
		    		
		    		if(winYet(boardArray,1) == true) {
		    			endGame("Computer"); 
		    			break;
		    		}
		    		//check if human won before checking for a tie...
					if (checkTie(boardArray)) {
						endGame("Tie");
						break;
					}
		    	}// end AI vs Player
			}
	    }
	    else if (inputq == 1) { //Human 1 vs Human 2
			int[] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			int timesUsed = 0;
			//JOptionPane.showMessageDialog(null, "You chose Human vs Human");
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//human 1's turn
				//JOptionPane.showMessageDialog(null, "Player 1's turn");
	    		location 		= humanInput(used);
	        	used[location] 	= location;
	        	timesUsed++;
	    		turn(boardArray, location, 2);
	    		//JOptionPane.showMessageDialog(null, "Player 1 placed an 'X' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray, 2) == true) {
	    			endGame("Player 1"); 
	    			break;
	    		}
	    		
	    		//Human 2's turn now
				//JOptionPane.showMessageDialog(null, "Player 2's turn");
				location = humanInput(used);
	        	used[location] = location;
	        	timesUsed++;
	    		turn(boardArray, location, 1);
	    		//JOptionPane.showMessageDialog(null, "Player 2 placed an 'O' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray,1) == true) {
	    			endGame("Player 2"); 
	    			break;
	    		}
	    		//check if human won before checking for a tie...
	    		if(checkTie(boardArray)) {endGame("Tie"); break; }
	    	}
	    
	    } //end Player v Player
	    else if (inputq == 2) { //Random AI vs Random AI
			int[] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			int timesUsed = 0;
			//JOptionPane.showMessageDialog(null, "You chose Random AI vs Random AI");
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//human's turn
				//JOptionPane.showMessageDialog(null, "Random AI 1's turn");
				location 		= humanInput(used);
	        	used[location] 	= location;
	        	timesUsed++;
	    		turn(boardArray, location, 2);
	    		//JOptionPane.showMessageDialog(null, "Random AI 1 placed an 'X' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray, 2) == true) {
	    			endGame("Random AI 1"); 
	    			break;
	    		}
	    		
	    		//Computer's turn now
				//JOptionPane.showMessageDialog(null, "Random AI 2's turn");
				location 		= randomOutput();
	        	used[location] 	= location;
	        	timesUsed++;
	    		turn(boardArray, location, 1);
	    		//JOptionPane.showMessageDialog(null, "Random AI 2 placed an 'O' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray,1) == true) {
	    			endGame("Random AI 2"); 
	    			break;
	    		}
	    		//check if human won before checking for a tie...
	    		if(checkTie(boardArray)) {endGame("Tie"); break; }
	    	}
	    	
	    } //end Random AI vs Random AI

	    //Player vs Machine Learning
	    else if (inputq == 3) {
			for (int x = 0; x < 10; x++) {
				boardArray 			= new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11};
			    drawBoard(boardArray);
				//JOptionPane.showMessageDialog(null, "You chose Player vs Machine Learning");
				nodeWeights 	= dataInput(weightsDbFile);
				//printArr(nodeWeights);
				int[] used 			= { -1, -1, -1, -1, -1, -1, -1, -1, -1};
				int timesUsed = 0;
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//human's turn
					//JOptionPane.showMessageDialog(null, "Player's turn");
					location = humanInput(used);
		        	used[location] 	= location;
		        	timesUsed++;
		    		turn(boardArray, location, 2);
		    		//JOptionPane.showMessageDialog(null, "You placed an 'X' at "+location);
		    		drawBoard(boardArray);
		    		if(winYet(boardArray, 2) == true) {
		    			reweight(false, used,nodeWeights, 2); 
		    			nodeWeights = reSort(nodeWeights);
		    			//System.out.println("New node weights (lost)"); 
		    			System.out.println("New Weights");
		    			printArr(nodeWeights); 
		    			try {
							dataOutput(nodeWeights, weightsDbFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
		    			endGame("Player 1"); 
		    			break;
		    		}
		    		if(checkTie(boardArray)) {endGame("Tie"); break; }
		    		//Machine Learning's turn now
		    		
					//JOptionPane.showMessageDialog(null, "Machine Learning's turn");
					sortByW(nodeWeights);
					location 		= output(nodeWeights,used);
		        	used[location] 	= location;
		        	timesUsed++;
		    		turn(boardArray, location, 1);
		    		//JOptionPane.showMessageDialog(null, "Machine Learning placed an 'O' at "+location);
		    		drawBoard(boardArray);
		    		if(winYet(boardArray,1) == true) {
		    			reweight(true, used, nodeWeights, 1); 
		    			//System.out.println("New node weights (won)"); 
		    			nodeWeights = reSort(nodeWeights);
		    			System.out.println("New Weights");
		    			printArr(nodeWeights);
		    			try {
							dataOutput(nodeWeights, weightsDbFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
		    			//System.out.println("Data stored");
		    			endGame("Machine 2"); 
		    			break;
		    		}
		    		if(checkTie(boardArray)) {endGame("Tie"); break; }
		    		//check if human won before checking for a tie...
		    	}
		    }
	    }
	    
	    //Machine Learning vs Random AI
	    else if (inputq == 4) {
			//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Random AI");
			nodeWeights 	= dataInput(weightsDbFile);
			int[] used 			= { -1, -1, -1, -1, -1, -1, -1, -1, -1};
			int timesUsed 		= 0;
			//printArr(nodeWeights);
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//Machine Learning's turn
				//JOptionPane.showMessageDialog(null, "Machine Learning's turn");
				sortByW(nodeWeights);
				location 		= output(nodeWeights,used);
	        	used[timesUsed] = location;
	        	timesUsed++;
	    		turn(boardArray, location, 2);
	    		//JOptionPane.showMessageDialog(null, "Machine Learning placed an 'X' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray,2) == true) {
	    			reweight(true, used, nodeWeights, 2); 
	    			//System.out.println("New node weights (won)"); 
	    			nodeWeights = reSort(nodeWeights);
	    			System.out.println("New Weights");
	    			printArr(nodeWeights);
	    			try {
						dataOutput(nodeWeights, weightsDbFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			//System.out.println("Data stored");
	    			endGame("Machine Learning 1"); 
	    			break;
	    		}

	    		//Random AI's turn now
	    		//JOptionPane.showMessageDialog(null, "Random AI's turn");
				location = randomOutput();
	        	used[timesUsed] = location;
	        	timesUsed++;
	    		turn(boardArray, location, 1);
	    		//JOptionPane.showMessageDialog(null, "Random AI placed an 'O' at "+location);
	    		drawBoard(boardArray);
	    		
	    		if(winYet(boardArray,1) == true) {
	    			reweight(false, used,nodeWeights, 1); 
	    			//System.out.println("New node weights (lost)"); 
	    			nodeWeights = reSort(nodeWeights);
	    			System.out.println("New Weights");
	    			printArr(nodeWeights); 
	    			try {
						dataOutput(nodeWeights, weightsDbFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			endGame("Random AI"); 
	    			break;
	    		}

	    		if(checkTie(boardArray)) {endGame("Tie"); break; }
	    		//check if human won before checking for a tie...
	    	}
	    }//end ML vs Rand
	    
	    //Machine Learning vs Machine Learning
		else if (inputq == 5) {
			int c1 = 0, c2 = 0, t = 0;
			for (int x = 0; x < 1000; x++) {
				boardArray 		= new int[]{3,4,5,6,7,8,9,10,11};
				used 			= new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				int timesUsed 	= 0;
				int timesUsed2 	= 0;
				//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
				nodeWeights 	= dataInput(weightsDbFile);
				nodeWeights2 	= dataInput(weightsDb2File);
				//printArr(nodeWeights);
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					//player 1's turn
					sortByW(nodeWeights);
					location = output(nodeWeights, used);
					used[location] = 1;
					timesUsed++;
					turn(boardArray, location, 1);
					//JOptionPane.showMessageDialog(null, "Machine Learning 1 placan 'X' at "+location);
					drawBoard(boardArray);
					if (timesUsed >= 3 && winYet(boardArray, 1) == true) {
						
						reweightSortAndStore(weightsDbFile, nodeWeights, 1,
								weightsDb2File, nodeWeights2, 2, used);
						//endGame("Machine Learning 1");
						c1++;
						break;
					}

					if (timesUsed2 >= 3 && winYet(boardArray, 2) == true) {
						
						reweightSortAndStore(weightsDb2File, nodeWeights2, 2,
								weightsDbFile, nodeWeights, 1, used);
						//endGame("Machine Learning 2");
						c2++;
						break;
					}

					if (checkTie(boardArray)) {
						//endGame("Tie");
						t++;
						break;
					}
					//player 2's turn now
					sortByW(nodeWeights2);
					location = output(nodeWeights2, used);
					used[location] = 2;
					timesUsed2++;
					turn(boardArray, location, 2);
					//JOptionPane.showMessageDialog(null, "Machine Learning 2 placed an 'X' at "+location);
					drawBoard(boardArray);
					if (timesUsed2 >= 3 && winYet(boardArray, 2) == true) {
						
						reweightSortAndStore(weightsDb2File, nodeWeights2, 2,
								weightsDbFile, nodeWeights, 1, used);
						//endGame("Machine Learning 2");
						c2++;
						break;
					}

				} //end for
			}
			JOptionPane.showMessageDialog(null, "The Game Tied " + t + " times\nMachine Learning 1 Won " + c1
				+ " times\nMachine Learning 2 Won " + c2 + " times");
		}//end else if
	}//end ML vs ML	}//end function
}