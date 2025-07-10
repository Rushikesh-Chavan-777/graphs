import java.util.Arrays;

public class findcheapestflights {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[][] prices = new int[n][k + 2];
    for (int[] row : prices) Arrays.fill(row, Integer.MAX_VALUE);
    prices[src][0] = 0;

    for (int i = 1; i <= k + 1; i++) {
        for (int j = 0; j < n; j++) {
            prices[j][i] = prices[j][i - 1];
        }
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], cost = flight[2];
            if (prices[u][i - 1] != Integer.MAX_VALUE) {
                prices[v][i] = Math.min(prices[v][i], prices[u][i - 1] + cost);
            }
        }
    }

    return prices[dst][k + 1] == Integer.MAX_VALUE ? -1 : prices[dst][k + 1];
}

    
}
