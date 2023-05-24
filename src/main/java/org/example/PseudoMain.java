package org.example;

public class PseudoMain {
  public static void main(String[] args) {
    int[] numbers = {-1, 0, 1, 2, 3};
    float[] probabilities = {0.01F, 0.3F, 0.58F, 0.1F, 0.01F};

    RandomGen generator = new RandomGen(numbers, probabilities);

    int numCalls = 100;

    int[] counter = new int[numbers.length];

    int num;
    for (int i = 0; i < numCalls; i++) {
      num = generator.nextNum();
      counter[getIndex(numbers, num)]++;
    }

    for (int i = 0; i < numbers.length; i++) {
      System.out.println(numbers[i] + ": " + counter[i] + " times");
    }
  }

  private static int getIndex(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == target) {
        return i;
      }
    }
    return -1;
  }
}