import java.util.ArrayList;
import java.util.Arrays;

public class isBipartiteDFS {

        /// bi[partite using DFS
    public static boolean isBipartiteDFS1(int V, ArrayList<ArrayList<Integer>> adj) {
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
                return false; // Conflict: same color neighbor
            }
        }

        return true;
    }


    
}
