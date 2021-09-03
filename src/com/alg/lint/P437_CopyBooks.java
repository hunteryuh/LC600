package com.alg.lint;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
https://www.jiuzhang.com/problem/copy-books/
https://www.lintcode.com/problem/437/
Description
Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.

These books list in a row and each person can claim a continous range of books. For example, one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Return the shortest time that the slowest copier spends. (返回完成复印任务最少需要的分钟数.)
(也就是符合这样划分的最大的子数组之和）

将一个数组分成 k个子数组， 让其中子数组和最大的值 最小。
 */
public class P437_CopyBooks {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public static int copyBooks(int[] pages, int k) {
        // write your code here

        if( pages == null || pages.length == 0) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }

        int start = getMaxPages(pages);   //the maximum item in the array of pages
        int end = getTotalPages(pages);

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (getNumberOfCopiers(pages, mid) > k) {
                // how many copiers does it need so that when the array is divided into multiple
                // subarrays the maximum of sum of all subarrays is as small as mid -- the limit
                // in this case the mid too small (need more people), so the workload needs to be bigger
                start = mid;
            } else {
                end = mid;
            }
        }

        if(getNumberOfCopiers(pages, start) <= k) {
            return start;
        }

        return end;
    }

    private String getChunk(final List<String> s3Keys, int k) {
        return s3Keys.get(s3Keys.size() - k);
    }

    private static int getNumberOfCopiers(int[] pages, int limit) {
        int count = 1;  // at least one copier, so start with 1
        int sum = 0;  // or start with page[0] as limit is always >= pages[0]
        for (int i = 0; i < pages.length; i++) {
            if (sum + pages[i] > limit) {
                count++;
                sum = 0;
            }
            sum += pages[i];
        }
        return count;
    }

    private static int getNumberOfCopiers2(int[] pages, int limit) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < pages.length; i++) {
            if (sum + pages[i] > limit) {
                count++;
                sum = pages[i];
            } else {
                sum += pages[i];
            }
        }
        count++;  // the rest does not exceed the limit but needs one copier
        return count;
    }

    private static int getTotalPages(int[] pages) {
        int sum = 0;
        for (int i = 0; i < pages.length; i++) {
            sum += pages[i];
        }
        return sum;
    }

    private static int getMaxPages(int[] pages) {
        int max = 0;
        for (int i = 0; i < pages.length; i++) {
            max = Math.max(max, pages[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] pages = {3, 2, 4};
        int k = 2;
        System.out.println(copyBooks(pages, k));  // 5
        System.out.println(copyBooks(pages, 3));  // 4
    }
}
