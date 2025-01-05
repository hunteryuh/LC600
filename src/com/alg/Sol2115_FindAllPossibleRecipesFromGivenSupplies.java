package com.alg;

import java.util.*;

/*
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have,
and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.



Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
Example 3:

Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
 */
// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/solutions/1646584/java-python-3-toplogical-sort-w-brief-explanation/
public class Sol2115_FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>(); // key: ingredient, value: recipes
        Map<String, Integer> indegree = new HashMap<>(); // ingredient indegree
        Set<String> recipeSet = new HashSet<>(Arrays.asList(recipes));

        for (String supply: supplies) {
//            graph.putIfAbsent(supply, new ArrayList<>());
            indegree.put(supply, 0);
        }
//
//        for (String recipe: recipes) {
//            graph.putIfAbsent(recipe, new ArrayList<>());
//            indegree.put(recipe, 0);
//            recipeSet.add(recipe);
//        }
        // add edges
        for (int i= 0; i < ingredients.size(); i++) {
            for (String ingredient: ingredients.get(i)) {
                graph.putIfAbsent(ingredient, new ArrayList<>());
                graph.get(ingredient).add(recipes[i]);
//                indegree.put(recipes[i], indegree.get(recipes[i]) + 1); // if initialized
                indegree.put(recipes[i], indegree.getOrDefault(recipes[i], 0) + 1);
            }
        }
        Queue<String> queue = new LinkedList<>();
        for (String s : indegree.keySet()) {
            if (indegree.get(s) == 0) {
                queue.offer(s);
            }
        }
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (recipeSet.contains(cur)) {
                result.add(cur);
            }
//            for (String neighbor : graph.get(cur)) { // if initialized
            for (String neighbor : graph.getOrDefault(cur, new ArrayList<>())) { // if not initialized
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return result;

    }
}
