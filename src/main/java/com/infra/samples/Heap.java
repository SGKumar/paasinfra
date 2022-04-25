package com.atlassian.old.samples;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap {

  private static Integer findKthLargest(int[] nums, int k, boolean reverse) {
    Comparator<Integer> comparator = reverse?Collections.reverseOrder():null;
    PriorityQueue<Integer> heap = new PriorityQueue<>(comparator);
    for (int n : nums) {
      heap.add(n);
      System.out.println("heap b4 remove: " + heap.toString());
      if (heap.size() > k)
        heap.remove();
      System.out.println("heap a4 remove: " + heap.toString());
    }
    if(heap.isEmpty()) {
      System.out.println("Empty heap");
    }
    return heap.peek();
  }

  public static void main(String[] args) {
    int[] nums = new int[] {12, 2, 41, 5, 7, 18, 36, 4};
    System.out.printf("k %d largest elem is: %d\n", 3, findKthLargest(nums, 3, false));

    int[] nums1 = new int[] {7, 3, 1, 9, 6, 4};
    System.out.printf("k %d largest elem is: %d", 2, findKthLargest(nums1, 2, true));
  }
}
