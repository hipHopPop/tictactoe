package hhp.tictactoe.simple.smart.positionmatch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

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
	
	public static int machineLearningInput(List<String> gameData, Classifier classifier) throws Exception {
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		if (gameDataCsv.equalsIgnoreCase("b,b,b,b,b,b,b,b,b")) {
			//get highest ranked game data
			String highestRankedGameData = classifier.getHighestRankedGameData();
			String[] split = highestRankedGameData.split(",");
			List<Integer> forRandomPick = new ArrayList<>();
			for (int i = 0; i < split.length; i++) {
				if (split[i].equalsIgnoreCase("x")) {
					forRandomPick.add(i);
				}
			}
			return forRandomPick.get(new Random().nextInt(forRandomPick.size()));
		}
		Map<String, Stream<Map.Entry<String, Integer>>> anyAndAllPositionMatch = classifier.filterForAnyAndAllPositionMatch(gameData);
		//Map<String, Integer> allPositionMatch = anyAndAllPositionMatch.get("all");
		for (Iterator<Entry<String, Integer>> iterator = anyAndAllPositionMatch.get("any").iterator(); iterator.hasNext();) {
			String[] split = iterator.next().getKey().split(",");
			List<Integer> forRandomPick = new ArrayList<>();
			for (int i = 0; i < split.length; i++) {
				if (split[i].equalsIgnoreCase("x") && gameData.get(i).equalsIgnoreCase("b")) {//checking for x because input data is "win for x"
					forRandomPick.add(i);
				}
			}
			if (forRandomPick.size() > 0) {
				return forRandomPick.get(new Random().nextInt(forRandomPick.size()));
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

	public static List<String> loadGameData(URL gameDataURL) throws URISyntaxException {
		try (Stream<String> stream = Files.lines(Paths.get(gameDataURL.toURI()))) {

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
