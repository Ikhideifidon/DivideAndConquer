package com.Github.IkhideIfidon;

public class ListNode {
    int val;
    ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    private static ListNode push(ListNode head, int data) {
        // allocate a new node in a heap and set its data
        ListNode newNode = new ListNode();
        newNode.val = data;
        newNode.next = head;
        return newNode;
    }

    // Function for linked list implementation from the given set of keys
    public static ListNode addAll(int[] keys) {
        ListNode head = null;
        // start from the end of the array
        for (int i = keys.length - 1; i >= 0; i--)
            head = push(head, keys[i]);
        return head;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " —> ");
            current = current.next;
        }
        System.out.println("null");
    }
}