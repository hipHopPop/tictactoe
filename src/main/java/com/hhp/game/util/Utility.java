package com.hhp.game.util;

import static com.hhp.game.util.Sorter.reSort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Utility {
	static double w0 = 0.50,w1 = 0.50,w2 = 0.50,w3 = 0.50,w4 = 0.50,w5 = 0.50,w6 = 0.50,w7 = 0.50,w8 = 0.50;
	public static double[][] nodeWeights = { {0,w0}, {1,w1}, {2,w2}, {3,w3}, {4,w4}, {5,w5}, {6,w6}, {7,w7}, {8,w8} };
	static double[] weights = {w0, w1, w2, w3, w4, w5, w6, w7, w8};

	public static double[][] nodeWeights2 = { {0,w0}, {1,w1}, {2,w2}, {3,w3}, {4,w4}, {5,w5}, {6,w6}, {7,w7}, {8,w8} };
	static double[] weights2 = {w0, w1, w2, w3, w4, w5, w6, w7, w8};

	//nodeWeights[node][weight]
	
	public static double[][] dataInput(File filePath) {
		double[][] output = { { 0, w0 }, { 1, w1 }, { 2, w2 }, { 3, w3 }, { 4, w4 }, { 5, w5 }, { 6, w6 }, { 7, w7 },
				{ 8, w8 } };
		try {
			Scanner sc = new Scanner(filePath);
			while (sc.hasNextLine()) {
				for (int i = 0, rep = 0; i < 9; i++) {
					String str = sc.nextLine();
					// System.out.println("str:"+str);
					output[rep][1] = Double.parseDouble(str);
					rep++;
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
   
   public static void dataOutput(double[][] nW, File filePath) throws IOException {
	   PrintWriter pw = new PrintWriter(new FileWriter(filePath));
	 
		for (int i = 0; i < nW.length; i++) {
			pw.write(Double.toString(nW[i][1]));
			pw.write("\n");
		}
		pw.close();

	}
	
	public static int output(double[][] nW, int[] used) {
		int out = 0;
		
		while (true) {
			for (int i = 0; i < nW.length; i++) {
				out = (int) nW[i][0];
				if (used[out] != -1) {
					continue;
				} else {
					return out;
				}
			}
			if (out >= 9) {
				System.out.println("java machine broke");
			} else {
				return out;
			}
		}
	}
	
	public static double[][] reweight(boolean win, int[] used, double[][] nW, int player){
		if(win) {
			for(int i = 0; i<nW.length; i++) {
				for(int j = 0; j<used.length; j++) {
					//if the int in the used array matches the int in the nodeWeights[][]
					if(nW[i][0] == j && used[j] == player) {
						nW[i][1] = nW[i][1] + 0.05;
						@SuppressWarnings("unused")
						String arrS = Arrays.toString(nW[i]);
						//System.out.println(arrS+" was reweighted");
					}
				}
			}
		}
		else if(win == false) {
			for(int i = 0; i<nW.length; i++) {
				for(int j = 0; j<used.length; j++) {
					//if the int in the used array matches the int in the nodeWeights[][]
					if(nW[i][0] == j && used[j] == player) {
						nW[i][1] = nW[i][1] - 0.05;
						@SuppressWarnings("unused")
						String arrS = Arrays.toString(nW[i]);
						//System.out.println(arrS+" was reweighted");
					}
				}
			}
		}
		return nW;
	}
	
	public static void reweightSortAndStore(File winningWeightsDbFile, double[][] winningNodeWeights, int winningPlayer,
			File losingWeightsDbFile, double[][] losingNodeWeights, int losingPlayer, int[] used) {
		reweight(true, used, winningNodeWeights, winningPlayer);
		reweight(false, used, losingNodeWeights, losingPlayer);
		// System.out.println("New node weights (won)");
		winningNodeWeights = Sorter.reSort(winningNodeWeights);
		//System.out.println("New Weights");
		//printArr(winningNodeWeights);
		try {
			dataOutput(winningNodeWeights, winningWeightsDbFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			dataOutput(losingNodeWeights, losingWeightsDbFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reweightSortAndStore(File weightsDbFile, double[][] nodeWeights, int player, int[] used, boolean win) {
		reweight(win, used,nodeWeights, player); 
		nodeWeights = reSort(nodeWeights);
		//System.out.println("New node weights (lost)"); 
		//System.out.println("New Weights");
		//printArr(nodeWeights); 
		try {
			dataOutput(nodeWeights, weightsDbFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int randomOutput(int[] used) {
		int locationOutput;
		Random rand = new Random();
		
		do {
			locationOutput = rand.nextInt(9) + 0;
		}
		while(used[locationOutput] != -1);
		
		return locationOutput; 
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
}
