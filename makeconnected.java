public class makeconnected {

    public int makeConnected(int n, int[][] connections) {
    if (connections.length < n - 1) return -1;
    
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) parent[i] = i;
    
    int components = n;
    for (int[] conn : connections) {
        int u = find(parent, conn[0]);
        int v = find(parent, conn[1]);
        if (u != v) {
            parent[u] = v;
            components--;
        }
    }
    
    return components - 1;
}

private int find(int[] parent, int i) {
    if (parent[i] != i) parent[i] = find(parent, parent[i]);
    return parent[i];
}

    
}
