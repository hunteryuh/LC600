package com.alg.other;
/*

1/5/2024
Chris

Implement the following API for building and querying the management chain of a company.

Updates:

manager(employee_id, manager_id) indicates that manager is the direct manager of employee
peer(employee_id, other_id) indicates that employee and other have the same direct manager

Queries:

reports_to(employee_id, other_id) returns true if employee is known to report to other either directly or indirectly and false otherwise

Each employee has a single direct manager.
Each manager can have multiple direct reports.
The company hierarchy doesn’t change, its structure is simply revealed incrementally by calls to the manager and peer functions.
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GG_CompanyManagementChain {
    public static class CompanyManagement {
        private Map<Integer, Integer> managers;
        private Map<Integer, Set<Integer>> peers;

        public CompanyManagement() {
            managers = new HashMap<>();
            peers = new HashMap<>();
        }

        // put the emplid-managerid in the map, also its peers
        public void manager(int employeeId, int managerId) {
            if (managers.containsKey(employeeId)) return; // to avoid infinite loop
            managers.put(employeeId, managerId);
            for (int id : peers.getOrDefault(employeeId, new HashSet<>())) {
                manager(id, managerId);
            }
        }

        public void peer(int employeeId, int otherId) {
            if (!peers.containsKey(employeeId)) {
                peers.put(employeeId, new HashSet<>());
            }
            peers.get(employeeId).add(otherId);

            if (!peers.containsKey(otherId)) {
                peers.put(otherId, new HashSet<>());
            }
            peers.get(otherId).add(employeeId);

            Integer managerId = managers.getOrDefault(employeeId, managers.get(otherId));
            if (managerId != null) {
                manager(employeeId, managerId);
                manager(otherId, managerId);
            }
        }

        public boolean reportsTo(int employeeId, int otherId) {
            if (managers.containsKey(employeeId)) {
                int managerId = managers.get(employeeId);
                if (managerId == otherId) {
                    return true;
                } else {
                    return reportsTo(managerId, otherId);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        CompanyManagement company = new CompanyManagement();

        // Build the management chain
        company.manager(2, 1);
        company.manager(3, 1);
        company.manager(4, 2);
        company.manager(5, 2);
        company.manager(6, 3);
        company.manager(7, 3);

        // Set peers
        company.peer(4, 5);
        company.peer(6, 7);

        // Query the management chain
        System.out.println(company.reportsTo(4, 1)); // true
        System.out.println(company.reportsTo(5, 1)); // true
        System.out.println(company.reportsTo(6, 1)); // true
        System.out.println(company.reportsTo(7, 1)); // true
        System.out.println(company.reportsTo(5, 4)); // true
        System.out.println(company.reportsTo(4, 7)); // true
        System.out.println(company.reportsTo(1, 4)); // false
        System.out.println(company.reportsTo(1, 6)); // false
    }

}
