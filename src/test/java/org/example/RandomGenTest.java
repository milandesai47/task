package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGenTest {

  @Test
  void nextNumWithFactorOf1DecimalPlaces() {
    // Define the numbers and probabilities
    int[] numbers = {21, 55};
    float[] probabilities = {0.2F, 0.8F};

    // Create the RandomGenerator instance
    RandomGen generator = new RandomGen(numbers, probabilities);

    // Define the expected probabilities within a tolerance
    float tolerance = 0.1F;

    // Run multiple iterations to test the generated numbers
    int numIterations = 1000;
    int[] counter = new int[numbers.length];
    for (int i = 0; i < numIterations; i++) {
      int num = generator.nextNum();
      counter[getIndex(numbers, num)]++;
    }

    // Calculate the actual probabilities
    float[] actualProbabilities = new float[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      actualProbabilities[i] = (float) counter[i] / numIterations;
    }

    // Assert that the actual probabilities match the expected probabilities within the tolerance
    for (int i = 0; i < numbers.length; i++) {
      Assertions.assertEquals(probabilities[i], actualProbabilities[i], tolerance);
    }
  }

  @Test
  void nextNumWithFactorOf2DecimalPlaces() {
    // Define the numbers and probabilities
    int[] numbers = {-1, 0, 1, 2, 3};
    float[] probabilities = {0.01F, 0.3F, 0.58F, 0.1F, 0.01F};

    // Create the RandomGenerator instance
    RandomGen generator = new RandomGen(numbers, probabilities);

    // Define the expected probabilities within a tolerance
    float tolerance = 0.01F;

    // Run multiple iterations to test the generated numbers
    int numIterations = 1000;
    int[] counter = new int[numbers.length];
    for (int i = 0; i < numIterations; i++) {
      int num = generator.nextNum();
      counter[getIndex(numbers, num)]++;
    }

    // Calculate the actual probabilities
    float[] actualProbabilities = new float[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      actualProbabilities[i] = (float) counter[i] / numIterations;
    }

    // Assert that the actual probabilities match the expected probabilities within the tolerance
    for (int i = 0; i < numbers.length; i++) {
      Assertions.assertEquals(probabilities[i], actualProbabilities[i], tolerance);
    }
  }

  @Test
  void shouldThrowExceptionWhenNonNullConstraintIsNotMet() {
    // invalid numbers and probabilities
    int[] numbers = {};
    float[] probabilities = {};

    // Expect exception
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      RandomGen randomGen = new RandomGen(numbers, probabilities);
    });

    assertTrue(exception.getMessage().contains("Numbers and probabilities must be of equal size and non-null"));
  }

  @Test
  void shouldThrowExceptionWhenProbabilitiesAccumulateToMoreThan1() {
    // Define the numbers and probabilities
    int[] numbers = {1, 2, 3, 4, 5};
    // invalid probabilities set > 1
    float[] probabilities = {0.06F, 0.3F, 0.58F, 0.1F, 0.01F};

    // Expect exception
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      RandomGen randomGen = new RandomGen(numbers, probabilities);
    });

    assertTrue(exception.getMessage().contains("Probabilities should accumulate to 1"));
  }

  private int getIndex(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == target) {
        return i;
      }
    }
    return -1;
  }

}
