package Game;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.JOptionPane;

import AI.MLAI;

public class Trainer {
	public static void main(String[] args){
	
		for(int i = 0; i < 100; i++) {
			

			JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
			MLAI.nodeWeights = MLAI.dataInput(".\\.\\WeightsDatabase.txt");
			MLAI.nodeWeight2 = MLAI.dataInput(".\\.\\WeightsDatabase2.txt");
			//MLAI.printArr(MLAI.nodeWeights);
			int[] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			int[] used2 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			@SuppressWarnings("unused")
			int timesUsed = 0;
			for(int k = 0; k < Integer.MAX_VALUE; k++) {
	    		//Machine Learning's turn
				JOptionPane.showMessageDialog(null, "Machine Learning 1's turn");
				MLAI.sortByW(MLAI.nodeWeights);
				TicTacToe.location = MLAI.output(MLAI.nodeWeights,TicTacToe.usedArray);
				TicTacToe.usedArray[TicTacToe.location] = true;
	        	used[timesUsed] = TicTacToe.location;
	        	timesUsed++;
	        	TicTacToe.turn(TicTacToe.boardArray, TicTacToe.location, 1);
	    		JOptionPane.showMessageDialog(null, "Machine Learning 1 placed an 'X' at "+TicTacToe.location);
	    		TicTacToe.drawBoard(TicTacToe.boardArray);
	    		if(TicTacToe.winYet(TicTacToe.boardArray,1) == true) {
	    			MLAI.reweight(true, used, MLAI.nodeWeights); 
	    			MLAI.reweight(false, used2, MLAI.nodeWeight2);
	    			//System.out.println("New node weights (won)"); 
	    			MLAI.nodeWeights = MLAI.reSort(MLAI.nodeWeights);
	    			System.out.println("New Weights");
	    			MLAI.printArr(MLAI.nodeWeights);
	    			try {
						MLAI.dataOutput(MLAI.nodeWeights, ".\\\\.\\\\WeightsDatabase.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					}
	    			
	    			try {
						MLAI.dataOutput(MLAI.nodeWeights, ".\\\\.\\\\WeightsDatabase2.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			//System.out.println("Data stored");
	    			TicTacToe.endGame("Machine Learning 1"); 
	    			restart();
	    			break;
	    		}
	    		
	    		if(TicTacToe.checkTie(TicTacToe.boardArray)) {TicTacToe.endGame("Tie"); break; }
	    		//Machine Learning 2's turn now
				JOptionPane.showMessageDialog(null, "Machine Learning 2's turn");
				MLAI.sortByW(MLAI.nodeWeight2);
				TicTacToe.location = MLAI.output(MLAI.nodeWeight2,TicTacToe.usedArray);
				TicTacToe.usedArray[TicTacToe.location] = true;
	        	used2[timesUsed] = TicTacToe.location;
	        	timesUsed++;
	        	TicTacToe.turn(TicTacToe.boardArray, TicTacToe.location, 2);
	    		JOptionPane.showMessageDialog(null, "Machine Learning 2 placed an 'X' at "+TicTacToe.location);
	    		TicTacToe.drawBoard(TicTacToe.boardArray);
	    		if(TicTacToe.winYet(TicTacToe.boardArray,2) == true) {
	    			MLAI.reweight(true, used2, MLAI.nodeWeight2);
	    			MLAI.reweight(false, used, MLAI.nodeWeights);
	    			//System.out.println("New node weights (won)"); 
	    			MLAI.nodeWeight2 = MLAI.reSort(MLAI.nodeWeight2);
	    			System.out.println("New Weights");
	    			MLAI.printArr(MLAI.nodeWeight2);
	    			try {
						MLAI.dataOutput(MLAI.nodeWeight2, ".\\\\.\\\\WeightsDatabase2.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	    			try {
						MLAI.dataOutput(MLAI.nodeWeights, ".\\\\.\\\\WeightsDatabase.txt");
					} catch (IOException e)
	    			
	    			
	    			{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			//System.out.println("Data stored");
	    			TicTacToe.endGame("Machine Learning 2"); 
	    			restart();

	    			break;
	    		}
	    		
			  }//end for
		}
	
	
		
	}
	public static void restart() {
        StringBuilder cmd = new StringBuilder();
          cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
          for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
              cmd.append(jvmArg + " ");
          }
          cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
          cmd.append(Window.class.getName()).append(" ");

          try {
              Runtime.getRuntime().exec(cmd.toString());
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          
          
          
          
          
          System.exit(0);
  }
}
/*
 * 

*/
