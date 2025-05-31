import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class iscycleBFS {

    // lets try to solve the famous cycle detection problem in graph data structures
    // here lets use a bfs approach
    // assume we are given a n adjacency list
    // the idea is to chech level wise and see taht if a node isnt a parent and is
    // visited before, mena sits oart of the ycycle
    public static boolean isCycleBFS(int src, int V, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[src] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, -1));

        while (!q.isEmpty()) {
            int noder = q.peek().node;
            int par = q.peek().parent;
            q.poll();

            for (Integer i : adj.get(noder)) {
                if (vis[i] == false) {
                    vis[i] = true;
                    q.add(new Pair(i, noder));
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
    
}
