package com.hhp.game;

import static com.hhp.game.util.Utility.dataInput;
import static com.hhp.game.util.Utility.dataOutput;
import static com.hhp.game.util.Utility.nodeWeights;
import static com.hhp.game.util.Utility.nodeWeights2;
import static com.hhp.game.util.Utility.output;
import static com.hhp.game.util.Utility.printArr;
import static com.hhp.game.util.Utility.reSort;
import static com.hhp.game.util.Utility.reweight;
import static com.hhp.game.util.Utility.reweightSortAndStore;
import static com.hhp.game.util.Utility.sortByW;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.hhp.game.util.RandomAI;

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
					location 		= RandomAI.raiOutput();
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
				location 		= RandomAI.raiOutput();
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
				location = RandomAI.raiOutput();
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
    	
	
	public static boolean checkTie(int[] functionBoardArray) {
		int noOfPieces = 0;
		
		//all spots taken up by a piece have a value of 1 or 2. If the sum of all the pieces is => 9, the board is full of pieces
		for(int i = 0; i < functionBoardArray.length; i++) {
			if(functionBoardArray[i] == 1 || functionBoardArray[i] == 2) {
				noOfPieces ++;
			}
		}
	
		if (noOfPieces >= 9) {
			return true;
		} else {
			return false;
		}
	}

	//Made to accept "Tie", "Human 1", "Human 2", "Computer 1", "Computer 2"
	public static void endGame(String input){
		
		if(input == "Tie") {
			JOptionPane.showMessageDialog(null, "The Game Tied");
		}
		else{
			JOptionPane.showMessageDialog(null, input+" Won");
		}
		
	}
	
	public static boolean winYet(int[] functionBoardArray, int w) {
		//O's win states
		if(w == 1) {
			if(functionBoardArray[0]  == 1 & functionBoardArray[1]   == 1 & functionBoardArray[2] == 1) {return true;}
			else if(functionBoardArray[3]   == 1 & functionBoardArray[4]   == 1 & functionBoardArray[5] == 1) {return true;}
			else if(functionBoardArray[6]   == 1 & functionBoardArray[7]   == 1 & functionBoardArray[8] == 1) {return true;}
			else if(functionBoardArray[0]   == 1 & functionBoardArray[3]   == 1 & functionBoardArray[6] == 1) {return true;}
			else if(functionBoardArray[1]   == 1 & functionBoardArray[4]   == 1 & functionBoardArray[7] == 1) {return true;}
			else if(functionBoardArray[2]   == 1 & functionBoardArray[5]   == 1 & functionBoardArray[8] == 1) {return true;}
			else if(functionBoardArray[0]   == 1 & functionBoardArray[4]   == 1 & functionBoardArray[8] == 1) {return true;}
			else if(functionBoardArray[6]   == 1 & functionBoardArray[4]   == 1 & functionBoardArray[2] == 1) {return true;}	
		}
		//X's win states
		if(w == 2){
			if(functionBoardArray[0]  == 2 & functionBoardArray[1]   == 2 & functionBoardArray[2] == 2) {return true;}
			else if(functionBoardArray[3]   == 2 & functionBoardArray[4]   == 2 & functionBoardArray[5] == 2) {return true;}
			else if(functionBoardArray[6]   == 2 & functionBoardArray[7]   == 2 & functionBoardArray[8] == 2) {return true;}
			else if(functionBoardArray[0]   == 2 & functionBoardArray[3]   == 2 & functionBoardArray[6] == 2) {return true;}
			else if(functionBoardArray[1]   == 2 & functionBoardArray[4]   == 2 & functionBoardArray[7] == 2) {return true;}
			else if(functionBoardArray[2]   == 2 & functionBoardArray[5]   == 2 & functionBoardArray[8] == 2) {return true;}
			else if(functionBoardArray[0]   == 2 & functionBoardArray[4]   == 2 & functionBoardArray[8] == 2) {return true;}
			else if(functionBoardArray[6]   == 2 & functionBoardArray[4]   == 2 & functionBoardArray[2] == 2) {return true;}
		}
		
		return false;
	}
	
	public static int humanInput(int[] used) {
		int output = 0;
		while(true) {
			//is the user bad at typing?
			try {
				output = Integer.parseInt(JOptionPane.showInputDialog("What space do you want to go in?"));
			}
			catch(Exception e) {JOptionPane.showMessageDialog(null,"Try again please, I'm Disappointed.");}
			
			if(output > 8 || output < 0) {JOptionPane.showMessageDialog(null, "Please choose a whole number between 0 and 8."); continue; } //outof bounds rip
			if(used[output] != -1) {JOptionPane.showMessageDialog(null, "That space is already used, please choose another one."); continue;}
			else {break;}
		}
		return output; 
	}
	
	public static int[] turn(int[] functionBoardArray, int location, int piece) {
		//use boardArray, accept input, change array, return array
		functionBoardArray[location] = piece;
		return functionBoardArray;
	}
	
	public static void drawBoard(int[] boardArray) {
		String[] stringArray = {"","","","","","","","",""};
		//e.g. boardArray = {0,1,2,0,2,0,1,0,2};
		for(int i = 0; i < 50; i++) {
		System.out.println("\n");
		}	
		for(int i = 0; i < boardArray.length; i++) {
			if(boardArray[i] == 0) {
				stringArray[i] = "   ";
			}
			else if(boardArray[i] == 1) {
				stringArray[i] = " O ";
			}
			else if(boardArray[i] == 2) {
				stringArray[i] = " X ";
			}
			
			//what are numbers
			else if(boardArray[i] == 3) {
				stringArray[i] = " 0 ";
			}
			else if(boardArray[i] == 4) {
				stringArray[i] = " 1 ";
			}
			else if(boardArray[i] == 5) {
				stringArray[i] = " 2 ";
			}
			else if(boardArray[i] == 6) {
				stringArray[i] = " 3 ";
			}
			else if(boardArray[i] == 7) {
				stringArray[i] = " 4 ";
			}
			else if(boardArray[i] == 8) {
				stringArray[i] = " 5 ";
			}
			else if(boardArray[i] == 9) {
				stringArray[i] = " 6 ";
			}
			else if(boardArray[i] == 10) {
				stringArray[i] = " 7 ";
			}
			else if(boardArray[i] == 11) {
				stringArray[i] = " 8 ";
			}
		}
		System.out.print("\n"+stringArray[0]+"|"+stringArray[1]+"|"+stringArray[2]+"\n-----------\n"+stringArray[3]+"|"+stringArray[4]+"|"+stringArray[5]+"\n-----------\n"+stringArray[6]+"|"+stringArray[7]+"|"+stringArray[8]+"\n");
	}
}