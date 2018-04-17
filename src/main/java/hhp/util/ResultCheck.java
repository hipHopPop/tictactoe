package hhp.util;

import javax.swing.JOptionPane;

public class ResultCheck {

	public static boolean checkTie(int[] functionBoardArray) {
		int noOfPieces = 0;

		for (int i = 0; i < functionBoardArray.length; i++) {
			if (functionBoardArray[i] == 1 || functionBoardArray[i] == 2) {
				noOfPieces++;
			}
		}

		if (noOfPieces >= 9) {
			return true;
		} else {
			//TODO: check if tie even if < 9 
			return false;
		}
	}

	// Made to accept "Tie", "Human 1", "Human 2", "Computer 1", "Computer 2"
	public static void endGame(String input) {

		if (input == "Tie") {
			JOptionPane.showMessageDialog(null, "The Game Tied");
		} else {
			JOptionPane.showMessageDialog(null, input + " Won");
		}

	}

	public static boolean winYet(int[] functionBoardArray, int w) {
		// O's win states
		if (w == 1) {
			if (functionBoardArray[0] == 1 & functionBoardArray[1] == 1 & functionBoardArray[2] == 1) {
				return true;
			} else if (functionBoardArray[3] == 1 & functionBoardArray[4] == 1 & functionBoardArray[5] == 1) {
				return true;
			} else if (functionBoardArray[6] == 1 & functionBoardArray[7] == 1 & functionBoardArray[8] == 1) {
				return true;
			} else if (functionBoardArray[0] == 1 & functionBoardArray[3] == 1 & functionBoardArray[6] == 1) {
				return true;
			} else if (functionBoardArray[1] == 1 & functionBoardArray[4] == 1 & functionBoardArray[7] == 1) {
				return true;
			} else if (functionBoardArray[2] == 1 & functionBoardArray[5] == 1 & functionBoardArray[8] == 1) {
				return true;
			} else if (functionBoardArray[0] == 1 & functionBoardArray[4] == 1 & functionBoardArray[8] == 1) {
				return true;
			} else if (functionBoardArray[6] == 1 & functionBoardArray[4] == 1 & functionBoardArray[2] == 1) {
				return true;
			}
		}
		// X's win states
		if (w == 2) {
			if (functionBoardArray[0] == 2 & functionBoardArray[1] == 2 & functionBoardArray[2] == 2) {
				return true;
			} else if (functionBoardArray[3] == 2 & functionBoardArray[4] == 2 & functionBoardArray[5] == 2) {
				return true;
			} else if (functionBoardArray[6] == 2 & functionBoardArray[7] == 2 & functionBoardArray[8] == 2) {
				return true;
			} else if (functionBoardArray[0] == 2 & functionBoardArray[3] == 2 & functionBoardArray[6] == 2) {
				return true;
			} else if (functionBoardArray[1] == 2 & functionBoardArray[4] == 2 & functionBoardArray[7] == 2) {
				return true;
			} else if (functionBoardArray[2] == 2 & functionBoardArray[5] == 2 & functionBoardArray[8] == 2) {
				return true;
			} else if (functionBoardArray[0] == 2 & functionBoardArray[4] == 2 & functionBoardArray[8] == 2) {
				return true;
			} else if (functionBoardArray[6] == 2 & functionBoardArray[4] == 2 & functionBoardArray[2] == 2) {
				return true;
			}
		}

		return false;
	}

}
