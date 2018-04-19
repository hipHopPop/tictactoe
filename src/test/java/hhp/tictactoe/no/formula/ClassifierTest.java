package hhp.tictactoe.no.formula;

import java.io.ObjectInputStream;

import org.junit.Before;
import org.junit.Test;

import hhp.tictactoe.no.formula.smart.positionmatch.classification.Classifier;

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