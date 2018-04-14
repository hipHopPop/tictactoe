/*
Programmer: Mateo Silver
Program: Tic-Tac-Toe-I/O
Purpose: Computer learns to play tic tac toe using 
only machine learning. 
*/

package Game;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import AI.MLAI;
import AI.RandomAI;

public class TicTacToe {
	//computer goes first
	//0 -> blank
	//1 -> O (human 1)
	//2 -> X (computer)
	//3 -> 1 
	//4 -> 2
	//...
	final static int turns = 9;
	public static int[] boardArray = {3,4,5,6,7,8,9,10,11};
	static int location;
	public static boolean[] usedArray = {false,false,false,false,false,false,false,false,false};
	
	public static void play(File weightsDbFile, File weightsDb2File){
		int inputq = 0;
		//H v H, M v M, R v R, H v M, H v R, M v R
		String[] q = {"Player vs  Random AI", "Player vs Player", "Random AI vs Random AI", "Player vs Machine L", "Random AI vs MachineL", "MachineL vs MachineL"};
	    inputq = JOptionPane.showOptionDialog(null,"What gamemode do you want to play?","TicTacToeTacToe",-1,-1,null,q,q[0]) ;
	    drawBoard(boardArray);
		        
	    if(inputq == 0) { //Human 1 vs Random AI 
			for (int x = 0; x < 10; x++) {
				boardArray = new int[]{3,4,5,6,7,8,9,10,11};
			    drawBoard(boardArray);
				usedArray = new boolean[]{false,false,false,false,false,false,false,false,false};
				//JOptionPane.showMessageDialog(null, "You chose Human vs AI");
		    	for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//human's turn
					//JOptionPane.showMessageDialog(null, "Player 1's turn");
					location = humanInput(usedArray);
		    		usedArray[location] = true;
		    		turn(boardArray, location, 2);
		    		//JOptionPane.showMessageDialog(null, "You placed an 'X' at "+location);
		    		drawBoard(boardArray);
		    		if(winYet(boardArray,2) == true) {
		    			endGame("Player"); 
		    			break;
		    		}
		    		//Computer's turn now
					//JOptionPane.showMessageDialog(null, "Computer 1's turn");
					location = RandomAI.raiOutput();
		        	usedArray[location] = true;
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
			//JOptionPane.showMessageDialog(null, "You chose Human vs Human");
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//human 1's turn
				//JOptionPane.showMessageDialog(null, "Player 1's turn");
	    		location = humanInput(usedArray);
	    		usedArray[location] = true;
	    		turn(boardArray, location, 2);
	    		//JOptionPane.showMessageDialog(null, "Player 1 placed an 'X' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray, 2) == true) {
	    			endGame("Player 1"); 
	    			break;
	    		}
	    		
	    		//Human 2's turn now
				//JOptionPane.showMessageDialog(null, "Player 2's turn");
				location = humanInput(usedArray);
	        	usedArray[location] = true;
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
			//JOptionPane.showMessageDialog(null, "You chose Random AI vs Random AI");
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//human's turn
				//JOptionPane.showMessageDialog(null, "Random AI 1's turn");
				location = humanInput(usedArray);
	    		usedArray[location] = true;
	    		turn(boardArray, location, 2);
	    		//JOptionPane.showMessageDialog(null, "Random AI 1 placed an 'X' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray, 2) == true) {
	    			endGame("Random AI 1"); 
	    			break;
	    		}
	    		
	    		//Computer's turn now
				//JOptionPane.showMessageDialog(null, "Random AI 2's turn");
				location = RandomAI.raiOutput();
	        	usedArray[location] = true;
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
				boardArray = new int[]{3,4,5,6,7,8,9,10,11};
			    drawBoard(boardArray);
				usedArray = new boolean[]{false,false,false,false,false,false,false,false,false};
				//JOptionPane.showMessageDialog(null, "You chose Player vs Machine Learning");
				MLAI.nodeWeights = MLAI.dataInput(weightsDbFile);
				//MLAI.printArr(MLAI.nodeWeights);
				int[] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
				@SuppressWarnings("unused")
				int timesUsed = 0;
				for(int i = 0; i < Integer.MAX_VALUE; i++) {
		    		//human's turn
					//JOptionPane.showMessageDialog(null, "Player's turn");
					location = humanInput(usedArray);
		    		usedArray[location] = true;
		    		turn(boardArray, location, 2);
		    		//JOptionPane.showMessageDialog(null, "You placed an 'X' at "+location);
		    		drawBoard(boardArray);
		    		if(winYet(boardArray, 2) == true) {
		    			MLAI.reweight(false, used,MLAI.nodeWeights); 
		    			MLAI.nodeWeights = MLAI.reSort(MLAI.nodeWeights);
		    			//System.out.println("New node weights (lost)"); 
		    			System.out.println("New Weights");
		    			MLAI.printArr(MLAI.nodeWeights); 
		    			try {
							MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			endGame("Player 1"); 
		    			break;
		    		}
		    		if(checkTie(boardArray)) {endGame("Tie"); break; }
		    		//Machine Learning's turn now
		    		
					//JOptionPane.showMessageDialog(null, "Machine Learning's turn");
					MLAI.sortByW(MLAI.nodeWeights);
					location = MLAI.output(MLAI.nodeWeights,usedArray);
		        	usedArray[location] = true;
		        	used[location] = location;
		        	timesUsed++;
		    		turn(boardArray, location, 1);
		    		//JOptionPane.showMessageDialog(null, "Machine Learning placed an 'O' at "+location);
		    		drawBoard(boardArray);
		    		if(winYet(boardArray,1) == true) {
		    			MLAI.reweight(true, used, MLAI.nodeWeights); 
		    			//System.out.println("New node weights (won)"); 
		    			MLAI.nodeWeights = MLAI.reSort(MLAI.nodeWeights);
		    			System.out.println("New Weights");
		    			MLAI.printArr(MLAI.nodeWeights);
		    			try {
							MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
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
			MLAI.nodeWeights = MLAI.dataInput(weightsDbFile);
			int[] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			
			int timesUsed = 0;
			//MLAI.printArr(MLAI.nodeWeights);
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
	    		//Machine Learning's turn
				//JOptionPane.showMessageDialog(null, "Machine Learning's turn");
				MLAI.sortByW(MLAI.nodeWeights);
				location = MLAI.output(MLAI.nodeWeights,usedArray);
	        	usedArray[location] = true;
	        	used[timesUsed] = location;
	        	timesUsed++;
	    		turn(boardArray, location, 2);
	    		//JOptionPane.showMessageDialog(null, "Machine Learning placed an 'X' at "+location);
	    		drawBoard(boardArray);
	    		if(winYet(boardArray,2) == true) {
	    			MLAI.reweight(true, used, MLAI.nodeWeights); 
	    			//System.out.println("New node weights (won)"); 
	    			MLAI.nodeWeights = MLAI.reSort(MLAI.nodeWeights);
	    			System.out.println("New Weights");
	    			MLAI.printArr(MLAI.nodeWeights);
	    			try {
						MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			//System.out.println("Data stored");
	    			endGame("Machine Learning 1"); 
	    			break;
	    		}

	    		//Random AI's turn now
	    		//JOptionPane.showMessageDialog(null, "Random AI's turn");
				location = RandomAI.raiOutput();
	        	usedArray[location] = true;
	    		turn(boardArray, location, 1);
	    		//JOptionPane.showMessageDialog(null, "Random AI placed an 'O' at "+location);
	    		drawBoard(boardArray);
	    		
	    		if(winYet(boardArray,1) == true) {
	    			MLAI.reweight(false, used,MLAI.nodeWeights); 
	    			//System.out.println("New node weights (lost)"); 
	    			MLAI.nodeWeights = MLAI.reSort(MLAI.nodeWeights);
	    			System.out.println("New Weights");
	    			MLAI.printArr(MLAI.nodeWeights); 
	    			try {
						MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
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
				boardArray = new int[]{3,4,5,6,7,8,9,10,11};
				usedArray = new boolean[]{false,false,false,false,false,false,false,false,false};
				//JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
				MLAI.nodeWeights = MLAI.dataInput(weightsDbFile);
				MLAI.nodeWeight2 = MLAI.dataInput(weightsDb2File);
				//MLAI.printArr(MLAI.nodeWeights);
				int[] used = { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				int[] used2 = { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				@SuppressWarnings("unused")
				int timesUsed = 0;
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					//Machine Learning's turn
					//JOptionPane.showMessageDialog(null, "Machine Learning 1's turn");
					MLAI.sortByW(MLAI.nodeWeights);
					location = MLAI.output(MLAI.nodeWeights, usedArray);
					usedArray[location] = true;
					used[timesUsed] = location;
					timesUsed++;
					turn(boardArray, location, 1);
					//JOptionPane.showMessageDialog(null, "Machine Learning 1 placan 'X' at "+location);
					drawBoard(boardArray);
					if (winYet(boardArray, 1) == true) {
						MLAI.reweight(true, used, MLAI.nodeWeights);
						MLAI.reweight(false, used2, MLAI.nodeWeight2);
						//System.out.println("New node weights (won)"); 
						MLAI.nodeWeights = MLAI.reSort(MLAI.nodeWeights);
						System.out.println("New Weights");
						MLAI.printArr(MLAI.nodeWeights);
						try {
							MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						try {
							MLAI.dataOutput(MLAI.nodeWeights, weightsDb2File);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("Data stored");
						//endGame("Machine Learning 1");
						c1++;
						break;
					}

					if (winYet(boardArray, 2) == true) {
						MLAI.reweight(true, used2, MLAI.nodeWeight2);
						MLAI.reweight(false, used, MLAI.nodeWeights);
						//System.out.println("New node weights (won)"); 
						MLAI.nodeWeight2 = MLAI.reSort(MLAI.nodeWeight2);
						System.out.println("New Weights");
						MLAI.printArr(MLAI.nodeWeight2);
						try {
							MLAI.dataOutput(MLAI.nodeWeight2, weightsDb2File);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						try {
							MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("Data stored");
						//endGame("Machine Learning 2");
						c2++;
						break;
					}

					if (checkTie(boardArray)) {
						//endGame("Tie");
						t++;
						break;
					}
					//Machine Learning 2's turn now
					//JOptionPane.showMessageDialog(null, "Machine Learning 2's turn");
					MLAI.sortByW(MLAI.nodeWeight2);
					location = MLAI.output(MLAI.nodeWeight2, usedArray);
					usedArray[location] = true;
					used2[timesUsed] = location;
					timesUsed++;
					turn(boardArray, location, 2);
					//JOptionPane.showMessageDialog(null, "Machine Learning 2 placed an 'X' at "+location);
					drawBoard(boardArray);
					if (winYet(boardArray, 2) == true) {
						MLAI.reweight(true, used2, MLAI.nodeWeight2);
						MLAI.reweight(false, used, MLAI.nodeWeights);
						//System.out.println("New node weights (won)"); 
						MLAI.nodeWeight2 = MLAI.reSort(MLAI.nodeWeight2);
						System.out.println("New Weights");
						MLAI.printArr(MLAI.nodeWeight2);
						try {
							MLAI.dataOutput(MLAI.nodeWeight2, weightsDb2File);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						try {
							MLAI.dataOutput(MLAI.nodeWeights, weightsDbFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("Data stored");
						//endGame("Machine Learning 2");
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
	
	public static int humanInput(boolean[] usedArray) {
		int output = 0;
		while(true) {
			//is the user bad at typing?
			try {
				output = Integer.parseInt(JOptionPane.showInputDialog("What space do you want to go in?"));
			}
			catch(Exception e) {JOptionPane.showMessageDialog(null,"Try again please, I'm Disappointed.");}
			
			if(output > 8 || output < 0) {JOptionPane.showMessageDialog(null, "Please choose a whole number between 0 and 8."); continue; } //outof bounds rip
			if(usedArray[output] == true) {JOptionPane.showMessageDialog(null, "That space is already used, please choose another one."); continue;}
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

/*


*/
