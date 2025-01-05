package com.alg;

import jdk.internal.util.xml.impl.Pair;

import java.util.Arrays;
import java.util.Stack;

public class Sol735_AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int cur: asteroids) {
            if (cur > 0) {
                stack.push(cur);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() + cur < 0) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(cur);
                } else if (stack.peek() + cur == 0) {
                    stack.pop();
                }
            }
        }
        // java8
//        return stack.stream().mapToInt(i->i).toArray();
        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    // rewrite with better flow
    public int[] asteroidCollision1(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int as: asteroids) {
            if (as > 0) {
                stack.push(as);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() + as < 0) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    stack.push(as);
                } else {
                    if (stack.peek() < 0) {
                        stack.push(as);
                    }
                    if (stack.peek() + as == 0 ) {
                        stack.pop();
                    }
                }

            }
        }
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

// https://leetcode.com/problems/asteroid-collision/solutions/485356/Java-Stack-Solution/
    public int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            if (asteroid < 0) {
                while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                    int prevAsteroid = stack.pop();
                    if (Math.abs(asteroid) == prevAsteroid) {
                        asteroid = 0;
                    } else {
                        asteroid = Math.abs(asteroid) > prevAsteroid ? asteroid : prevAsteroid;
                    }
                }
            }
            if (asteroid != 0) {
                stack.push(asteroid);
            }
        }
        return stack.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        int[] as = new int[]{-2, -2, 1, -2};
        Sol735_AsteroidCollision ss = new Sol735_AsteroidCollision();
        int[] res = ss.asteroidCollision(as);
        System.out.println(Arrays.toString(res));
    }
}
