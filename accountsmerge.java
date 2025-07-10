import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class accountsmerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        // Step 1: Initialize Union-Find structure
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                parent.putIfAbsent(email, email);
                union(parent, account.get(1), email);
            }
        }

        // Step 2: Group emails by root parent
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            unions.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
        }

        // Step 3: Format the result
        List<List<String>> result = new ArrayList<>();
        for (String root : unions.keySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(root)); // Name
            merged.addAll(unions.get(root));   // Sorted emails
            result.add(merged);
        }

        return result;
    }

    private String find(Map<String, String> parent, String email) {
        if (!parent.get(email).equals(email)) {
            parent.put(email, find(parent, parent.get(email)));
        }
        return parent.get(email);
    }

    private void union(Map<String, String> parent, String email1, String email2) {
        String root1 = find(parent, email1);
        String root2 = find(parent, email2);
        if (!root1.equals(root2)) {
            parent.put(root2, root1);
        }
    }
    
}
