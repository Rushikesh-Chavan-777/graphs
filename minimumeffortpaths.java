public class minimumeffortpaths {
    public int minimumEffortPath(int[][] heights) {
    int rows = heights.length, cols = heights[0].length;
    int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    int left = 0, right = 1_000_000;

    while (left < right) {
        int mid = (left + right) / 2;
        boolean[][] visited = new boolean[rows][cols];
        if (dfs(heights, visited, 0, 0, mid, dirs)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    return left;
}

private boolean dfs(int[][] h, boolean[][] visited, int r, int c, int maxDiff, int[][] dirs) {
    int rows = h.length, cols = h[0].length;
    if (r == rows - 1 && c == cols - 1) return true;
    visited[r][c] = true;
    for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && !visited[nr][nc]) {
            if (Math.abs(h[nr][nc] - h[r][c]) <= maxDiff) {
                if (dfs(h, visited, nr, nc, maxDiff, dirs)) return true;
            }
        }
    }
    return false;
}

    
}
