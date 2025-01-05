package com.alg.other;
import java.util.*;
public class DB_RevenueReferral {
    /*
databricks interview 7/12/2024
revenue referral association
When Databricks acquires customers, they can commit to a certain amount of
money they will spend - we will call this revenue.

Total revenue for a customer = initial revenue + initial revenue of direct referred customers

TR of customer 2 = 40 +  0 = 40
TR of customer 1 = 20 + 40 = 60
TR of customer 0 = 10 + 20 = 30

To implement:
insert(revenue: int) -> int (auto-incrementing customer id starting at 0)
insert(revenue: int, referrer: int) -> int (auto-incrementing customer id starting at 0)
# Returns the set of k customers (IDs) that have the lowest TR >= min_total_revenue
get_lowest_k_by_total_revenue(k: int, min_total_revenue: int) -> Set<int>

Example:
insert(10) -> 0
insert(20, 0) -> 1
insert(40, 1) -> 2
# insert(100, 0) -> 3
# The set of k=1 custmomer who has the lowest TR >= 35
get_lowest_k_by_total_revenue(1, 35) -> Set(2)
# The set of k=2 custmomers who have the lowest TR >= 35
get_lowest_k_by_total_revenue(2, 35) -> Set(1,2)

 */

    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */

    public static void main(String[] args) {

        // Solution solution = new Solution();

        DB_RevenueReferral uR = new DB_RevenueReferral();
        uR.insert(10);
        uR.insert(20, 0);
        uR.insert(40, 1);
        uR.insert(100);

        List<Integer> list = uR.getLowestKByTotal(1, 35);
        List<Integer> list2 = uR.getLowestKByTotal(2, 35);

        System.out.println("lowest 1 > 35 user is " + list);
        System.out.println("lowest 2 > 35 user are " + list2);
    }

    private Map<Integer, Integer> userToRevenue;
    private int userId;

    public DB_RevenueReferral() {
        this.userId = 0;
        this.userToRevenue = new HashMap<>();
    }
    // O(1)

    public void insert(int revenue) {
        userToRevenue.put(this.userId, revenue);
        userId++;
    }

    // O(1)
    // treemap, logn
    public void insert(int revenue, int referrer) {
        int currentRev = userToRevenue.get(referrer);
        userToRevenue.put(referrer, revenue + currentRev);
        userToRevenue.put(userId, revenue);
        userId++;
    }


    // time: nLgn
    // faster
    // 30, 1; 40, 2 nlgk
    // {
    //  30: 0,
    //  40: 2,
    //  60: 1,
    // }
    // logn* + k
    public List<Integer> getLowestKByTotal(int k, int total) {
        // map userid -> revenue
        List<Integer> result = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> userRevenuePairs = new ArrayList<>(userToRevenue.entrySet());
        userRevenuePairs.sort((a, b) -> a.getValue() - b.getValue()); // low -> high  nlogn
        for (Map.Entry<Integer, Integer> item : userRevenuePairs) {
            if (result.size() < k && item.getValue() >= total) {
                result.add(item.getKey());
            }
            if (result.size() >= k) {
                break;
            }
        }
        return result;
    }


}
