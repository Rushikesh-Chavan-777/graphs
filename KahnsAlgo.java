import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KahnsAlgo {

        //now, lets do topolocigal sort using BFS(Kahn's algorithm)
    public static int[] toposort(int V, ArrayList<ArrayList<Integer>> adj){
        Queue<Integer> q = new LinkedList<>();
        int[] indegree = new int[V];
        for(int i = 0; i < V; i++) {
            indegree[i] = 0;
        }
        for(int i = 0; i < V; i++) {
            for(Integer j: adj.get(i)) {
                indegree[j]++;
            }
        }
        for(int i = 0; i < V; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }
        int[] topo = new int[V];
        int i = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            topo[i++] = node;
            for(Integer it : adj.get(node)) {
                indegree[it]--;
                if(indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }
        return topo;
    }
    
}
