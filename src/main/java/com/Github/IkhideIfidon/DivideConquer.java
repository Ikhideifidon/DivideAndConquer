package com.Github.IkhideIfidon;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DivideConquer {

    // Hard: Median of Two Sorted Arrays.
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            if (nums1 == null) {
                if (nums2.length % 2 != 0)
                    return nums2[nums2.length / 2];
                else {
                    int mid = nums2.length / 2;
                    return (double) (nums2[mid - 1] + nums2[mid]) / 2;
                }

            } else
                return findMedianSortedArrays(null, nums1);
        }

        // Make sure nums1 is the shorter array
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length;
        int n = nums2.length;
        int k = (m + n + 1) / 2;                           // Half of the combined length. 1 is added to even out.
        int left = 0;
        int right = m;

        while (left <= right) {
            int i = left + (right - left) / 2 ;            // i is an index through nums1
            int j = k - i;                                 // j is an index through nums2

            int ALeft = (i - 1) < 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int ARight = i == nums1.length ? Integer.MAX_VALUE : nums1[i];

            int BLeft = (j - 1)  < 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int BRight = j == nums2.length ? Integer.MAX_VALUE : nums2[j];

            if (ALeft <= BRight && BLeft <= ARight) {
                if ((m + n) % 2 == 0)
                    return (double) (Math.max(ALeft, BLeft) + Math.min(ARight, BRight)) / 2;
                else
                    return Math.max(ALeft, BLeft);
            } else if (ALeft > BRight)                  // 'i' has moved too far right
                right = i - 1;
            else                                        // 'i' has moved too far left
                left = i + 1;
        }
        throw new IllegalArgumentException("Both arrays must be sorted");
    }

    // Hard: Merge k Sorted Lists
    // Definition for singly-linked list.


    // Using Priority Queue
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // Natural order comparator
        Comparator<ListNode> comp = (L1, L2) -> {
            if (L1.val > L2.val)
                return 1;
            else if (L1.val < L2.val)
                return -1;
            return 0;
        };
        PriorityQueue<ListNode> queue = new PriorityQueue<>(comp);

        for (ListNode list : lists) {
            // Assuming lists contains some null list
            if (list != null)
                queue.offer(list);
        }

        ListNode dummy = new ListNode(0, null);
        ListNode tail = dummy;

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)                // If temp is NOT empty.
                queue.offer(tail.next);
        }
        return dummy.next;
    }

    // Using Divide and Conquer
    // Time = O(nlogn)
    // Space = O(n)
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int low, int high) {
        int mid = low + (high - low) / 2;
        if (low < high) {
            ListNode left = divide(lists, low, mid);
            ListNode right = divide(lists, mid + 1, high);
            return merge(left, right);
        }
        return lists[low];
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode result = new ListNode(0);
        ListNode tail = result;

        while (left != null || right != null) {
            if (left != null && right != null) {
                if (left.val < right.val) {
                    tail.next = left;
                    left = left.next;
                }  else {
                    tail.next = right;
                    right = right.next;
                }
            }  else if (left == null) {
                tail.next = right.next;
                right = right.next;
            } else {
                tail.next = left;
                left = left.next;
            }
            tail = tail.next;
        }
        return result.next;
    }




}
