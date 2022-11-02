package com.Github.IkhideIfidon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static com.Github.IkhideIfidon.DivideConquer.ListNode.printList;

class DivideConquerTest {
    private final Random rand = new Random(0);

    @Test
    public void findMedianSortedArrays() {
    }

    @Test
    public void mergeKLists() {
        DivideConquer.ListNode L1;
        DivideConquer.ListNode L2;
        DivideConquer.ListNode L3;
        DivideConquer.ListNode L4;

        // Build LinkedList
        L1 = utility(new DivideConquer.ListNode(), 9, 56);
        L2 = utility(new DivideConquer.ListNode(), 4, 21);
        L3 = utility(new DivideConquer.ListNode(), 18, 35);
        L4 = utility(new DivideConquer.ListNode(), 7, 19);


        DivideConquer.ListNode[] lists = new DivideConquer.ListNode[]{L1, L2, L3, L4};
        printList(L1);


    }

    private DivideConquer.ListNode utility(DivideConquer.ListNode L, int lowerbound, int upperbound) {
        final int  randLimit = upperbound * 10 - lowerbound + 1;
        final int[] linked = new int[upperbound];
        for (int i = 0; i < upperbound; i++)
            linked[i] = lowerbound + rand.nextInt(randLimit);
        Arrays.sort(linked);
        L.addAll(linked);
        return L;
    }

    @Test
    public void maxSubarray() {
    }
}