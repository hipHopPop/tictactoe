package hhp.tictactoe.learning.supervised;

import static hhp.tictactoe.learning.supervised.Utility.getDataCount;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Classifier {

	private List<Integer> weightList 	= null;// input data is "win for x", has weights of x
	public static List<String> players 	= Arrays.asList(new String[] { "x", "o" });
	
	class Pair {
		int bestPosition 	= -1;
		int bestWeight 		= Integer.MIN_VALUE;
	}
	
	public Classifier(URL gameImagesURL) throws URISyntaxException, IOException {

		weightList = getDataCount(gameImagesURL);
	}
	
	public Classifier(List<Integer> weightListClone) {
		weightList = weightListClone;
	}

	public int getHighestRankedPosition() {
		// int minIdx = IntStream.range(0, weightList.size()).reduce((i, j) -> weightList.get(i) > weightList.get(j) ? j : i).getAsInt();
		return IntStream.range(0, weightList.size()).reduce((i, j) -> weightList.get(i) > weightList.get(j) ? i : j).getAsInt();
	}

	public int getBestRankedPosition(List<String> gameData) {
		Pair positionWeight = new Pair();
		IntStream.range(0, gameData.size()).filter(i -> gameData.get(i).equalsIgnoreCase("b")).forEach(i -> {
			if (positionWeight.bestWeight < weightList.get(i)) {
				positionWeight.bestPosition = i;
				positionWeight.bestWeight = weightList.get(i);
			}
		});
		return positionWeight.bestPosition;
	}

	public void increaseWeight(List<String> gameData, String player) {
		IntStream.range(0, gameData.size()).filter(i -> gameData.get(i).equalsIgnoreCase(player)).forEach(i -> {
			Integer positionCount = weightList.get(i);
			weightList.set(i, (positionCount + 1));
		});
	}

	public void decreaseWeight(List<String> gameData, String player) {
		IntStream.range(0, gameData.size()).filter(i -> ! gameData.get(i).equalsIgnoreCase(player)).forEach(i -> {
			Integer positionCount = weightList.get(i);
			weightList.set(i, (positionCount - 1));
		});
	}

	public void printClassification() {
		System.out.println("x - " + weightList.stream().map(Object::toString).collect(Collectors.joining(",")));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Classifier clone() throws CloneNotSupportedException {
		return new Classifier((List<Integer>) ((ArrayList<Integer>) weightList).clone());
	}
}
