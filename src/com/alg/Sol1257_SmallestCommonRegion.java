package com.alg;

import java.util.*;
/*
You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region x contains another region y then x is bigger than y. Also, by definition, a region x contains itself.

Given two regions: region1 and region2, return the smallest region that contains both of them.

If you are given regions r1, r2, and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.

It is guaranteed the smallest region exists.



Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"

Example 2:

Input: regions = [["Earth", "North America", "South America"],["North America", "United States", "Canada"],["United States", "New York", "Boston"],["Canada", "Ontario", "Quebec"],["South America", "Brazil"]], region1 = "Canada", region2 = "South America"
Output: "Earth"



Constraints:

    2 <= regions.length <= 104
    2 <= regions[i].length <= 20
    1 <= regions[i][j].length, region1.length, region2.length <= 20
    region1 != region2
    regions[i][j], region1, and region2 consist of English letters.
    The input is generated such that there exists a region which contains all the other regions, either directly or indirectly.


 */
public class Sol1257_SmallestCommonRegion {
    // https://leetcode.com/problems/smallest-common-region/editorial/
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> childToParentMap = new HashMap<>();
        for (List<String> region: regions) {
            String parent = region.get(0);
            for (int i= 1; i < region.size(); i++) {
                childToParentMap.put(region.get(i), parent);
            }
        }
        Set<String> parents = new HashSet<>();
        while (region1 != null) {
            parents.add(region1);
            region1 = childToParentMap.get(region1);
        }
        while (region2 != null) {
            if (parents.contains(region2)) {
                return region2;
            }
            region2 = childToParentMap.get(region2);
        }
        return null;
    }
}
