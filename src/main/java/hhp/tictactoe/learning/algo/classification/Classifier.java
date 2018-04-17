package hhp.tictactoe.learning.algo.classification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hhp.util.MapUtil;

public class Classifier {

	private Map<String, Map<String, Integer>> m 	= new HashMap<>();
	private Map<String, Integer> fmForHighestRank 	= new HashMap<>();
	private Map<String, Integer> fmForBestRank 		= new HashMap<>();

	public void increaseWeight(String category, String image) {
		Map<String, Integer> fm = m.get(category);
		if (fm == null) {
			fm = new HashMap<>();
			fm.put(image, 1);
			fmForHighestRank.put(image, 1);
		} else {
			Integer w = fm.get(image);
			if (w == null) {
				fm.put(image, 1);
				fmForHighestRank.put(image, 1);
			} else {
				fm.put(image, ++w);
				fmForHighestRank.put(image, w);
			}
		}
		m.put(category, fm);
	}

	public void increaseWeight(List<String> gameData) {
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		StringBuilder category = new StringBuilder();
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
		String gameDataCsv = gameData.stream().collect(Collectors.joining(","));
		StringBuilder category = new StringBuilder();
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

	public Stream<Entry<String, Integer>> getBestRankedImages(List<String> gameData) {
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

	public String getHighestRankedImage() {
		Entry<String, Integer> i = MapUtil.getHighestRankedImage(fmForHighestRank);
		return i.getKey();
	}
}
