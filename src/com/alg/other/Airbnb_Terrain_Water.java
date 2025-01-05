package com.alg.other;

import java.util.Arrays;

public class Airbnb_Terrain_Water {
    /*
    Print a terrain where each number represents the height of a column at that index.

+
++    +  +
+++  ++ ++
++++ ++ ++
+++++++ ++
++++++++++ <--- base layer

pour water
     */
    public void printTerrain(int[] array) {
        int max = 0;
        for (int m: array) {
            max = Math.max(m, max);
        }
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < array.length; j++) {
                if (i <= array[j]) {
                    System.out.print('+');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        for (int j = 0; j < array.length; j++) {
            System.out.print('+');
        }
    }
    /*
    Part 2:
Imagine we drop a certain amount of water at a certain column.
The water can flow in whichever direction makes sense. Print the terrain after all the water has fallen.

dumpWater(terrain, waterAmount=8, column=1)
     */

    public int[] dumpWater(int[] arr, int waterAmount, int k) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        int j = k;
        for (int i = 0; i < waterAmount; i++) {
            while (k > 0 && res[k -1] <= res[k]) {
                k--;
            }
            while (k < res.length - 1 && res[k] >= res[k + 1]) {
                k++;
            }
            while ( k> j && res[k-1] == res[k]) {
                k--;
            }
            res[k]++;
            k = j;
        }
        // or use a different array int[] water to store water heights at each index
        // print(water, arr) for private print(int[] water, int[] height)
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5, 4, 3, 2, 1, 3, 4, 0, 3, 4};
        Airbnb_Terrain_Water aa = new Airbnb_Terrain_Water();
        aa.printTerrain(arr);
        int[] after = aa.dumpWater(arr, 8, 1);

        System.out.println(Arrays.toString(after));
        int max = 0;
        for (int m: after) {
            max = Math.max(m, max);
        }
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < after.length; j++) {
                if (i <= after[j] && i > arr[j]) {
                    System.out.print('w');
                } else if (i <= arr[j]) {
                    System.out.print('+');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        for (int j = 0; j < after.length; j++) {
            System.out.print('+');
        }
    }
}
