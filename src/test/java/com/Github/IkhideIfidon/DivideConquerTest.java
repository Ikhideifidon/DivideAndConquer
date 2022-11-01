package com.Github.IkhideIfidon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class DivideConquerTest {

    @Test
    public void findMedianSortedArrays() {
    }

    @Test
    public void mergeKLists() {
        ListNode L1 = new ListNode();
        ListNode L2 = new ListNode();
        ListNode L3 = new ListNode();
        ListNode L4 = new ListNode();

        Random rand = new Random(0);
        int upperbound = 100;
        int lowerbound = 4;
        final int randLimit = upperbound * 10 - lowerbound + 1;
        final Integer[] linked1 = new Integer[upperbound];
        for (int i = 0; i < upperbound; i++) {
            linked1[i] = lowerbound + rand.nextInt(randLimit);
        }
        Arrays.sort(linked1);
        L1.addAll(List.of(linked1));
        System.out.println(Arrays.toString(linked1));
    }
}