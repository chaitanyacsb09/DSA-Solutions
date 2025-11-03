
//DP
class Solution {
   public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];

        dp[n] = true; //base case;
        for(int start = n - 1; start >= 0; start--){
            for(String word: wordDict){
                int wordLen = word.length();
                int endIdx = start + wordLen - 1;
                if(endIdx >= n) continue;

                String brokenWord = s.substring(start, endIdx + 1); 
                if(wordSet.contains(brokenWord) && word.equals(brokenWord) && dp[endIdx + 1]){
                    dp[start] = true;
                    break;
                }
            }
        }
        return dp[0];
    }   
}

//Better DP
class Solution {
   public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        dp[n] = true; //base case;
        for(int start = n - 1; start >= 0; start--){
            for(String word: wordDict){
                int wordLen = word.length();
                int endIdx = start + wordLen - 1;
                if(endIdx >= n) continue;

                boolean isSubstrEqualToWord = s.startsWith(word, start); 
                if(isSubstrEqualToWord && dp[endIdx + 1]){
                    dp[start] = true;
                    break;
                }
            }
        }
        return dp[0];
    }   
}

//Memoized
class Solution {
   public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict);
    Boolean[] memo = new Boolean[s.length()];
    return canBreak(s, 0, wordSet, memo);
}

private boolean canBreak(String s, int start, Set<String> wordSet, Boolean[] memo) {
    if (start == s.length()) return true;
    if (memo[start] != null) return memo[start];
    for (int end = start + 1; end <= s.length(); end++) {
        if (wordSet.contains(s.substring(start, end)) && canBreak(s, end, wordSet, memo)) {
            memo[start] = true;
            return true;
        }
    }
    memo[start] = false;
    return false;
}
}