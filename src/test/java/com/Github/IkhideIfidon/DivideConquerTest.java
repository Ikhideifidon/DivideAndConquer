package com.Github.IkhideIfidon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;


class DivideConquerTest {
    private final Random rand = new Random();

    // Utility method
    private int[] buildArray(int lowerbound, int upperbound) {
        final int  randLimit = upperbound * 10 - lowerbound + 1;
        final int[] linked = new int[upperbound];
        rand.setSeed(0);
        for (int i = 0; i < upperbound; i++)
            linked[i] = lowerbound + rand.nextInt(randLimit);

        // Arrays.sort(linked) for mergedKLists method;
        return linked;
    }

    @Test
    public void findMedianSortedArrays() {
    }

    @Test
    public void mergeKLists() {
        int[] L1;
        int[] L2;
        int[] L3;
        int[] L4;

        // Build Array
        L1 = buildArray(9, 55);
        L2 = buildArray(4, 21);
        L3 = buildArray(18, 35);
        L4 = buildArray(7, 19);

        // Build LinkedList
        ListNode list1 = ListNode.addAll(L1);
        ListNode list2 = ListNode.addAll(L2);
        ListNode list3 = ListNode.addAll(L3);
        ListNode list4 = ListNode.addAll(L4);

        ListNode[] arrayLinked = {list1, list2, list3, list4};
        ListNode result = DivideConquer.mergeKLists(arrayLinked);
        ListNode.printList(result);
    }

    @Test
    public void partition() {
        int[] nums = {6, 4, 1, 7, 4, 7, 3, 6, 5};
        System.out.println( DivideConquer.partition(nums, 0, nums.length - 1));
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void HoarePartition() {
        int[] nums = {6, 4, 1, 7, 4, 7, 3, 6, 5};
        System.out.println( DivideConquer.hoarePartition(nums, 0, nums.length - 1));
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void quickSort() {
        int[] nums = buildArray(45, 100_000_000);
        long startTime = System.currentTimeMillis();
        DivideConquer.quickSort(nums);
        long endTime = System.currentTimeMillis();
        System.out.println("QuickSort Based on Randomized  Partition\t");
        System.out.println("Time elapsed is: " + (endTime - startTime) + " milliseconds");

    }

    @Test
    public void randomizedQuickSelect() {
        int[] nums = buildArray(45, 1000_000_00);
        int k = 60;                        // return the smallest kth element in the array.
        int result = nums[k-1];
        long startTime = System.currentTimeMillis();
        Assertions.assertEquals(result, DivideConquer.quickSelect(nums, k));
        long endTime = System.currentTimeMillis();
        System.out.println("Randomized Partition\t");
        System.out.println("Time elapsed is: " + (endTime - startTime) + " milliseconds");
        System.out.format("The %dth smallest element in the array is " + result, k);

    }

    @Test
    public void hoareQuickSelect() {
        int[] nums = buildArray(45, 1000_000_00);
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);                            // MergeSort
        int k = 60;                                         // return the smallest kth element in the array.
        int result = sortedNums[k-1];
        long startTime = System.currentTimeMillis();
        Assertions.assertEquals(result, DivideConquer.quickSelect(nums, k));
        long endTime = System.currentTimeMillis();
        System.out.println("Hoare Partition\t");
        System.out.println("Time elapsed is: " + (endTime - startTime) + " milliseconds");
        System.out.format("The %dth smallest element in the array is " + result, k);
    }

    @Test
    public void median() {
        int[] A = {12, 3, 5, 7, 4, 19, 26, 2, 9, 4};
        System.out.println(DivideConquer.medianUnsortedArray(A));
    }

    @Test
    public void mergeSort() {
        int[] nums = buildArray(45, 1000_000_00);
        long startTime = System.currentTimeMillis();
        DivideConquer.mergeSort(nums);
        long endTime = System.currentTimeMillis();
        System.out.println("MergeSort\t");
        System.out.println("Time elapsed is: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    public void insertionSort() {
        int[] nums = buildArray(4, 100);
        DivideConquer.insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    @Test
    public void maxSlidingWindowPriorityQueue() {
        int[] A = buildArray(12, 100_000_000);
        int k = 546;
        long startTime = System.currentTimeMillis();
        DivideConquer.maxSlidingWindow(A, k);
        long endTime = System.currentTimeMillis();
        System.out.println("PriorityQueue Maximum Sliding Window\t");
        System.out.println("Time taken is " + (endTime - startTime) + " milliseconds");

    }

    @Test
    public void maxSlidingWindowDeque() {
        int[] A = buildArray(12, 100_000_000);
        int k = 546;
        long startTime = System.currentTimeMillis();
        DivideConquer.maxSlidingWindowDeque(A, k);
        long endTime = System.currentTimeMillis();
        System.out.println("Deque Maximum Sliding Window\t");
        System.out.println("Time taken is " + (endTime - startTime) + " milliseconds");
    }

    @Test
    public void maxSlidingWindowPriorityQueueDequeCheck() {
        int[] A = buildArray(12, 100_000_000);
        int k = 546;
        int i = rand.nextInt(A.length - k + 1);
        int[] expectedDeque = DivideConquer.maxSlidingWindowDeque(A, k);
        int[] expectedPriorityQueue = DivideConquer.maxSlidingWindow(A, k);
        Assertions.assertEquals(expectedPriorityQueue[i], expectedDeque[i]);
    }

}