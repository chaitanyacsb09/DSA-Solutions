//Space Optimized
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.equals(text2))return text1.length();    
        int t1Len = text1.length(), t2Len = text2.length();

        char[] c1 = text1.toCharArray(), c2 = text2.toCharArray();
        // int[][] dp = new int[t1Len + 1][t2Len + 1]; //Base Condition Covered, Init with zeroes
        int[] curr = new int[t2Len + 1], next = new int[t2Len + 1];
        for (int i1 = t1Len - 1; i1 >= 0; i1--) {
            for (int i2 = t2Len - 1; i2 >= 0; i2--) {
                if (c1[i1] == c2[i2]) {
                    curr[i2] = 1 + next[i2 + 1];
                } else {
                    curr[i2] = Math.max(next[i2], curr[i2 + 1]);
                }
            }
            next = (int[])(curr.clone());
        }
        return curr[0];
    }

}

public int longestCommonSubsequence(String text1, String text2) {
        if(text1.equals(text2))return text1.length();    
        int t1Len = text1.length(), t2Len = text2.length();

        char[] c1 = text1.toCharArray(), c2 = text2.toCharArray();
        int[][] dp = new int[t1Len + 1][t2Len + 1]; //Base Condition Covered, Init with zeroes
        for (int i1 = t1Len - 1; i1 >= 0; i1--) {
            for (int i2 = t2Len - 1; i2 >= 0; i2--) {
                int take = 0, notTake = 0;
                if (c1[i1] == c2[i2]) {
                    take = 1 + dp[i1 + 1][i2 + 1];
                } else {
                    notTake = Math.max(dp[i1 + 1][i2], dp[i1][i2 + 1]);
                }
                dp[i1][i2] = Math.max(take, notTake);
            }
        }
        return dp[0][0];
    }