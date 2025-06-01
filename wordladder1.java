import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class wordladder1 {
        // now, we shall solve the famous word ladder 1 problem
    public static int WordLadderLength(String startword, String targetword, String[] wordlist) {
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < wordlist.length; i++) {
            set.add(wordlist[i]);
        }

        Queue<PairWordLadder> q = new LinkedList<>();
        q.offer(new PairWordLadder(startword, 1));
        set.remove(startword);
        while (!q.isEmpty()) {
            String worder = q.peek().first;
            int level = q.peek().second;
            q.poll();
            if (worder == targetword)
                return level;
            int len = worder.length();
            for (int i = 0; i < len; i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    char[] array = worder.toCharArray();
                    array[i] = j;
                    if (set.contains(array.toString())) {
                        set.remove(array.toString());
                        q.add(new PairWordLadder(array.toString(), level + 1));
                    }

                }
            }
        }
        return 0;
    }
    
}
