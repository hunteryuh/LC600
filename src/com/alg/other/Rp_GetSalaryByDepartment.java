package com.alg.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
Write a function that takes data blob as input (see example below)
 and calculates the sum of salaries in each department.

[
  {
      "id": "583fd999a2a3a4121dd6b6fc",
      "name": "Brook Quigley",
      "employmentType": "HOURLY_FT",
      "dob": "1993-04-31",
      "identifiedGender": "FEMALE",
      "title": "Sales Development Rep",
      "compensation": {
        "currency": "USD",
        "annualSalary": 65000.00
      },
      "department": "Sales",
      "workLocation": "San Francisco",
      "startDate": "2020-05-31"
  },
  {
    "id": "583fd982a2a3a4121dd6b429",
    "name": "John Grady",
    "employmentType": "HOURLY_FT",
    "dob": "1996-05-31",
    "identifiedGender": "MALE",
    "title": "Customer Support Associate",
    "compensation": {
      "currency": "USD",
      "annualSalary": 52000.00
    },
    "department": "Customer Support",
    "workLocation": "San Francisco",
    "startDate": "2019-05-31"
  }
]
*/
// rippling
public class Rp_GetSalaryByDepartment {
    // max, average, sum for each department
    public Map<String, List<Double>> getSalaryForDepartment(List<Employee> employees, boolean isDepartment) {
        Map<String, List<Double>> res = new HashMap<>();
        Map<String, Integer> countsDept = new HashMap<>();
        for (Employee ee: employees) {
            String department = ee.department;
            String workLocation = ee.workLocation;
            String key = isDepartment ? department : workLocation;
            Double salary = ee.compensation.annualSalary;
            if (!res.containsKey(key)) {
                res.put(key, Arrays.asList(salary, salary, salary));
                countsDept.put(key, 1);
            } else {
                Double previousMax = res.get(key).get(0);
                Double previousSum = res.get(key).get(2);
                double currentSum = previousSum + salary;
                res.get(key).set(0, Math.max(salary, previousMax));
                res.get(key).set(2, currentSum);
                countsDept.put(key, 1 + countsDept.get(key));
                int currentCounts = countsDept.get(key);
                res.get(key).set(1, currentSum / currentCounts);
            }
        }
        return res;
    }

    class Employee{
        String department;
        String workLocation;
        Compensation compensation;

        Employee(String department, String workLocation, double annualSalary) {
            this.department = department;
            this.workLocation = workLocation;
            this.compensation = new Compensation(annualSalary);
        }
    }

    class Compensation {
        double annualSalary;
        Compensation(double annualSalary) {
            this.annualSalary = annualSalary;
        }
    }
}
