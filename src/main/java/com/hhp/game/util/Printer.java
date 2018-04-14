package com.hhp.game.util;

public class Printer {

	public static void printArr(double[][] arr) {
		System.out.println("Node  Weight");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i][0] + "   ");
			System.out.print(arr[i][1] + "\n");
		}
	}// end function

	public static void drawBoard(int[] boardArray) {
		String[] stringArray = { "", "", "", "", "", "", "", "", "" };
		// e.g. boardArray = {0,1,2,0,2,0,1,0,2};
		for (int i = 0; i < 50; i++) {
			System.out.println("\n");
		}
		for (int i = 0; i < boardArray.length; i++) {
			if (boardArray[i] == 0) {
				stringArray[i] = "   ";
			} else if (boardArray[i] == 1) {
				stringArray[i] = " O ";
			} else if (boardArray[i] == 2) {
				stringArray[i] = " X ";
			}

			// what are numbers
			else if (boardArray[i] == 3) {
				stringArray[i] = " 0 ";
			} else if (boardArray[i] == 4) {
				stringArray[i] = " 1 ";
			} else if (boardArray[i] == 5) {
				stringArray[i] = " 2 ";
			} else if (boardArray[i] == 6) {
				stringArray[i] = " 3 ";
			} else if (boardArray[i] == 7) {
				stringArray[i] = " 4 ";
			} else if (boardArray[i] == 8) {
				stringArray[i] = " 5 ";
			} else if (boardArray[i] == 9) {
				stringArray[i] = " 6 ";
			} else if (boardArray[i] == 10) {
				stringArray[i] = " 7 ";
			} else if (boardArray[i] == 11) {
				stringArray[i] = " 8 ";
			}
		}
		System.out.print("\n" + stringArray[0] + "|" + stringArray[1] + "|" + stringArray[2] + "\n-----------\n"
				+ stringArray[3] + "|" + stringArray[4] + "|" + stringArray[5] + "\n-----------\n" + stringArray[6]
				+ "|" + stringArray[7] + "|" + stringArray[8] + "\n");
	}

}
