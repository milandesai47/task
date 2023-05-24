package org.example;

import java.util.Random;

public class RandomGen {
  private final int[] numbers;
  private final float[] probabilities;
  private final Random random;

  public RandomGen(int[] numbers, float[] probabilities) {
    // validating constraints
    if (numbers.length == 0 || probabilities.length == 0 || numbers.length != probabilities.length) {
      throw new IllegalArgumentException("Numbers and probabilities must be equal and non-null");
    }

    // probabilities should sum up to 1.
    float total = 0F;
    for (float probability : probabilities) {
      total = total + probability;
    }
    if(total != 1F) {
      throw new IllegalArgumentException("Probabilities should accumulate to 1");
    }

    this.numbers = numbers;
    this.probabilities = probabilities;
    this.random = new Random();
  }

  public int nextNum() {
    float randomValue = random.nextFloat();
    // start initial probability as 0
    float totalProbability = 0.0F;

    // loop through all the probabilities
    for (int i = 0; i < probabilities.length; i++) {

      // accumulate the probabilities
      totalProbability = totalProbability + probabilities[i];

      // until our random value is higher than probability
      if (totalProbability >= randomValue) {
        // at this point return the seeded number at position of the probability.
        return numbers[i];
      }
    }
    throw new IllegalStateException("Unable to generate next number");
  }
}
