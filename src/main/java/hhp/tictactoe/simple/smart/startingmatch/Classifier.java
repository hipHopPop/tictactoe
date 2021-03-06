package hhp.tictactoe.simple.smart.startingmatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hhp.util.MapUtil;

public class Classifier {

	private Map<String, Map<String, Integer>> m 	= new HashMap<>();
	private Map<String, Integer> fmForHighestRank 	= new HashMap<>();
	private Map<String, Integer> fmForBestRank 		= new HashMap<>();

	public void setScrambledWeight(String category, String gameData) {
		int random = new Random().nextInt(2);
		Map<String, Integer> fm = m.get(category);
		if (fm == null) {
			fm = new HashMap<>();
			fm.put(gameData, random);
			fmForHighestRank.put(gameData, random);
		} else {
			Integer w = fm.get(gameData);
			if (w == null) {
				fm.put(gameData, random);
				fmForHighestRank.put(gameData, random);
			} else {
				fm.put(gameData, ++w);
				fmForHighestRank.put(gameData, w);
			}
		}
		m.put(category, fm);
	}

	public void increaseWeight(List<String> gameData) {
		String gameDataCsv 		= gameData.stream().collect(Collectors.joining(","));
		StringBuilder category 	= new StringBuilder();
		gameData.stream().forEach(play -> {
			category.append(play);
			Map<String, Integer> fm = m.get(category.toString());
			if (fm != null) {
				fm.keySet().stream()
						.filter(featureKey -> featureKey.startsWith(gameDataCsv) && fm.get(featureKey) != null)
						.forEach(featureKey -> {
							Integer w = fm.get(featureKey);
							fm.put(featureKey, ++w);
							fmForHighestRank.put(featureKey, w);
						});
				m.put(category.toString(), fm);
			}
		});
	}

	public void decreaseWeight(List<String> gameData) {
		String gameDataCsv 		= gameData.stream().collect(Collectors.joining(","));
		StringBuilder category 	= new StringBuilder();
		gameData.stream().forEach(play -> {
			category.append(play);
			Map<String, Integer> fm = m.get(category.toString());
			if (fm != null) {
				fm.keySet().stream()
						.filter(featureKey -> featureKey.startsWith(gameDataCsv) && fm.get(featureKey) != null)
						.forEach(featureKey -> {
							Integer w = fm.get(featureKey);
							fm.put(featureKey, --w);
							fmForHighestRank.put(featureKey, w);
						});
				m.put(category.toString(), fm);
			}
		});
	}

	public Stream<Entry<String, Integer>> getBestRankedGameData(List<String> gameData) {
		fmForBestRank.clear();
		StringBuilder category = new StringBuilder();
		gameData.stream().forEach(play -> {
			category.append(play);
			Map<String, Integer> fm = m.get(category.toString());
			if (fm != null) {
				fmForBestRank.putAll(fm);
			}
		});
		return MapUtil.getSortedMapByDescValue(fmForBestRank);
	}

	public String getHighestRankedGameData() {
		Entry<String, Integer> i = MapUtil.getHighestRankedGameData(fmForHighestRank);
		return i.getKey();
	}

	public void printClassification() {
		m.keySet().stream().forEach(category -> {
			System.out.println("\n"+category);
			Map<String, Integer> fm = m.get(category);
			fm.keySet().stream()
			.forEach(featureKey -> {
				System.out.println(featureKey + "(weight:" + fm.get(featureKey) + ")");
			});
		});
	}
	
	@Override
	public Classifier clone() throws CloneNotSupportedException {
		Classifier clone = new Classifier();

		Map<String, Map<String, Integer>> mClone 	= new HashMap<>();
		Map<String, Integer> fmForHighestRankClone 	= new HashMap<>();
		Map<String, Integer> fmForBestRankClone 	= new HashMap<>();
		
		for (String category : m.keySet()) {
			Map<String, Integer> fm 		= m.get(category);
			Map<String, Integer> fmClone 	= new HashMap<>();
			for (String featureKey : fm.keySet()) {
				fmClone.put(featureKey, fm.get(featureKey).intValue());
			}
			mClone.put(category, fmClone);
		}

		for (String featureKey : fmForHighestRank.keySet()) {
			fmForHighestRankClone.put(featureKey, fmForHighestRank.get(featureKey).intValue());
		}

		for (String featureKey : fmForBestRank.keySet()) {
			fmForBestRankClone.put(featureKey, fmForBestRank.get(featureKey).intValue());
		}

		clone.m 				= mClone;
		clone.fmForHighestRank 	= fmForHighestRankClone;
		clone.fmForBestRank 	= fmForBestRankClone;
		
		return clone;
	}
}
