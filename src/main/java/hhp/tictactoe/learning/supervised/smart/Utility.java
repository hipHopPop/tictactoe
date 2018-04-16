package hhp.tictactoe.learning.supervised.smart;

import java.util.List;

import javax.swing.JOptionPane;

public class Utility {
	
	public static int humanInput(List<String> used) {
		int output = 0;
		while(true) {
			//is the user bad at typing?
			try {
				output = Integer.parseInt(JOptionPane.showInputDialog("What space do you want to go in?"));
			}
			catch(Exception e) {JOptionPane.showMessageDialog(null,"Try again please, I'm Disappointed.");}
			
			if(output > 8 || output < 0) {JOptionPane.showMessageDialog(null, "Please choose a whole number between 0 and 8."); continue; } //outof bounds rip
			if( ! used.get(output).equalsIgnoreCase("b")) {JOptionPane.showMessageDialog(null, "That space is already used, please choose another one."); continue;}
			else {break;}
		}
		return output; 
	}
	
	public static String[] turn(String[] functionBoardArray, int location, String piece) {
		functionBoardArray[location] = piece;
		return functionBoardArray;
	}
}
