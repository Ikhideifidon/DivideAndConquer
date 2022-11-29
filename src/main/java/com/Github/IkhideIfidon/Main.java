package com.Github.IkhideIfidon;

public class Main {
    public static void main(String[] args) {
        int[] nums = {4, 4, 2, 4, 2, 2, 4, 2, 2, 2};
        System.out.println(DivideConquer.majorityElement(nums));
        ListNode node = ListNode.addAll(nums);
        ListNode.append(node, 10, 11, 12);
        ListNode.printList(node);
    }
}