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
	private Map<String, Integer> fmForHighestRank 		= new HashMap<>();
	public static List<String> players = Arrays.asList(new String[]{"x", "o"});

	public void setScrambledWeight(int position, String player, String image) {
		int random = new Random().nextInt(3);
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
			featureMap.put(image, random);
			fmForHighestRank.put(image, random);
		} else {
			Integer weight = featureMap.get(image);
			weight = (weight == null) ? random : ++weight;
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
				return;//continues to next item
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

	public Map<String, Stream<Map.Entry<String, Integer>>> filterForAnyAndAllPositionMatch(List<String> gameData) {
		HashMap<String, Integer> anyPositionMatch = new HashMap<>();
		HashMap<String, Integer> allPositionMatch = new HashMap<>();

		int gameDataSize = gameData.size();

		IntStream.range(0, gameDataSize).forEach(position -> {
			String player = gameData.get(position);
			if (players.contains(player)) {
				Map<Integer, Map<String, Integer>> positionMap = playerPositionMap.get(player);
				if (positionMap != null) {
					positionMap.get(position).forEach((key, value) -> {
						if (anyPositionMatch.containsKey(key)) {
							int compoundedValue = value + anyPositionMatch.get(key);
							anyPositionMatch.put(key, compoundedValue);
						} else {
							anyPositionMatch.put(key, value);
						}
						if (allPositionMatch.containsKey(key)) {
							int compoundedValue = value++;
							allPositionMatch.put(key, compoundedValue);
						} else {
							allPositionMatch.put(key, 1);
						}
					});
				}
			}
		});
		Map<String, Stream<Map.Entry<String, Integer>>> anyAndAllPositionMatch = new HashMap<>();
		anyAndAllPositionMatch.put("any", MapUtil.getSortedMapByDescValue(anyPositionMatch));
		
		int noOfBlanks = gameData.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting())).get("b").intValue();
		allPositionMatch.entrySet().removeIf(entry -> entry.getValue() < gameDataSize - noOfBlanks);
		anyAndAllPositionMatch.put("all", MapUtil.getSortedMapByDescValue(allPositionMatch));
		
		return anyAndAllPositionMatch;
	}
	
	public String getHighestRankedImage() {
		Entry<String, Integer> i = MapUtil.getHighestRankedImage(fmForHighestRank);
		return i.getKey();
	}

	public void printClassification() {
		playerPositionMap.keySet().stream().forEach(player -> {
			Map<Integer, Map<String, Integer>> positionMap = playerPositionMap.get(player);
			positionMap.keySet().stream().forEach(position -> {
				Map<String, Integer> featureMap = positionMap.get(position);
				System.out.println("\n" + player + "(position:" + position + ")");
				featureMap.keySet().stream().forEach(featureKey -> {
					System.out.println(featureKey + "(weight:" + featureMap.get(featureKey) + ")");
				});
			});
		});
	}
	
	@Override
	public Classifier clone() throws CloneNotSupportedException {
		Classifier clone = new Classifier();

		Map<String, Map<Integer, Map<String, Integer>>> playerPositionMapClone 	= new HashMap<>();
		Map<Integer, Map<String, Integer>> posiitonMapClone 					= new HashMap<>();
		Map<String, Integer> fmForHighestRankClone 								= new HashMap<>();
		
		for (String player : playerPositionMap.keySet()) {
			Map<Integer, Map<String, Integer>> posiitonMap = playerPositionMap.get(player);
			for (Integer position : posiitonMap.keySet()) {
				Map<String, Integer> featureMap 		= posiitonMap.get(position);
				Map<String, Integer> featureMapClone 	= new HashMap<>();
				for (String featureKey : featureMap.keySet()) {
					featureMapClone.put(featureKey, new Random().nextInt(3));
				}
				posiitonMapClone.put(position, featureMapClone);
			}
			playerPositionMapClone.put(player, posiitonMapClone);
		}

		for (String featureKey : fmForHighestRank.keySet()) {
			fmForHighestRankClone.put(featureKey, fmForHighestRank.get(featureKey).intValue());
		}

		clone.playerPositionMap = playerPositionMapClone;
		clone.fmForHighestRank 	= fmForHighestRankClone;
		
		return clone;
	}
}
