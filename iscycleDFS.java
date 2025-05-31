import java.util.ArrayList;

public class iscycleDFS {

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
    
}
