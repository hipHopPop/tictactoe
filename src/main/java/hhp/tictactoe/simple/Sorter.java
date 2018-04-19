package hhp.tictactoe.simple;

public class Sorter {

	public static double[][] sortByW(double[][] nW) {
		// sort by weight [i][w]
		// uses bubble-sort
		boolean isSorted = false;
		@SuppressWarnings("unused")
		int switches = 0;

		while (isSorted == false) {
			isSorted = true;
			for (int i = 0; i < nW.length - 1; i++) {
				if (nW[i][1] < nW[i + 1][1]) {
					double[] hold = nW[i + 1];
					nW[i + 1] = nW[i];
					nW[i] = hold;
					// System.out.println("Switched " +nW[i][0]+ " with " +nW[i+1][0]);
					switches++;
					isSorted = false;
				} // end if i > i+1
			} // end for int i...
		} // end while sorted is false

		// return sorted array
		return nW;
	}

	public static double[][] reSort(double[][] nW) {
		boolean isSorted = false;
		@SuppressWarnings("unused")
		int switches = 0;

		while (isSorted == false) {
			isSorted = true;
			for (int i = 0; i < nW.length - 1; i++) {
				if (nW[i][0] > nW[i + 1][0]) {
					double[] hold = nW[i + 1];
					nW[i + 1] = nW[i];
					nW[i] = hold;
					// System.out.println("Switched " +nW[i][0]+ " with " +nW[i+1][0]);
					switches++;
					isSorted = false;
				} // end if i > i+1
			} // end for int i...
		} // end while sorted is false

		// return sorted array
		return nW;
	}

}
