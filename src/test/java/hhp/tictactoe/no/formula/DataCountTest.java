package hhp.tictactoe.no.formula;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCountTest {
	public static void main(String[] args) throws Exception {
		Map<String, List<Integer>> playerMap = new HashMap<>();
		URL resource = DataCountTest.class.getResource("/supervised/tic-tac-toe.data.txt");
		try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()))) {
			stream.filter(line -> line.endsWith("positive")).forEach(line -> {
				line = line.replaceAll(",positive", "");
				String[] s = line.split(",");
				for (int i = 0; i < s.length; i++) {
					List<Integer> positionList = playerMap.get(s[i]);
					if (positionList == null) {
						positionList = new ArrayList<>(Arrays.asList(new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }));
						positionList.set(i, 1);
					} else {
						Integer playerCount = positionList.get(i);
						positionList.set(i, playerCount == null ? 1 : (playerCount + 1));
					}
					playerMap.put(s[i], positionList);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerMap.forEach((player, positionList) -> {
			System.out.println(
					player + " - " + positionList.stream().map(Object::toString).collect(Collectors.joining(",")));
		});
	}
}