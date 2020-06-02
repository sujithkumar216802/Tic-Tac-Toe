package com.sujithkumar.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AI {

    int[] value(int[][] grid) {
        int x = -1, y = -1, value = -32767;
        boolean flag = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i][j] != 0)
                    flag = false;

        if (flag) {
            return new int[]{new Random().nextInt(3), new Random().nextInt(3)};
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    int[][] temp = new int[3][3];
                    for (int q = 0; q < 3; q++)
                        for (int r = 0; r < 3; r++)
                            temp[q][r] = grid[q][r];
                    temp[i][j] = 2;
                    int p = calculate(temp, 1, 2);
                    if (p > value) {
                        y = i;
                        x = j;
                        value = p;
                    }
                }
            }
        }
        return new int[]{x, y};
    }


    int calculate(int[][] grid, int move, int dept) {
        ArrayList<Integer> n = new ArrayList<>();
        if (check(grid)) {
            if (move == 1) {
                return 11 - dept;
            } else {
                return dept - 11;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    int[][] temp = new int[3][3];
                    for (int q = 0; q < 3; q++)
                        for (int r = 0; r < 3; r++)
                            temp[q][r] = grid[q][r];
                    temp[i][j] = move;
                    if (move == 1) {
                        int p = calculate(temp, 2, dept + 1);
                        n.add(p);
                    } else {
                        int p = calculate(temp, 1, dept + 1);
                        n.add(p);
                    }
                }
            }
        }
        Collections.sort(n);
        if (move == 1 && n.size() >= 1)
            return n.get(0);
        else if (move == 2 && n.size() >= 1)
            return n.get(n.size() - 1);
        return 0;
    }


    boolean check(int[][] grid) {
        //horizontal
        for (int i = 0; i < 3; i++)
            if ((grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 1) || (grid[i][0] == 2 && grid[i][1] == 2 && grid[i][2] == 2))
                return true;
        //vertical
        for (int i = 0; i < 3; i++)
            if ((grid[0][i] == 1 && grid[1][i] == 1 && grid[2][i] == 1) || (grid[0][i] == 2 && grid[1][i] == 2 && grid[2][i] == 2))
                return true;
        //diagonal
        if ((grid[0][0] == 1 && grid[1][1] == 1 && grid[2][2] == 1) || (grid[0][0] == 2 && grid[1][1] == 2 && grid[2][2] == 2))
            return true;
        return (grid[0][2] == 1 && grid[1][1] == 1 && grid[2][0] == 1) || (grid[0][2] == 2 && grid[1][1] == 2 && grid[2][0] == 2);
    }
}


