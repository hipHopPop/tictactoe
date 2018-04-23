package hhp.tictactoe.learning.supervised;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class Utility {

	/**
	 * input data is "win for x", has weights of x
	 */
	public static List<Integer> getDataCount(URL gameImagesURL) throws URISyntaxException, IOException {
		
		List<Integer> weightList = new ArrayList<>(Arrays.asList(new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }));
		
		Files.lines(Paths.get(gameImagesURL.toURI())).filter(line -> line.endsWith("positive")).forEach(line -> {
			line = line.replaceAll(",positive", "");
			String[] s = line.split(",");
			IntStream.range(0, s.length).filter(i -> s[i].equalsIgnoreCase("x")).forEach(i -> {
				Integer positionCount = weightList.get(i);
				weightList.set(i, (positionCount + 1));
			});
		});
		return weightList;
	}

	public static int humanInput(List<String> used) {
		Integer output = null;
		while (true) {
			output = null;
			// is the user bad at typing?
			try {
				output = Integer.parseInt(JOptionPane.showInputDialog("What space do you want to go in?"));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Try again please, I'm Disappointed.");
			}
			if (output == null) {
				continue;
			}
			if (output > 8 || output < 0) {
				JOptionPane.showMessageDialog(null, "Please choose a whole number between 0 and 8.");
				continue;
			} // outof bounds rip
			if (!used.get(output).equalsIgnoreCase("b")) {
				JOptionPane.showMessageDialog(null, "That space is already used, please choose another one.");
				continue;
			} else {
				break;
			}
		}
		return output;
	}

	public static int machineLearningInput(List<String> gameData, Classifier classifier) throws Exception {
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		if (gameDataCsv.equalsIgnoreCase("b,b,b,b,b,b,b,b,b")) {
			return classifier.getHighestRankedPosition();
		}
		return classifier.getBestRankedPosition(gameData);
	}

	public static int randomInput(List<String> gameData) {
		int locationInput;
		Random rand = new Random();

		do {
			locationInput = rand.nextInt(9) + 0;
		} while (!gameData.get(locationInput).equalsIgnoreCase("b"));

		return locationInput;
	}

	public static int[] turn(int[] functionBoardArray, int location, String piece) {
		functionBoardArray[location] = piece.equalsIgnoreCase("x") ? 1 : 2;
		return functionBoardArray;
	}
}
