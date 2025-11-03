class Solution {
    private int lcsUtil(int i1, int i2, String s1, String s2, int[][] dp) {
        //Base Condition
        if (i1 >= s1.length() || i2 >= s2.length()) {
            return 0;
        }
        if (dp[i1][i2] != -1) {
            return dp[i1][i2];
        }
        int take = 0, notTake = 0;
        if (s1.charAt(i1) == s2.charAt(i2)) {
            take = 1 + lcsUtil(i1 + 1, i2 + 1, s1, s2, dp);
        } else {
            notTake = Math.max(lcsUtil(i1 + 1, i2, s1, s2, dp),
                    lcsUtil(i1, i2 + 1, s1, s2, dp));
        }
        return dp[i1][i2] = Math.max(take, notTake);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int t1Len = text1.length(), t2Len = text2.length();
        int[][] dp = new int[t1Len][t2Len];
        for (int i = 0; i < t1Len; i++) {
            Arrays.fill(dp[i], -1);
        }
        return lcsUtil(0, 0, text1, text2, dp);
    }
}