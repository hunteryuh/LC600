package com.alg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.

You are given an array of employees employees where:

employees[i].id is the ID of the ith employee.
employees[i].importance is the importance value of the ith employee.
employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.



Example 1:


Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
Output: 11
Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
Example 2:


Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
Output: -3
Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
Thus, the total importance value of employee 5 is -3.


Constraints:

1 <= employees.length <= 2000
1 <= employees[i].id <= 2000
All employees[i].id are unique.
-100 <= employees[i].importance <= 100
One employee has at most one direct leader and may have several subordinates.
The IDs in employees[i].subordinates are valid IDs.
 */
public class Sol690_EmployeeImportance {
    // bfs
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for(Employee e: employees) {
            map.put(e.id, e);
        }
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        int total = 0;
        while (!queue.isEmpty()) {
            Employee cur = queue.poll();
            total += cur.importance;
            for (int sub: cur.subordinates) {
                queue.offer(map.get(sub));
            }
        }
        return total;
    }

    // dfs
    public int getImportanceDFS(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee ee: employees) {
            map.put(ee.id, ee);
        }
        return getImportanceHelper(map, id);
    }

    private int getImportanceHelper(Map<Integer, Employee> map, int id) {
        Employee root = map.get(id);
        int total = root.importance;
        for (int sub: root.subordinates) {
            total += getImportanceHelper(map, sub);
        }
        return total;
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };
    Map<Integer, Employee> map = new HashMap<>();
    double max = Double.MIN_VALUE;
    int maxId = 0;
    public int getEmployeeWithHighestRatingAverage(List<Employee> employees) {

        for (Employee ee: employees) {
            map.put(ee.id, ee);
        }
        getMaxAveHelper(map.get(0));
        return maxId;
    }
    // see public class Sol0_amz_MaximumSubtreeofAverage
    // get highest perfor‍‍‌‍‍‌‍‌‌‌‌‍‍‍‌‍mance r‍‍‍‌‌‍‌‍‌‌‍‍‍‍‍‍‍‌ating average
    private double[] getMaxAveHelper(Employee root) {
        if (root == null) {
            return new double[]{0, 0}; // sum and count
        }
        double currentTotal = root.importance;
        double count = 1;
        for (int x: root.subordinates) {
            double[] cur = getMaxAveHelper(map.get(x));
            currentTotal += cur[0];
            count += cur[1];
        }
        double average  = currentTotal/count;
        if (count > 1 && average > max) { //taking "at least 1 child" into account
            max = average;
            maxId = root.id;
        }
        return new double[]{currentTotal, count};
    }
}
