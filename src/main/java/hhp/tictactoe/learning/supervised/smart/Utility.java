package hhp.tictactoe.learning.supervised.smart;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import hhp.tictactoe.learning.algo.classification.Classifier;

public class Utility {
	
	public static int humanInput(List<String> used) {
		Integer output = null;
		while(true) {
			output = null;
			//is the user bad at typing?
			try {
				output = Integer.parseInt(JOptionPane.showInputDialog("What space do you want to go in?"));
			}
			catch(Exception e) {JOptionPane.showMessageDialog(null,"Try again please, I'm Disappointed.");}
			if(output == null) {continue;}
			if(output > 8 || output < 0) {JOptionPane.showMessageDialog(null, "Please choose a whole number between 0 and 8."); continue; } //outof bounds rip
			if( ! used.get(output).equalsIgnoreCase("b")) {JOptionPane.showMessageDialog(null, "That space is already used, please choose another one."); continue;}
			else {break;}
		}
		return output; 
	}
	
	public static int machineLearningInput(List<String> gameData, Classifier classifier, String player) throws Exception {
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		if (gameDataCsv.equalsIgnoreCase("b,b,b,b,b,b,b,b,b")) {
			//get highest ranked image
			String highestRankedImage = classifier.getHighestRankedImage();
			String[] split = highestRankedImage.split(",");
			for (int i = 0; i < split.length; i++) {
				if (split[i].equalsIgnoreCase("x")) {
					return i;
				}
			}
		}
		Stream<Entry<String, Integer>> bestRankedImages = classifier.getBestRankedImages(gameData);
		for (Iterator<Entry<String, Integer>> iterator = bestRankedImages.iterator(); iterator.hasNext();) {
			String[] split = iterator.next().getKey().split(",");
			for (int i = 0; i < split.length; i++) {
				if (split[i].equalsIgnoreCase(player) && gameData.get(i).equalsIgnoreCase("b")) {
					return i;
				}
			}
		}
		throw new Exception("unable to find a location for machine learning..");
	}

	public static int randomInput(List<String> gameData) {
		int locationInput;
		Random rand = new Random();
		
		do {
			locationInput = rand.nextInt(9) + 0;
		}
		while( ! gameData.get(locationInput).equalsIgnoreCase("b"));
		
		return locationInput; 
	}
	
	public static int[] turn(int[] functionBoardArray, int location, String piece) {
		functionBoardArray[location] = piece.equalsIgnoreCase("x") ? 1 : 2;
		return functionBoardArray;
	}

	public static List<String> loadGameImages(URL gameImagesURL) throws URISyntaxException {
		try (Stream<String> stream = Files.lines(Paths.get(gameImagesURL.toURI()))) {

			List<String> positiveGames = stream
					.filter(line -> line.endsWith("positive"))
					.collect(Collectors.toList());
			return positiveGames;

		} catch (IOException e) {
			e.printStackTrace();
		}
		//positiveGames.forEach(System.out::println);
		//negativeGames.forEach(System.out::println);
		return null;
	}
}
