import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class dijkstraWithPath {



    public static void dijkstraWithPath1(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
    int[] dist = new int[V];
    int[] parent = new int[V];
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

    Arrays.fill(dist, (int) 1e9);
    for (int i = 0; i < V; i++) parent[i] = i;

    dist[S] = 0;
    pq.offer(new Pair(S, 0));

    while (!pq.isEmpty()) {
        Pair current = pq.poll();
        int u = current.node;
        int d = current.dist;

        for (ArrayList<Integer> edge : adj.get(u)) {
            int v = edge.get(0);
            int weight = edge.get(1);
            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                parent[v] = u;
                pq.offer(new Pair(v, dist[v]));
            }
        }
    }

    for (int i = 0; i < V; i++) {
        System.out.print("Shortest path to node " + i + " (distance = " + dist[i] + "): ");
        printPath(i, parent);
        System.out.println();
    }
}

private static void printPath(int node, int[] parent) {
    if (parent[node] == node) {
        System.out.print(node + " ");
        return;
    }
    printPath(parent[node], parent);
    System.out.print(node + " ");
}

static class Pair {
    int node, dist;

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

    
}
