package com.alg;
/*
Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 */
public class Sol307_RangeSumQueryMutable {

    public class NumArray {

        // https://leetcode.com/problems/range-sum-query-mutable/discuss/75724/17-ms-Java-solution-with-segment-tree
        class SegmentTreeNode {
            int start;
            int end;
            int sum;
            SegmentTreeNode left;
            SegmentTreeNode right;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = null;
                this.right = null;
                this.sum = 0;
            }
        }

        SegmentTreeNode root = null;

        public NumArray(int[] nums) {
            root = buildSegmenttree(nums, 0, nums.length - 1);
        }

        private SegmentTreeNode buildSegmenttree(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if (start == end) {
                node.sum = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                node.left = buildSegmenttree(nums, start, mid);
                node.right = buildSegmenttree(nums, mid + 1, end);
                node.sum = node.left.sum + node.right.sum;
            }
            return node;
        }

        public void update(int index, int val) {
            update(root, index, val);
        }

        private void update(SegmentTreeNode root, int index, int val) {
            if (root.start == index && root.end == index) {
                root.sum = val;
                return;
            }
            int mid = root.start + (root.end - root.start) / 2;
            if (index <= mid) {
                update(root.left, index, val);
            } else {
                update(root.right, index, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }

        public int sumRange(int left, int right) {
            return sumRange(root, left, right);
        }

        private int sumRange(SegmentTreeNode root, int left, int right) {
            if (root.start == left && root.end == right) {
                return root.sum;
            }
            int mid = root.start + (root.end - root.start) / 2;
            if (right <= mid) {
                return sumRange(root.left, left, right);
            } else if (left > mid) {
                return sumRange(root.right, left, right);
            } else {
                return sumRange(root.left, left, mid) + sumRange(root.right, mid + 1, right);
            }
        }
    }
}

    // array appraoch https://leetcode.com/problems/range-sum-query-mutable/discuss/75763/7ms-Java-solution-using-bottom-up-segment-tree

