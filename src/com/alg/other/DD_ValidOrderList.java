package com.alg.other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static boolean validateOrder(String[] list) {

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

    // wrong
    boolean isValid(String[] orders) {
        // p1, p2, d1, d1
        Set<String> pickups = new HashSet<>();
        for (String order: orders) {
            String num = order.substring(1);
            if(order.charAt(0) == 'P')
                pickups.add(num);
            else if(!pickups.contains(num))
                return false;
        }
        return true;
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


    public static void main(String[] args) {
        DD_ValidOrderList dd = new DD_ValidOrderList();
        List<List<String>> ans = dd.getAllPossibleOrders(2);  // 6
        System.out.println(ans);
//        System.out.println(validateOrder(new String[]{"P1","P2","D3","D1"}));
    }
}
