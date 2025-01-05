package com.alg.other;

import java.util.*;
import java.util.ArrayList;

/*
First question :
Validate order list : P1, P2, D2,D1

Pick are ascending order and delivery can be any order
Solved it completely and correctly

Second Question :
Given number of order return all teh possible valid orders
n =2 orders = [ P1,P2, D1 , D2] , [P1,P2, D2,D1] , [P1 , D1, P2, D2]

Couldn't solve the second one.


https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/solutions/1726019/count-all-valid-pickup-and-delivery-options/

 */
public class DD_ValidOrderList {
    public boolean isValidOrderList(String[] list) {

        Set<Integer> pickup = new HashSet<>();
        Set<Integer> dropoff = new HashSet<>();
        for (String s : list) {
            if (s.startsWith("P")) {
                int order = Integer.parseInt(s.substring(1));
                if (!pickup.contains(order)) {
                    pickup.add(order);
                } else {
                    return false;
                }
            } else if (s.startsWith("D")) {
                int order = Integer.parseInt(s.substring(1));
                if (!dropoff.contains(order) && pickup.contains(order)) {
                    dropoff.add(order);
                } else {
                    return false;
                }
            }
        }
        return pickup.size() == dropoff.size();
    }

    public List<List<String>> getAllPossibleOrders(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] visited = new int[n + 1];
        findAllPossibleOrders(n, res, new ArrayList<String>(), visited);
        return res;
    }

    // if pick up order can be descending or ascending
    private void findAllPossibleOrders(int n, List<List<String>> res, ArrayList<String> path, int[] visited) {
        if (path.size() == n * 2) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                visited[i]++;
                path.add("P" + i);
                findAllPossibleOrders(n, res, path, visited);
                visited[i]--;
                path.remove(path.size() - 1);
            }
            if (visited[i] == 1) {
                visited[i]++;
                path.add("D" + i);
                findAllPossibleOrders(n, res, path, visited);
                visited[i]--;
                path.remove(path.size() - 1);
            }
        }
    }

    // pickup & delivery permutation
    // if pick up order needs to be ascending and delivery can be descending or ascending
    // n = 2, get answer: [[P1, D1, P2, D2], [P1, P2, D1, D2], [P1, P2, D2, D1]]
    public List<List<String>> getAllPossibleOrders2(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] visited = new int[n + 1];
        findAllPossibleOrders2(n, res, new ArrayList<String>(), visited, 1);
        return res;
    }

    private void findAllPossibleOrders2(int n, List<List<String>> res, ArrayList<String> path, int[] visited, int currentOrderNumber) {
        System.out.println(currentOrderNumber);
        if (path.size() == n * 2) {
            System.out.println(path);
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                if (i >= currentOrderNumber) { // Check if the order number is greater than or equal to the current order number
                    visited[i]++;
                    path.add("P" + i);
                    findAllPossibleOrders2(n, res, path, visited, currentOrderNumber + 1);
                    visited[i]--;
                    path.remove(path.size() - 1);
                }
            }
            if (visited[i] == 1) {
                visited[i]++;
                path.add("D" + i);
                findAllPossibleOrders2(n, res, path, visited, currentOrderNumber); // Keep the same order number for delivery
                visited[i]--;
                path.remove(path.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        DD_ValidOrderList dd = new DD_ValidOrderList();
//        List<List<String>> ans = dd.getAllPossibleOrders2(2);  // 3
//        List<List<String>> ans2 = dd.getAllPossibleOrders2(3);
        // f(2) = 3, f(3) = 15; 90/ 6 A(3) permutation = 6, ascending is one of 6, see leetcode 1359
//        System.out.println(ans);


        String[] order0 = {"P1", "P3", "P1", "D1", "D1", "D2", "P2", "P0", "P2", "D2", "D0"};
        String[] order1 = {"P1", "P3", "P1", "D1", "D1", "D2", "P2", "P0", "D0", "P2", "D2"};
        String[] order2 = {"P1", "P3", "P1", "D1", "D3"};
        String[] order3 = {"P1", "P1", "D1", "D1"};
        String[] order4 = {"P1", "D1", "P1", "D1", "P2", "D2"};
        List<String> res0 = dd.findLongestValidSubarrayBruteForce(order0);
        List<String> res1 = dd.findLongestValidSubarrayBruteForce(order1);
        List<String> res2 = dd.findLongestValidSubarrayBruteForce(order2);
        List<String> res3 = dd.findLongestValidSubarrayBruteForce(order3);
        List<String> res4 = dd.findLongestValidSubarrayBruteForce(order4);
        System.out.println(res0);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);

//        String[] order = {"P3", "P1", "D1", "D3"};
//        System.out.println(dd.isValidOrderList(order));
//        System.out.println(dd.findLongestValidSubarrayBruteForce(order));
    }

    // working brute force
    public List<String> findLongestValidSubarrayBruteForce(String[] orders) {
        List<String> res = new ArrayList<>();
        int longest = 0;
        int n = orders.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String[] cur = Arrays.copyOfRange(orders, i, j + 1);
//                System.out.println(Arrays.asList(cur));
                if (isValidOrderList(cur) && cur.length > longest) {
                    longest = cur.length;
                    res = new ArrayList<>(Arrays.asList(cur));
//                    System.out.println(res);
                }
            }
        }
        return res;
    }

}
