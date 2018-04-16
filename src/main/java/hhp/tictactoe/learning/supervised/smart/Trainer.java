package hhp.tictactoe.learning.supervised.smart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import hhp.tictactoe.learning.algo.classification.Classifier;

public class Trainer {
	private static Classifier<String, String> classifier;

	public static void main(String[] args) throws URISyntaxException {
		/*
		 * Create a new classifier instance. The context features are Strings
		 * and the context will be classified with a String according to the
		 * featureset of the context.
		 */
		classifier = new Classifier<String, String>();

		/*
		 * The classifier can learn from classifications that are handed over to
		 * the learn methods. Imagine a tokenized text as follows. The tokens
		 * are the text's features. The category of the text will either be
		 * positive or negative.
		 */
		try (Stream<String> stream = Files
				.lines(Paths.get(TicTacToe.class.getResource("/supervised/tic-tac-toe.data.txt").toURI()))) {
			stream.filter(line -> line.endsWith("positive")).forEach(line -> {
				String[] s = line.split(",");
				for (int i = 0; i < s.length; i++) {
					StringBuilder category = new StringBuilder();
					for (int j = 0; j < i; j++) {
						category.append(s[j]);
					}
					String feature = line;
					classifier.addWeight(category, line);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

        new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(classifier);
	}
}
