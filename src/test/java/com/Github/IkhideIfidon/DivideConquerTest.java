package com.Github.IkhideIfidon;

import com.ikhideifidon.SinglyLinkedList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class DivideConquerTest {
    private final Random rand = new Random(0);

    @Test
    public void findMedianSortedArrays() {
    }

    @Test
    public void mergeKLists() {
        SinglyLinkedList<Integer> L1;
        SinglyLinkedList<Integer> L2;
        SinglyLinkedList<Integer> L3;
        SinglyLinkedList<Integer> L4;

        // Build LinkedList
        L1 = utility(new SinglyLinkedList<>(), 9, 56);
        L2 = utility(new SinglyLinkedList<>(), 4, 21);
        L3 = utility(new SinglyLinkedList<>(), 18, 35);
        L4 = utility(new SinglyLinkedList<>(), 7, 19);

        //noinspection unchecked
        SinglyLinkedList<Integer>[] lists = (SinglyLinkedList<Integer>[]) new SinglyLinkedList[]{L1, L2, L3, L4};
        System.out.println(Arrays.toString(lists));


    }

    private SinglyLinkedList<Integer> utility(SinglyLinkedList<Integer> L, int lowerbound, int upperbound) {
        final int  randLimit = upperbound * 10 - lowerbound + 1;
        final Integer[] linked = new Integer[upperbound];
        for (int i = 0; i < upperbound; i++)
            linked[i] = lowerbound + rand.nextInt(randLimit);
        Arrays.sort(linked);
        L.addAll(List.of(linked));
        return L;
    }

    @Test
    public void maxSubarray() {
    }
}