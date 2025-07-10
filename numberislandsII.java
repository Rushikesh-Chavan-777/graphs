import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class numberislandsII {
    private Map<Integer, Integer> parent = new HashMap<>();

    public int removeStones(int[][] stones) {
        for (int[] stone : stones) {
            int row = stone[0];
            int col = ~stone[1]; // Use ~ to distinguish rows and columns
            union(row, col);
        }

        Set<Integer> uniqueRoots = new HashSet<>();
        for (int key : parent.keySet()) {
            uniqueRoots.add(find(key));
        }

        return stones.length - uniqueRoots.size();
    }

    private int find(int x) {
        parent.putIfAbsent(x, x);
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent.put(rootX, rootY);
        }
    }
    
}
