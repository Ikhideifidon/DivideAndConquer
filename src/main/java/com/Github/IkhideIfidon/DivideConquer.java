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
    // Time = O(nlogK)
    // Space = O(K)

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public void add(ListNode head, int data) {
            @SuppressWarnings("WriteOnlyObject")
            ListNode newNode = new ListNode(data);
            newNode.next = head;
        }

        public void addAll(int[] keys) {
            ListNode head = new ListNode();
            for (int key : keys)
                head.add(head, key);
        }

        public static void printList(ListNode head) {
            ListNode pointer = head;
            while (pointer != null)
            {
                System.out.print(pointer.val + " —> ");
                pointer = pointer.next;
            }
            System.out.println("null");
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
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
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        return divide(lists, 0, lists.length - 1);
    }

    private static ListNode divide(ListNode[] lists, int low, int high) {
        int mid = low + (high - low) / 2;
        if (low < high) {
            ListNode left = divide(lists, low, mid);
            ListNode right = divide(lists, mid + 1, high);
            return merge(left, right);
        }
        return lists[low];
    }

    private static ListNode merge(ListNode left, ListNode right) {
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

    // Medium: Maximum Sub-array.
    // Time = O(nlogn)
    // Space = O(1)
    public static int maxSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        return helper(nums, left, right);
    }

    private static int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];                   // if nums contains only one element

        int mid = left + (right - left) / 2;
        int leftMax = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = mid; i >= left; i--) {
            currentSum += nums[i];
            if (currentSum > leftMax)
                leftMax = currentSum;
        }

        int rightMax = Integer.MIN_VALUE;
        currentSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currentSum += nums[i];
            if (currentSum > rightMax)
                rightMax = currentSum;
        }

        int maxLeftRight = Math.max(helper(nums, left, mid), helper(nums, mid + 1, right));

        return Math.max(maxLeftRight, leftMax + rightMax);
    }

    // Medium: Maximum Sub-array.
    // Time = O(n)
    // Space = O(1)
    public static int maxSubarrayKadane(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            currentSum = Math.max(currentSum + num, num);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;

    }


}
