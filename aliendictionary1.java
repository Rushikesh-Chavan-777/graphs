import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class aliendictionary1 {


    public static String alienOrder(String[] words) {
    Map<Character, List<Character>> adj = new HashMap<>();
    Map<Character, Integer> inDegree = new HashMap<>();

    // Initialize the graph
    for (String word : words) {
        for (char c : word.toCharArray()) {
            adj.putIfAbsent(c, new ArrayList<>());
            inDegree.putIfAbsent(c, 0);
        }
    }

    // Build graph edges
    for (int i = 0; i < words.length - 1; i++) {
        String w1 = words[i];
        String w2 = words[i + 1];

        if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

        int len = Math.min(w1.length(), w2.length());
        for (int j = 0; j < len; j++) {
            char c1 = w1.charAt(j);
            char c2 = w2.charAt(j);
            if (c1 != c2) {
                adj.get(c1).add(c2);
                inDegree.put(c2, inDegree.get(c2) + 1);
                break;
            }
        }
    }

    // Topological Sort (BFS)
    Queue<Character> q = new LinkedList<>();
    for (char c : inDegree.keySet()) {
        if (inDegree.get(c) == 0) q.offer(c);
    }

    StringBuilder order = new StringBuilder();
    while (!q.isEmpty()) {
        char c = q.poll();
        order.append(c);
        for (char neighbor : adj.get(c)) {
            inDegree.put(neighbor, inDegree.get(neighbor) - 1);
            if (inDegree.get(neighbor) == 0) q.offer(neighbor);
        }
    }

    return order.length() == adj.size() ? order.toString() : "";
}

    
}
