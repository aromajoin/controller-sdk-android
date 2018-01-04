package com.aromajoin.controllersdksample;

import java.util.List;

public class Utility {
  public static int[] convertToIntArray(List<Integer> list) {
    int[] arr = new int[list.size()];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = list.get(i);
    }
    return arr;
  }
}
