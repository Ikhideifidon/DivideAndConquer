package com.Github.IkhideIfidon;

public class ListNode {
    int size;
    int val;
    ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    private ListNode head = null;
    private ListNode tail = null;

    // Add to the tail of the list
    public void add(int value) {
        ListNode newest = new ListNode(value);
        if (size == 0)
            head = newest;
       else
           tail.next = newest;
       tail = newest;
       size++;
    }

    public void addAll(Iterable<Integer> listCollection) {
        for (int data: listCollection)
            add(data);
    }
}
