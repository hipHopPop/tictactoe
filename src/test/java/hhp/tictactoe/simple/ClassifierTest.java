package hhp.tictactoe.simple;

import java.io.ObjectInputStream;

import org.junit.Before;
import org.junit.Test;

import hhp.tictactoe.simple.smart.positionmatch.Classifier;

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