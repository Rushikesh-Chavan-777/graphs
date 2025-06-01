import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstras {


        // now, writing code to the Dikstra's algorithm to get the minimun distace from
    // a node
    public static int[] Dijkstrasq(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int src) {
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
    
}
