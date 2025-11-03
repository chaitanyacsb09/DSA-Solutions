//DP
class Solution {
    private boolean isPalindrome(int l, int r, String s){
        while(l < r){
            if(s.charAt(l++) != s.charAt(r--)){
                return false;
            }
        }
        return true;
    }

    public int minCut(String s) {
        // code here
        // code here
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for(int i = 0; i < n; i++){
            isPalindrome[i][i] = true;
        }
        for(int len = 2; len <= n; len++){
            /*
            As j moves from [len-1 .... n-1]
               i goes from [0 .. n-1-(len-1)]
            */
            for(int i = 0, j = len - 1; j < n; i++, j++){
                if((s.charAt(i) == s.charAt(j)) && (len == 2 || isPalindrome[i+1][j-1])){
                    isPalindrome[i][j] = true;
                }
                else{
                    isPalindrome[i][j] = false;
                }
            }
        }
        int[] dp = new int[n+1];
        for(int i = n-1; i >= 0; i--){
            int ans = Integer.MAX_VALUE;
            for(int k = i; k < n; k++){
                if(!isPalindrome[i][k]){
                    continue;
                }
                int tempAns = 1 +  dp[k+1];
                ans = Math.min(ans, tempAns);
            }
            dp[i] = ans;
        }
        return dp[0] - 1; 
        
    }
}

//Memoized
class Solution {
    static private boolean isPalindrome(int l, int r, String s){
        while(l < r){
            if(s.charAt(l++) != s.charAt(r--)){
                return false;
            }
        }
        return true;
    }
    static private int palPartitionUtil(int start, String s, int[] memo){
        if(start >= s.length()){
            return 0;
        }
        
        if(memo[start] != -1){
            return memo[start];
        }
        
        int ans = Integer.MAX_VALUE;
        for(int k = start; k < s.length(); k++){
            if(!isPalindrome(start, k, s)){
                continue;
            }
            int tempAns = 1 +  palPartitionUtil(k+1,s, memo);
            ans = Math.min(ans, tempAns);
        }
        return memo[start] = ans;
    }
    static int palPartition(String s) {
        // code here
        int n = s.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        //Or in the base case return -1;
        return palPartitionUtil(0, s, memo) - 1; 
        
    }
}

//Memoized to return all palindrome partitioned strings
class Solution {
    List<List<String>> ans = new ArrayList<>();
    
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }
    
    private void partitionUtil(int start, String s, List<String> list) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String substr = s.substring(start, end);
            if (isPalindrome(substr)) {
                list.add(substr);
                partitionUtil(end, s, list);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public List<List<String>> partition(String s) {
        partitionUtil(0, s, new ArrayList<>());
        return ans;
    }
}
