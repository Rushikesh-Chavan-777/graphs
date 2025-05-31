import java.util.LinkedList;
import java.util.Queue;

public class nearestones {

        // nearest 1's
    public static int[][] nearestOnes(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] distance = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> q = new LinkedList<>();

        // Step 1: Add all cells with 1 to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    q.add(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        // Directions: up, right, down, left
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        // Step 2: Perform BFS
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0], c = current[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                // Check bounds and unvisited
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                    distance[nr][nc] = distance[r][c] + 1;
                    visited[nr][nc] = true;
                    q.add(new int[] { nr, nc });
                }
            }
        }
        return distance;
    }
}
