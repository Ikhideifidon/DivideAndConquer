package com.Github.IkhideIfidon;

import java.util.*;

public class DivideConquer {

    private static final Random rand = new Random(0);

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

    public static int partition(int[] A, int left, int right) {
        if (A == null || A.length == 0) return -1;

        int i = left - 1;
        int pivot = A[right];

        for (int j = left; j < right; j++) {
            if (A[j] <= pivot) {
                int temp = A[j];
                A[j] = A[i + 1];
                A[i + 1] = temp;
                i++;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[right];
        A[right] =temp;

        // pivot index
        return i + 1;
    }

    public static int hoarePartition(int[] A, int lower, int upper) {
        if (A == null) throw new NullPointerException("Array cannot be null.");
        else if (lower > upper) return -1;

        int middle = lower + (upper - lower) / 2;
        int pivot = A[middle];
        // Swap middle element with left element.
        A[middle] = A[lower];
        A[lower] = pivot;

        // Advance the left pointer by 1
        int left = lower + 1;
        int right = upper;

        boolean finished = false;
        while (!finished) {

            while (left <= right && A[left] <= pivot)
                left++;

            while (A[right] > pivot)
                right--;

            if (left < right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
            finished = left > right;
        }

        int temp = A[lower];
        A[lower] = A[right];
        A[right] = temp;
        return right;
    }

    public static int randomizedPartition(int[] A, int left, int right) {
        if (A == null || A.length == 0) return -1;
        int pivotIndex = rand.nextInt(left, right + 1);

        // Swap element at the pivotIndex with the element at the right.
        // This is to ensure that the pivot element is always at the right.
        int temp = A[pivotIndex];
        A[pivotIndex] = A[right];
        A[right] = temp;

        return partition(A, left, right);

    }

    private static void quickSortHelper(int[] A, int left, int right) {
        if (left >= right) return;
        int q = hoarePartition(A, left, right);
        quickSortHelper(A, left, q - 1);                // Partition all the elements before the pivot index
        quickSortHelper(A, q + 1, right);                // Partition all the elements after the pivot index
    }

    public static void quickSort(int[] A) {
        if (A == null || A.length <= 1) return;
        quickSortHelper(A, 0, A.length - 1);
    }

    private static int quickSelectHelper(int[] A, int k, int left, int right) {
        if (left == right) return A[left];
        int q = hoarePartition(A, left, right);              // Partition index.
        if (q == k) return A[q];                              // Array is 0-indexed
        else if (q < k) return quickSelectHelper(A, k,q + 1, right);
        return quickSelectHelper(A, k, left, q - 1);
    }

    public static int quickSelect(int[] A, int k) {
        if (A == null) throw new NullPointerException("Array cannot be null.");
        else if (A.length == 0) throw new ArrayIndexOutOfBoundsException("Array cannot be empty.");
        else if (k <= 0 || k > A.length) throw new IllegalArgumentException("k cannot be less than, equal to or greater than array length.");
        return quickSelectHelper(A, k, 0, A.length - 1);
    }

    // Median of an unsorted array
    public static int medianUnsortedArray(int[] A) {
        if (A == null || A.length == 0) return Integer.MIN_VALUE;
        return medianHelper(A, 0, A.length - 1);
    }

    private static int medianHelper(int[] A, int left, int right) {
        if (left == right) return A[left];
        int n = A.length / 2;
        int q = partition(A, left, right);
        if (q == n) return A[q];
        else if (q < n) return medianHelper(A, q + 1, right);
        return medianHelper(A, left, q - 1);
    }

    public static void mergeSort(int[] A) {
        if (A.length < 2) return;
        int mid = A.length / 2;

        int[] left = Arrays.copyOfRange(A, 0, mid);
        mergeSort(left);
        int[] right = Arrays.copyOfRange(A, mid, A.length);
        mergeSort(right);

        // Merge both arrays
        merge(A, left, right);
    }

    private static void merge(int[] A, int[] left, int[] right) {
        int i = 0;                               // Index through the left array.
        int j = 0;                               // Index through the right array.
        int k = 0;                               // Index through the given array, A.

        while (i < left.length && j < right.length) {
            if (left[i] < right[j])
                A[k++] = left[i++];
            else
                A[k++] = right[j++];
        }

        while (i < left.length)
            A[k++] = left[i++];

        while (j < right.length)
            A[k++] = right[j++];
    }

    public static void insertionSort(int[] A) {
        if (A == null || A.length <= 1) return;
        for (int i = 1; i < A.length; i++) {
            int temp = A[i];

            int j = i - 1;
            while (j >= 0 && A[j] > temp)
                A[j + 1] = A[j--];                  // Right shift

            A[j + 1] = temp;
        }
    }

    // Time = O(nlogk)
    // Space = O(n)
    public static int[] maxSlidingWindow(int[] A, int k) {
        if (A == null) throw new NullPointerException("Array cannot be null.");
        if (k <= 0 || k > A.length)
            throw new IllegalArgumentException("k must be in the range(1, Array length) included.");

        // PriorityQueue of int[]
        Comparator<int[]> comp = (o1, o2) -> Integer.compare(o2[1], o1[1]);

        PriorityQueue<int[]> queue = new PriorityQueue<>(comp);
        int[] result = new int[A.length - k + 1];
        for (int i = 0; i < A.length; i++) {
            // pop the root element if its index is out of bounds.
            while (!queue.isEmpty() && (i - k) > queue.peek()[0])
                queue.poll();

            queue.offer(new int[]{i, A[i]});
            if (i >= k - 1) {                          // Window size reached
                assert queue.peek() != null;
                result[i - k + 1] = queue.peek()[1];
            }
        }
        return result;
    }

    public static int[] maxSlidingWindowDeque(int[] A, int k) {
        if (A == null) throw new NullPointerException("Array cannot be null.");
        if (k <= 0 || k > A.length)
            throw new IllegalArgumentException("k must be in the range(1, Array length) included.");

        // A = {1, 3, -1, -3, 5, 3, 6, 7}

        // Maintain a monotonically decreasing queue that allows removal at the left end and addition at the right end
        Deque<Integer> queue = new LinkedList<>();
        int[] result = new int[A.length - k + 1];

        for (int i = 0; i < A.length; i++) {

            // pop the first element if its index is out of bounds.
            if (!queue.isEmpty() && (i - k) > queue.peekFirst())
                queue.pollFirst();

            // This maintains monotonically decreasing queue.
            while (!queue.isEmpty() && A[queue.peekLast()] < A[i])
                queue.pollLast();

            queue.offerLast(i);

            if (i >= k - 1) {                          // Window size reached
                assert queue.peekFirst() != null;
                result[i - k + 1] = A[queue.peekFirst()];
            }
        }
        return result;
    }

    public static int majorityElement(int[] A) {
        if (A == null) throw new NullPointerException("Array cannot be null.");
        if (A.length == 0) throw new ArrayIndexOutOfBoundsException("Array cannot be empty.");
        if (A.length == 1) return A[0];
        int mid = A.length / 2;
        int maxCount = 0;
        int value = 0;
        // Set is also a suitable data structure to use.
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : A) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            int temp = frequency.get(num);
            if (temp > maxCount) {
                maxCount = temp;
                value = num;
            }
        }

        return maxCount > mid ? value : Integer.MIN_VALUE;
    }

    // Using Boyer-Moore Voting Algorithm
    // Time = O(n)
    // Space = O(1)
    public static int boyerMooreVotingAlgorithm(int[] A) {
        int n = A.length;
        int number = 0;
        int count = 0;
        for (int num : A) {
            if (count == 0)
                number = num;

            count += num == number ? 1 : -1;
        }

        count = 0;
        for (int num : A) {
            if (num == number)
                count++;

            if (count > n / 2)
                return number;
        }
        return Integer.MIN_VALUE;
    }
}
