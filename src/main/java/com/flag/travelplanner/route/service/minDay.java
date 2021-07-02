package com.flag.travelplanner.route.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class minDay {

    public static void main(String[] args) {

        double [][] input = new double[5][5];
        input[0][0] = 0.0;
        input[0][1] = 1.5;
        input[0][2] = 1.0;
        input[0][3] = 1.0;
        input[0][4] = 1.5;
        input[1][0] = 1.5;
        input[1][1] = 0.0;
        input[1][2] = 1.5;
        input[1][3] = 1.5;
        input[1][4] = 2.0;
        input[2][0] = 1.0;
        input[2][1] = 1.5;
        input[2][2] = 0.0;
        input[2][3] = 2.0;
        input[2][4] = 2.5;
        input[3][0] = 1.0;
        input[3][1] = 1.5;
        input[3][2] = 2.0;
        input[3][3] = 0.0;
        input[3][4] = 1.5;
        input[4][0] = 1.5;
        input[4][1] = 2.0;
        input[4][2] = 2.5;
        input[4][3] = 1.5;
        input[4][4] = 0.0;

        int n = input.length;
        List<Integer> cur = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        double maxHour = 3.0;
        visited.add(0);
        for (int i = 1; i < n; i++) {
            if (!visited.contains(i)) {
                cur.add(0);
                dfs(i, input[0][i], cur, res, visited, input, maxHour);
            }
        }
        System.out.println(res);
    }

    private static void dfs(int i, double curHour, List<Integer> cur, List<List<Integer>> res, Set<Integer> visited, double [][] input, double maxHour) {
        if (visited.contains(i)) return;
        if (curHour > maxHour || cur.size() == 4) {
            res.add(new LinkedList<>(cur));
            cur.clear();
            return;
        }
        visited.add(i);
        cur.add(i);
        for (int j = 1; j < input.length; j++) {
            dfs(j, curHour + input[i][j], cur, res, visited, input, maxHour);
            //return;
        }
    }
}
