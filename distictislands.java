import java.util.ArrayList;
import java.util.HashSet;

public class distictislands {


        // now, lets solve the famous count sistaic islands problem
    public static void CountIslandsDFS(int n, int m, int row, int col, boolean[][] visted, int row0, int col0,
            int[][] grid, ArrayList<String> setter) {
        visted[row][col] = true;
        int[] rower = { -1, 0, +1, 0 };
        int[] columnar = { 0, 1, 0, -1 };
        for (int i = 0; i < 4; i++) {
            int new_row = row + rower[i];
            int new_col = col + columnar[i];
            if (new_row >= 0 && new_row < n && new_col >= 0 && new_col < m && visted[new_row][new_col] == false
                    && grid[new_row][new_col] == 1) {
                setter.add(toString(new_row - row0, new_col - col0));
                CountIslandsDFS(n, m, new_row, new_col, visted, row0, col0, grid, setter);
            }
        }
    }

    public static String toString(int n, int m) {
        return Integer.toString(n) + ' ' + Integer.toString(m);
    }

    public static int countDistictIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        HashSet<ArrayList<String>> set = new HashSet<>();
        boolean[][] visted = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visted[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ArrayList<String> setter = new ArrayList<String>();
                if (visted[i][j] == false && grid[i][j] == 1) {
                    CountIslandsDFS(n, m, i, j, visted, i, j, grid, setter);
                }
                set.add(setter);
            }
        }
        return set.size();
    }
    
}
