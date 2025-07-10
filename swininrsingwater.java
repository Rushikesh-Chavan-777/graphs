import java.util.PriorityQueue;

public class swininrsingwater {

    public int swimInWater(int[][] grid) {
    int n = grid.length;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    boolean[][] visited = new boolean[n][n];
    pq.offer(new int[]{grid[0][0], 0, 0});
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    
    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int time = curr[0], x = curr[1], y = curr[2];
        if (x == n - 1 && y == n - 1) return time;
        if (visited[x][y]) continue;
        visited[x][y] = true;
        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                pq.offer(new int[]{Math.max(time, grid[nx][ny]), nx, ny});
            }
        }
    }
    return -1;
}

    
}
