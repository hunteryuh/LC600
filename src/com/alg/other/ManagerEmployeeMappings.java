package com.alg.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerEmployeeMappings {

    private static Set<Character> findAllReportingEmployees(Character manager,
        Map<Character, Set<Character>> managerToEmployeeMappings,
        Map<Character, Set<Character>> result) {
        // if the subproblem is already seen before
        if (result.containsKey(manager)) {
            // return the already computed mapping
            return result.get(manager);
        }

        // find all employees reporting directly to the current manager
        Set<Character> managerEmployees = managerToEmployeeMappings.get(manager);

        // find all employees reporting indirectly to the current manager
        for (char reportee: managerEmployees)
        {
            // find all employees reporting to the current employee
            Set<Character> employees = findAllReportingEmployees(reportee,
                managerToEmployeeMappings, result);

            // move those employees to the current manager
            if (employees != null) {
                managerEmployees.addAll(employees);
            }
        }

        // save the result to avoid recomputation and return it
        result.put(manager, managerEmployees);
        return managerEmployees;
    }

    // Find all employees who directly or indirectly reports to a manager
    public static Map<Character, Set<Character>> findEmployees(Map<Character, Character>
                                                                   employeeToManagerMappings)
    {
        // store manager to employee mappings in a new map.
        // `List<Character>` is used since a manager can have several employees mapped
        Map<Character, Set<Character>> managerToEmployeeMappings = new HashMap<>();

        // fill the above map with the manager to employee mappings
        for (Map.Entry<Character, Character> entry: employeeToManagerMappings.entrySet())
        {
            char employee = entry.getKey();
            char manager = entry.getValue();

            managerToEmployeeMappings.putIfAbsent(manager, new HashSet<>());
            managerToEmployeeMappings.putIfAbsent(employee, new HashSet<>());

            // don't map an employee with itself
            if (employee != manager) {
                managerToEmployeeMappings.get(manager).add(employee);
            }
        }

        // construct an ordered map to store the result
        Map<Character, Set<Character>> result = new HashMap<>();

        // find all reporting employees (direct and indirect) for every manager
        // and store the result in a map
        for (Map.Entry<Character, Character> entry: employeeToManagerMappings.entrySet()) {
            findAllReportingEmployees(entry.getKey(), managerToEmployeeMappings,
                result);
        }

        return result;
    }

    public static void main(String[] args) {
        // construct a mapping from employee to manager
        Map<Character, Character> employeeToManagerMappings = new HashMap<>();

        employeeToManagerMappings.put('A', 'A');
        employeeToManagerMappings.put('B', 'A');
        employeeToManagerMappings.put('C', 'B');
        employeeToManagerMappings.put('D', 'B');
        employeeToManagerMappings.put('E', 'D');
        employeeToManagerMappings.put('F', 'E');

        Map<Character, Set<Character>> result = findEmployees(employeeToManagerMappings);

        // print contents of the resulting map
        for (Map.Entry<Character, Set<Character>> entry: result.entrySet()) {
            System.out.println(entry.getKey() + " —> " + entry.getValue());
        }
    }
}
