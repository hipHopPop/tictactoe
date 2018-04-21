package hhp.util;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class MapUtil {
	public static Entry<String, Integer> getHighestRankedGameData(Map<String, Integer> map) {
		Stream<Map.Entry<String, Integer>> sorted = getSortedMapByDescValue(map);
		Entry<String, Integer> item = sorted.findFirst().orElse(null);
		return item; 
	}

	public static Stream<Map.Entry<String, Integer>> getSortedMapByDescValue(Map<String, Integer> map) {
		Stream<Map.Entry<String, Integer>> sorted = map.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
		return sorted;
	}
}
