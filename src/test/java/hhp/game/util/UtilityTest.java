package hhp.game.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import hhp.game.util.Printer;
import hhp.game.util.Utility;

public class UtilityTest {

	@Test
	public void test() {

		int[] usedArr = { 3, 4, 6 };

		System.out.println("\n");

		System.out.println("dataInput3()");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		Printer.printArr(Utility.nodeWeights);

		System.out.println("\nReweight");
		Utility.reweight(true, usedArr, Utility.nodeWeights, 1);
		Printer.printArr(Utility.nodeWeights);

		System.out.println("\ndataOutput()");
		try {
			File weightsDbFile = new File(Utility.class.getResource("/WeightsDatabase.txt").toURI());

			Utility.dataOutput(Utility.nodeWeights, weightsDbFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// System.out.println("read new weights");
		// nodeWeights = dataInput3();
		// printArr(nodeWeights);

	}

}
/*
Steps that the alg takes:
1:sort nodeWeights[][] by weight
2.choose one of top 3?
3.output choice
4.after game get data and reweight(+-0.05 win/loss)
5.rinse and repeat

import java.io.*; 

 */