// Firefighter.java
public class Firefighter {
    /*@ requires t >= 0 && dimensions != null && grids != null && dimensions.length == t * 2 
      @    && grids.length == t;
      @ ensures \result != null && \result.length == t;
      @ ensures (\forall int i; 0 <= i && i < t; \result[i] >= 0);
      @*/
    public static int[] maxPeopleSaved(int t, int[] dimensions, int[][][] grids) {
        int[] results = new int[t];
        for (int i = 0; i < t; i++) {
            int m = dimensions[2 * i];
            int n = dimensions[2 * i + 1];
            results[i] = solve(m, n, grids[i]);
        }
        return results;
    }

    private static int solve(int m, int n, int[][] grid) {
        if (grid[0][0] == -1) return 0;

        // dp[i][j][dir] : max personnes sauvées en (i,j) avec direction (0: droite, 1: gauche)
        int[][][] dp = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = dp[i][j][1] = -1; // -1 signifie non calculé
            }
        }
        return Math.max(dp(m, n, grid, dp, 0, 0, 0), dp(m, n, grid, dp, 0, 0, 1));
    }

    private static int dp(int m, int n, int[][] grid, int[][][] dp, int i, int j, int dir) {
        if (i >= m || j < 0 || j >= n || grid[i][j] == -1) return Integer.MIN_VALUE;
        if (dp[i][j][dir] != -1) return dp[i][j][dir];

        int people = grid[i][j];

        int max;
        if (dir == 0) { // Droite
            int right = dp(m, n, grid, dp, i, j + 1, 0);
            int downLeft = dp(m, n, grid, dp, i + 1, j, 1);
            max = people + Math.max(right, downLeft);
        } else { // Gauche
            int left = dp(m, n, grid, dp, i, j - 1, 1);
            int downRight = dp(m, n, grid, dp, i + 1, j, 0);
            max = people + Math.max(left, downRight);
        }
        dp[i][j][dir] = max == people + Integer.MIN_VALUE ? people : max;
        return dp[i][j][dir];
    }
}