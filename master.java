import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Pair {
    int distance;
    int node;

    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Pair1 {
    int node;
    int parent;

    public Pair1(int node1, int parent1) {
        this.node = node1;
        this.parent = parent1;
    }
}

class PairWordLadder {
    String first;
    int second;

    public PairWordLadder(String first1, int second1) {
        this.first = first1;
        this.second = second1;
    }
}

public class master {
    // lets try to solve the famous cycle detection problem in graph data structures
    // here lets use a bfs approach
    // assume we are given a n adjacency list
    // the idea is to chech level wise and see taht if a node isnt a parent and is
    // visited before, mena sits oart of the ycycle
    public static boolean isCycleBFS(int src, int V, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[src] = true;
        Queue<Pair1> q = new LinkedList<>();
        q.add(new Pair1(src, -1));

        while (!q.isEmpty()) {
            int noder = q.peek().node;
            int par = q.peek().parent;
            q.poll();

            for (Integer i : adj.get(noder)) {
                if (vis[i] == false) {
                    vis[i] = true;
                    q.add(new Pair1(i, noder));
                } else if (par != i) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycleBreadth(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++)
            vis[i] = false;
        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                if (isCycleBFS(i, V, adj, vis))
                    return true;
            }
        }
        return false;
    }

    // now, we shall use a DFS approach to detecting cycles
    public static boolean isCycleDFS(int src, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[src] = true;
        for (Integer i : adj.get(src)) {
            if (vis[i] == false) {
                if (isCycleDFS(i, src, adj, vis))
                    return true;
            } else if (parent != i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCycleDepth(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++)
            vis[i] = false;
        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                if (isCycleDFS(i, -1, adj, vis))
                    return true;
            }
        }
        return false;
    }

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

    // bipartite using BFS
    public static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1); // -1 means uncolored

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bfsCheck(i, adj, color))
                    return false;
            }
        }

        return true;
    }

    // BFS utility to check bipartiteness from a given node
    public static boolean bfsCheck(int start, ArrayList<ArrayList<Integer>> adj, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        color[start] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (Integer neighbor : adj.get(node)) {
                if (color[neighbor] == -1) {
                    // Color with opposite color
                    color[neighbor] = 1 - color[node];
                    q.offer(neighbor);
                } else if (color[neighbor] == color[node]) {
                    // Same color on both sides → not bipartite
                    return false;
                }
            }
        }
        return true;
    }

    /// bi[partite using DFS
    public static boolean isBipartiteDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1); // -1 = unvisited

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!dfsCheck(i, 0, color, adj))
                    return false;
            }
        }

        return true;
    }

    // DFS utility function
    public static boolean dfsCheck(int node, int c, int[] color, ArrayList<ArrayList<Integer>> adj) {
        color[node] = c;

        for (Integer neighbor : adj.get(node)) {
            if (color[neighbor] == -1) {
                if (!dfsCheck(neighbor, 1 - c, color, adj))
                    return false;
            } else if (color[neighbor] == color[node]) {
                return false; // Conflict: same color neighbo
            }
        }


        return true;
    }   

    // now, lets solve the problem of find a graph is cyclic or not gove that it is
    // directed
    public static boolean isCyclicDirectedDFS(int src, int V, ArrayList<ArrayList<Integer>> arr, int[] visited,
            int[] path) {
        visited[src] = 1;
        path[src] = 1;
        for (Integer i : arr.get(src)) {
            if (visited[i] == 0) {
                if (isCyclicDirectedDFS(i, V, arr, visited, path))
                    return true;
            } else if (path[i] == 1) {
                return true;
            }

        }
        path[src] = 0;
        return false;
    }

    public static boolean isCyclicDirected(int V, ArrayList<ArrayList<Integer>> arr) {
        int[] visited = new int[V];
        int[] path = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = 0;
        }

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (isCyclicDirectedDFS(i, V, arr, visited, path))
                    return true;
            }
        }
        return false;
    }

    // now, lets do topolocigal sort using BFS(Kahn's algorithm)
    public static int[] toposort(int V, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            indegree[i] = 0;
        }
        for (int i = 0; i < V; i++) {
            for (Integer j : adj.get(i)) {
                indegree[j]++;
            }
        }
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int[] topo = new int[V];
        int i = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topo[i++] = node;
            for (Integer it : adj.get(node)) {
                indegree[it]--;
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }
        return topo;
    }

    // now, we shall solve the famous word ladder 1 problem
    public static int WordLadderLength(String startword, String targetword, String[] wordlist) {
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < wordlist.length; i++) {
            set.add(wordlist[i]);
        }

        Queue<PairWordLadder> q = new LinkedList<>();
        q.offer(new PairWordLadder(startword, 1));
        set.remove(startword);
        while (!q.isEmpty()) {
            String worder = q.peek().first;
            int level = q.peek().second;
            q.poll();
            if (worder == targetword)
                return level;
            int len = worder.length();
            for (int i = 0; i < len; i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    char[] array = worder.toCharArray();
                    array[i] = j;
                    if (set.contains(array.toString())) {
                        set.remove(array.toString());
                        q.add(new PairWordLadder(array.toString(), level + 1));
                    }

                }
            }
        }
        return 0;
    }

    // now, writing code to the Dikstra's algorithm to get the minimun distace from
    // a node
    public static int[] Dijkstras(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        int[] dist = new int[V];
        Arrays.fill(dist, (int)(1e9));
        pq.add(new Pair(0, src));

        while(!pq.isEmpty()) {
            int distance = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();
            for(int i = 0; i < adj.get(node).size(); i++) {
                int noder = adj.get(node).get(i).get(0);
                int weight = adj.get(node).get(i).get(1);

                if(weight + distance < dist[noder]) {
                    dist[noder] = weight + distance;
                    pq.add(new Pair(weight, noder));
                }
            }
        }
        return dist;
    }




    

    public static void main(String[] args) {

    }

}
