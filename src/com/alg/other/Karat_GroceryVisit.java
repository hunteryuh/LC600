package com.alg.other;
/*
You are going on a camping trip, but before you leave you need to buy groceries. To optimize your time spent in the store, instead of buying the items from your shopping list in order, you plan to buy everything you need from one department before moving to the next.
Given an unsorted list of products with their departments and a shopping list, return the time saved in terms of the number of department visits eliminated.
Example:
products = [
    ["Cheese",          "Dairy"],
    ["Carrots",         "Produce"],
    ["Potatoes",        "Produce"],
    ["Canned Tuna",     "Pantry"],
    ["Romaine Lettuce", "Produce"],
    ["Chocolate Milk",  "Dairy"],
    ["Flour",           "Pantry"],
    ["Iceberg Lettuce", "Produce"],
    ["Coffee",          "Pantry"],
    ["Pasta",           "Pantry"],
    ["Milk",            "Dairy"],
    ["Blueberries",     "Produce"],
    ["Pasta Sauce",     "Pantry"]
]
list1 = ["Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"]
"Produce","Dairy", "Pantry"
For example, buying the items from list1 in order would take 5 department visits, whereas your method would lead to only visiting 3 departments, a difference of 2 departments.
Produce(Blueberries)->Dairy(Milk)->Pantry(Coffee/Flour)->Dairy(Cheese)->Produce(Carrots) = 5 department visits
New: Produce(Blueberries/Carrots)->Pantry(Coffee/Flour)->Dairy(Milk/Cheese) = 3 department visits
list2 = ["Blueberries", "Carrots", "Coffee", "Milk", "Flour", "Cheese"] => 2
list3 = ["Blueberries", "Carrots", "Romaine Lettuce", "Iceberg Lettuce"] => 0
list4 = ["Milk", "Flour", "Chocolate Milk", "Pasta Sauce"] => 2
list5 = ["Cheese", "Potatoes", "Blueberries", "Canned Tuna"] => 0
All Test Cases:
shopping(products, list1) => 2
shopping(products, list2) => 2
shopping(products, list3) => 0
shopping(products, list4) => 2
shopping(products, list5) => 0
Complexity Variable:
n: number of products
*/

import java.util.*;

public class Karat_GroceryVisit {
    public static int shopping(String[][] products, String[] shoppingList) {
        // Create a map to store products by department
        Map<String, Set<String>> departmentMap = new HashMap<>();
        for (String[] product : products) {
            String item = product[0];
//            item.length()   string length()
//            products.length  array length
//            departmentMap.size()  map size()
//            new ArrayList<>().size()  list size()
            String department = product[1];
            departmentMap.computeIfAbsent(department, k -> new HashSet<>()).add(item);
        }

        // Initialize a set to keep track of visited departments
        Set<String> visitedDepartments = new HashSet<>();

        // Iterate through the shopping list
        for (String item : shoppingList) {
            for (String department : departmentMap.keySet()) {
                if (departmentMap.get(department).contains(item)) {
                    visitedDepartments.add(department);
                    break; // Move to the next item
                }
            }
        }

        // Calculate the number of department visits saved
        int totalDepartments = departmentMap.size();
        int savedVisits = totalDepartments - visitedDepartments.size();
        return savedVisits;
    }

    public static void main(String[] args) {
        String[][] products = {
                {"Cheese", "Dairy"},
                // ... (other product entries)
        };

        String[] list1 = {"Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"};
        System.out.println("Saved visits for list1: " + shopping(products, list1)); // 2

        // Repeat for other test cases...
    }
}
