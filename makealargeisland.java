import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class makealargeisland {
    public int largestIsland(int[][] grid) {
    int n = grid.length;
    int islandId = 2;
    Map<Integer, Integer> areaMap = new HashMap<>();
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 1) {
                int area = dfs(grid, i, j, islandId, dirs);
                areaMap.put(islandId++, area);
            }
        }
    }
    
    int max = areaMap.values().stream().max(Integer::compare).orElse(0);
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 0) {
                Set<Integer> seen = new HashSet<>();
                int total = 1;
                for (int[] d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    if (x >= 0 && y >= 0 && x < n && y < n && grid[x][y] > 1 && seen.add(grid[x][y])) {
                        total += areaMap.get(grid[x][y]);
                    }
                }
                max = Math.max(max, total);
            }
        }
    }
    
    return max == 0 ? n * n : max;
}

private int dfs(int[][] grid, int i, int j, int id, int[][] dirs) {
    int n = grid.length, area = 1;
    grid[i][j] = id;
    for (int[] d : dirs) {
        int x = i + d[0], y = j + d[1];
        if (x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 1) {
            area += dfs(grid, x, y, id, dirs);
        }
    }
    return area;
}

    
}
