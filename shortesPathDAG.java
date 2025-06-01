import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class shortesPathDAG {
    public static int[] shortestPathDAG(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
    boolean[] visited = new boolean[V];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            topoSort(i, visited, stack, adj);
        }
    }

    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[S] = 0;

    while (!stack.isEmpty()) {
        int node = stack.pop();
        if (dist[node] != Integer.MAX_VALUE) {
            for (ArrayList<Integer> edge : adj.get(node)) {
                int next = edge.get(0);
                int weight = edge.get(1);
                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                }
            }
        }
    }

    return dist;
}

private static void topoSort(int node, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
    visited[node] = true;
    for (ArrayList<Integer> edge : adj.get(node)) {
        int neighbor = edge.get(0);
        if (!visited[neighbor]) {
            topoSort(neighbor, visited, stack, adj);
        }
    }
    stack.push(node);
}

    
}
