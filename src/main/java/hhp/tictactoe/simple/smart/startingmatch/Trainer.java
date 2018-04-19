package hhp.tictactoe.simple.smart.startingmatch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import hhp.tictactoe.simple.smart.startingmatch.classification.Classifier;

public class Trainer {

	public static Classifier train(URL gameImagesURL) throws URISyntaxException {
		/*
		 * Create a new classifier instance. The context features are Strings and the
		 * context will be classified with a String according to the featureset of the
		 * context.
		 */
		Classifier classifier = new Classifier();

		/*
		 * The classifier can learn from classifications that are handed over to the
		 * learn methods. Imagine a tokenized text as follows. The tokens are the text's
		 * features. The category of the text will either be positive or negative.
		 */
		try (Stream<String> stream = Files.lines(Paths.get(gameImagesURL.toURI()))) {
			stream.filter(line -> line.endsWith("positive")).forEach(line -> {
				line = line.replaceAll(",positive", "");
				String[] s = line.split(",");
				StringBuilder category = new StringBuilder();
				for (int i = 0; i < s.length; i++) {
					category.append(s[i]);
					classifier.setScrambledWeight(category.toString(), line);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classifier;
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URL resource = TicTacToe.class.getResource("/supervised/tic-tac-toe.data.txt");
		Classifier classifier = Trainer.train(resource);
		classifier.printClassification();
	}
}
