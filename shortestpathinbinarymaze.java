import java.util.LinkedList;
import java.util.Queue;

public class shortestpathinbinarymaze {

    public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;
    if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
    
    int[][] dirs = {{0,1},{1,0},{1,1},{0,-1},{-1,0},{-1,-1},{-1,1},{1,-1}};
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{0, 0, 1});
    boolean[][] visited = new boolean[n][n];
    visited[0][0] = true;

    while (!q.isEmpty()) {
        int[] curr = q.poll();
        int r = curr[0], c = curr[1], d = curr[2];
        if (r == n - 1 && c == n - 1) return d;
        for (int[] dir : dirs) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc] && grid[nr][nc] == 0) {
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc, d + 1});
            }
        }
    }

    return -1;
}

    
}
