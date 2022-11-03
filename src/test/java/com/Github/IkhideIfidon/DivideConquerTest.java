package com.Github.IkhideIfidon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;


class DivideConquerTest {
    private final Random rand = new Random(0);

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

    private int[] buildArray(int lowerbound, int upperbound) {
        final int  randLimit = upperbound * 10 - lowerbound + 1;
        final int[] linked = new int[upperbound];
        for (int i = 0; i < upperbound; i++)
            linked[i] = lowerbound + rand.nextInt(randLimit);
        Arrays.sort(linked);
        return linked;
    }



    @Test
    public void maxSubarray() {
    }
}