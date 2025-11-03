//dp[i][j] -> min num operation required to convert w1[i…n-1] to w2[j…m-1]
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        //base conditions
        dp[n][m] = 0;

        //when word1 got exhausted
        for (int idx2 = 0; idx2 < m; idx2++) {
            dp[n][idx2] = m - idx2; // insert from w2
        }

        //when word2 got exhausted
        for (int idx1 = 0; idx1 < n; idx1++) {
            dp[idx1][m] = n - idx1; // delete chars from w1
        }

        for (int idx1 = n - 1; idx1 >= 0; idx1--) {
            for (int idx2 = m - 1; idx2 >= 0; idx2--) {
                if (word1.charAt(idx1) == word2.charAt(idx2)) {
                    dp[idx1][idx2] = dp[idx1 + 1][idx2 + 1];
                } else {
                    int replace = dp[idx1 + 1][idx2 + 1];
                    int insert = dp[idx1][idx2 + 1];
                    int delete = dp[idx1 + 1][idx2];
                    dp[idx1][idx2] = 1 + Math.min(
                        replace,
                        Math.min(insert, delete)
                    );
                }
            }
        }
        return dp[0][0];
    }
}