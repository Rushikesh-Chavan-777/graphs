import java.util.ArrayList;

public class isCycleDirected {
        // now, lets solve the problem of find a graph is cyclic or not gove  that it is directed
    public static boolean isCyclicDirectedDFS(int src, int V, ArrayList<ArrayList<Integer>> arr, int[] visited, int[] path) {
        visited[src] = 1;
        path[src] = 1;
        for(Integer i : arr.get(src)) {
            if(visited[i] == 0) {
                if(isCyclicDirectedDFS(i, V, arr, visited, path)) return true;
            }else if(path[i] == 1) {
                return true;
            }

        }
        path[src] = 0;
        return false;
    }
    public static boolean isCyclicDirected(int V, ArrayList<ArrayList<Integer>> arr) {
        int[] visited = new int[V];
        int[] path = new int[V];
        for(int i = 0; i < V; i++) {
            path[i] = 0;
        }

        for(int i = 0; i < V; i++) {
            if(visited[i] == 0) {
                if(isCyclicDirectedDFS(i, V, arr, visited, path)) return true;
            }
        }
        return false;
    }
    
}
