package hhp.tictactoe.learning.supervised;

import java.io.ObjectInputStream;

import org.junit.Before;
import org.junit.Test;

import hhp.tictactoe.learning.supervised.smart.positionmatch.classification.Classifier;

public class ClassifierTest {

	private Classifier<String, String> classifier;

	@Before
	public void setUp() {
		classifier = new ObjectInputStream();
	}

	@Test
	public void testClassification() {
	}

	@Test
	public void testClassificationProbability() {
	}
}