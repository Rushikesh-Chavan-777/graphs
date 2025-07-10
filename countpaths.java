import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class countpaths {

    public int countPaths(int n, int[][] roads) {
    List<List<int[]>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    for (int[] r : roads) {
        graph.get(r[0]).add(new int[]{r[1], r[2]});
        graph.get(r[1]).add(new int[]{r[0], r[2]});
    }

    long[] dist = new long[n];
    Arrays.fill(dist, Long.MAX_VALUE);
    int[] ways = new int[n];
    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    
    dist[0] = 0;
    ways[0] = 1;
    pq.offer(new long[]{0, 0});
    
    int mod = (int)1e9 + 7;

    while (!pq.isEmpty()) {
        long[] curr = pq.poll();
        long d = curr[0];
        int node = (int) curr[1];

        if (d > dist[node]) continue;

        for (int[] nei : graph.get(node)) {
            int next = nei[0];
            long time = nei[1];
            if (dist[next] > d + time) {
                dist[next] = d + time;
                ways[next] = ways[node];
                pq.offer(new long[]{dist[next], next});
            } else if (dist[next] == d + time) {
                ways[next] = (ways[next] + ways[node]) % mod;
            }
        }
    }
    
    return ways[n - 1];
}

    
}
