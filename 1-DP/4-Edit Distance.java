class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        if(word1.equals(word2)) return 0;
        if(word1.isBlank()){return m;}
        if(word2.isBlank()){return n;}

        //Initializations
        int[][] dp = new int[n+1][m+1];
        char[] s1 = word1.toCharArray(), s2 = word2.toCharArray();
        
        for(int i1 = 1; i1 <= n; i1++){
            dp[i1][0] = i1;
        }
        for(int i2 = 1; i2 <= m; i2++){
            dp[0][i2] = i2;
        }   
        for(int i1=1; i1 <= n; i1++){
            for(int i2 = 1; i2 <= m; i2++){
                if(s1[i1-1] == s2[i2-1]){
                    dp[i1][i2] = dp[i1 - 1][i2 - 1];
                }
                else{
                    int replacement = dp[i1 - 1][i2 - 1];
                    int insertion = dp[i1][i2 - 1];
                    int deletion = dp[i1 - 1][i2];
                    dp[i1][i2] = 1 + Math.min(replacement,
                                            Math.min(deletion, insertion)
                                        );
                }
            }
        }
       return dp[n][m];
    }
}
//Memoized Solution
class Solution {
    private int minDistanceUtil(int i1, int i2, char[] s1, char[] s2, int[][] dp){
        if(i1 < 0) return i2 +1;
        if(i2 < 0) return i1 + 1;
        
        if(dp[i1][i2] != -1){
            return dp[i1][i2];
        }

        //Character Match No Operation Required
        if(s1[i1] == s2[i2]){
            return dp[i1][i2] = minDistanceUtil(i1 - 1, i2 - 1, s1, s2, dp);
        }
        int replacement = minDistanceUtil(i1 - 1, i2 - 1, s1, s2, dp);
        int insertion = minDistanceUtil(i1, i2 - 1, s1, s2, dp);
        int deletion =  minDistanceUtil(i1 - 1, i2, s1, s2, dp);
        return dp[i1][i2] = 1 + Math.min(replacement,
                                    Math.min(insertion, deletion)
                                );
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return minDistanceUtil(word1.length() - 1, word2.length() - 1, word1.toCharArray(), word2.toCharArray(), dp);
    }
}