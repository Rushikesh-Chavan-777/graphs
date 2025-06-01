import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class shortestPathUndirected {



    public static int[] shortestPathUndirected1(int V, ArrayList<ArrayList<Integer>> adj, int S) {
    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[S] = 0;

    Queue<Integer> q = new LinkedList<>();
    q.offer(S);

    while (!q.isEmpty()) {
        int node = q.poll();

        for (int neighbor : adj.get(node)) {
            if (dist[node] + 1 < dist[neighbor]) {
                dist[neighbor] = dist[node] + 1;
                q.offer(neighbor);
            }
        }
    }

    return dist;
}

    
}
