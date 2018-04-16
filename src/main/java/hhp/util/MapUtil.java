package hhp.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class MapUtil {
	public static String getBestRankedImage(HashMap<String, Integer> map) {
		Stream<Map.Entry<String, Integer>> sorted = map.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
		Entry<String, Integer> item = sorted.findFirst().orElse(null);
		return (item != null) ? item.getKey() : null; 
	}
}