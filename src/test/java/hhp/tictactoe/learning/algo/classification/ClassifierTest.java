package hhp.tictactoe.learning.algo.classification;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

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