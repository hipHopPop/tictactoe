package hhp.tictactoe.learning.supervised.smart.positionmatch.classification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import hhp.util.MapUtil;

public class Classifier {

	private Map<String, Map<Integer, Map<String, Integer>>> playerPositionMap = new HashMap<>();
	private Map<String, Integer> fmForHighestRank 	= new HashMap<>();
	private Map<String, Integer> fmForBestRank 		= new HashMap<>();
	public static List<String> players = Arrays.asList(new String[]{"x", "o"});

	public void setDefaultWeight(int position, String player, String image) {
		if ( ! players.contains(player)) {
			return;
		}
		Map<Integer, Map<String, Integer>> positionMap = playerPositionMap.get(player);
		if (positionMap == null) {
			positionMap = new HashMap<>();
		}
		Map<String, Integer> featureMap = positionMap.get(position);
		if (featureMap == null) {
			featureMap = new HashMap<>();
			featureMap.put(image, 1);
			fmForHighestRank.put(image, 1);
		} else {
			Integer weight = featureMap.get(image);
			weight = (weight == null) ? 1 : ++weight;
			featureMap.put(image, weight);
			fmForHighestRank.put(image, weight);
		}
		positionMap.put(position, featureMap);
		playerPositionMap.put(player, positionMap);
	}

	public void increaseWeight(List<String> gameData) {
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		IntStream.range(0, gameData.size()).forEach(position -> {
			String player = gameData.get(position);
			if ( ! players.contains(player)) {
				continue;
			}
			Map<Integer, Map<String, Integer>> positionMap = playerPositionMap.get(player);
			if (positionMap != null) {
				Map<String, Integer> featureMap = positionMap.get(position);
				featureMap.keySet().stream().filter(featureKey -> featureKey.equalsIgnoreCase(gameDataCsv))
						.forEach(featureKey -> {
							Integer weight = featureMap.get(gameDataCsv);
							if (weight == null) {
								featureMap.put(gameDataCsv, 1);
								fmForHighestRank.put(gameDataCsv, 1);
							} else {
								featureMap.put(gameDataCsv, ++weight);
								fmForHighestRank.put(gameDataCsv, weight);
							}
						});
				positionMap.put(position, featureMap);
				playerPositionMap.put(player, positionMap);
			}
		});
	}

	public void decreaseWeight(List<String> gameData) {
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		IntStream.range(0, gameData.size()).forEach(position -> {
			String player = gameData.get(position);
			if (players.contains(player)) {
				Map<Integer, Map<String, Integer>> positionMap = playerPositionMap.get(player);
				if (positionMap != null) {
					Map<String, Integer> featureMap = positionMap.get(position);
					featureMap.keySet().stream().filter(featureKey -> featureKey.equalsIgnoreCase(gameDataCsv))
							.forEach(featureKey -> {
								Integer weight = featureMap.get(gameDataCsv);
								if (weight == null) {
									featureMap.put(gameDataCsv, 0);
									fmForHighestRank.put(gameDataCsv, 0);
								} else {
									featureMap.put(gameDataCsv, --weight);
									fmForHighestRank.put(gameDataCsv, weight);
								}
							});
					positionMap.put(position, featureMap);
					playerPositionMap.put(player, positionMap);
				}
			}
		});
	}

	public Stream<Entry<String, Integer>> getBestRankedImages(List<String> gameData) {}

	public String getHighestRankedImage() {
		Entry<String, Integer> i = MapUtil.getHighestRankedImage(fmForHighestRank);
		return i.getKey();
	}

	public void printClassification() {}
	
	@Override
	public Classifier clone() throws CloneNotSupportedException {}
}
