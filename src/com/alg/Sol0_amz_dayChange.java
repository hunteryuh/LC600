package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/5/2018.
 */
/*int[] dayChange(int[] cells, int days), cells 数组，有8个元素，每天的变化情况是 当前位置
cell = (cell[i - 1] == cell[i + 1]) ? 0 : 1, 左右相等，当前位置为0，
不等为1， 默认 cells[0]左边 和 cells[cells.length - 1]右边的位置元素都是0， 求days天后的变化.
例子：
cells: [1 0 0 0 0 1 0 0]
days: 1
output: [0 1 0 0 1 0 1 0]*/
public class Sol0_amz_dayChange {
    public static int[] daysChange(int[] days, int k){
        if ( days == null || k <= 0)
            return days;
        int len = days.length;
        int[] res = new int[len + 2];
        res[0] = res[len + 1] =0;
        for(int i = 1; i < len + 1; i++){
            res[i] = days[i-1];
        }
        int pre = res[0];
        for(int i = 0; i < k; i++){
            for ( int j = 1; j < len + 1; j++){
                int tmp = res[j];
                res[j] = pre ^ res[j+1];
                pre = tmp;
            }
        }
        return Arrays.copyOfRange(res,1,len + 1);
    }

    public static void main(String[] args) {
        int[] a = {1, 0, 0, 0, 0, 1, 0, 0};
        int[] res = daysChange(a,1);
        System.out.println(Arrays.toString(res));
    }
}
