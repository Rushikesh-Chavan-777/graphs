public class numberofenclaves {

        // number of enclaves
    public static int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Directions: up, right, down, left
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        // Step 1: Flood-fill all land cells connected to the boundary
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i == 0 || j == 0 || i == rows - 1 || j == cols - 1)
                        && grid[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, grid, visited, dr, dc);
                }
            }
        }

        // Step 2: Count unvisited land cells (enclaves)
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    // DFS utility function
    public static void dfs(int r, int c, int[][] grid, boolean[][] visited, int[] dr, int[] dc) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length
                    && grid[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(nr, nc, grid, visited, dr, dc);
            }
        }
    }
    
}
