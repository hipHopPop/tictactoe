package hhp.tictactoe.learning.supervised;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;;
public class Trainer {

	public static Classifier train(URL gameImagesURL) throws URISyntaxException, IOException {
		/*
		 * Create a new classifier instance. The context features are Strings and the
		 * context will be classified with a String according to the featureset of the
		 * context.
		 */
		return new Classifier(gameImagesURL);
	}
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		URL resource = TicTacToe.class.getResource("/supervised/tic-tac-toe.data.txt");
		Classifier classifier = Trainer.train(resource);
		classifier.printClassification();
	}
}
