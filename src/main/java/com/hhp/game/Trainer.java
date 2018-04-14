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
import static com.hhp.game.util.Utility.nodeWeights;
import static com.hhp.game.util.Utility.nodeWeights2;
import static com.hhp.game.util.Utility.output;
import static com.hhp.game.util.Utility.reweight;
import static com.hhp.game.util.Utility.turn;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;


public class Trainer {
	public static void main(String[] args) throws URISyntaxException{
		File weightsDbFile = new File(Trainer.class.getResource("/WeightsDatabase.txt").toURI());
		File weightsDb2File = new File(Trainer.class.getResource("/WeightsDatabase2.txt").toURI());
		for(int i = 0; i < 100; i++) {
			

			JOptionPane.showMessageDialog(null, "You chose Machine Learning vs Machine Learning");
			nodeWeights = dataInput(weightsDbFile);
			nodeWeights2 = dataInput(weightsDb2File);
			//printArr(nodeWeights);
			int[] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			int[] used2 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
			int timesUsed = 0;
			for(int k = 0; k < Integer.MAX_VALUE; k++) {
	    		//Machine Learning's turn
				JOptionPane.showMessageDialog(null, "Machine Learning 1's turn");
				sortByW(nodeWeights);
				TicTacToe.location = output(nodeWeights,TicTacToe.used);
				TicTacToe.used[TicTacToe.location] = TicTacToe.location;
	        	used[timesUsed] = TicTacToe.location;
	        	timesUsed++;
	        	turn(TicTacToe.boardArray, TicTacToe.location, 1);
	    		JOptionPane.showMessageDialog(null, "Machine Learning 1 placed an 'X' at "+TicTacToe.location);
	    		drawBoard(TicTacToe.boardArray);
	    		if(winYet(TicTacToe.boardArray,1) == true) {
	    			reweight(true, used, nodeWeights, 1); 
	    			reweight(false, used2, nodeWeights2, 2);
	    			//System.out.println("New node weights (won)"); 
	    			nodeWeights = reSort(nodeWeights);
	    			System.out.println("New Weights");
	    			printArr(nodeWeights);
	    			try {
						dataOutput(nodeWeights, weightsDbFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					}
	    			
	    			try {
						dataOutput(nodeWeights, weightsDb2File);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			//System.out.println("Data stored");
	    			endGame("Machine Learning 1"); 
	    			restart();
	    			break;
	    		}
	    		
	    		if(checkTie(TicTacToe.boardArray)) {endGame("Tie"); break; }
	    		//Machine Learning 2's turn now
				JOptionPane.showMessageDialog(null, "Machine Learning 2's turn");
				sortByW(nodeWeights2);
				TicTacToe.location = output(nodeWeights2,TicTacToe.used);
				TicTacToe.used[TicTacToe.location] = TicTacToe.location;
	        	used2[timesUsed] = TicTacToe.location;
	        	timesUsed++;
	        	turn(TicTacToe.boardArray, TicTacToe.location, 2);
	    		JOptionPane.showMessageDialog(null, "Machine Learning 2 placed an 'X' at "+TicTacToe.location);
	    		drawBoard(TicTacToe.boardArray);
	    		if(winYet(TicTacToe.boardArray,2) == true) {
	    			reweight(true, used2, nodeWeights2, 2);
	    			reweight(false, used, nodeWeights, 1);
	    			//System.out.println("New node weights (won)"); 
	    			nodeWeights2 = reSort(nodeWeights2);
	    			System.out.println("New Weights");
	    			printArr(nodeWeights2);
	    			try {
						dataOutput(nodeWeights2, weightsDb2File);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	    			try {
						dataOutput(nodeWeights, weightsDbFile);
					} catch (IOException e)
	    			
	    			
	    			{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			//System.out.println("Data stored");
	    			endGame("Machine Learning 2"); 
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
