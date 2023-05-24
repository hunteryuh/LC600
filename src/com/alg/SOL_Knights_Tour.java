package com.alg;

/**
 * Created by HAU on 6/14/2017.
 */
public class SOL_Knights_Tour {

    public static int N = 8;
    public static boolean isValid( int x, int y, int sol[][]){
        return (x >= 0 && x < N && y >=0 && y < N && sol[x][y] == -1);
    }
    public static void printTour(int sol[][]){
        for ( int x = 0; x < N; x++){
            for (int y = 0; y < N; y++){
                System.out.print(sol[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static boolean SolveKnightTour(){
        int sol[][] = new int[N][N];
        // initialization
        for ( int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;

        // possible move pairs
        int xmove[] ={-1,-2,-2,-1,1,2,2,1};
        int ymove[] ={2,1,-1,-2,-2,-1,1,2}; // should be in a cyclic order?
/*        int xmove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int ymove[] = {1, 2, 2, 1, -1, -2, -2, -1};*/

        sol[0][0] = 0;
        if(!solverKT(0,0,1,sol,xmove,ymove)) {
            System.out.println("no solution");
            return false;
        } else printTour(sol);
        return true;

    }

    // recursive function /utility
    private static boolean solverKT(int x, int y, int count, int sol[][],
                                   int[] xmove, int[] ymove){
        int k, newx, newy;
        if (count == N * N) return true;

        for ( k = 0; k < N; k++){
            newx = x + xmove[k]; newy = y + ymove[k];
            if (isValid(newx,newy,sol)) {
                sol[newx][newy] = count;
                if (solverKT(newx,newy,count+1,sol,xmove,ymove))
                    return true;
                else sol[newx][newy] = -1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SolveKnightTour();
    }
}
