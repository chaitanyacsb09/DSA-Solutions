class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        System.arraycopy(cuts, 0, newCuts, 1, m);
        newCuts[m + 1] = n;
        Arrays.sort(newCuts);
        
        int[][] dp = new int[m + 2][m + 2];
        
        for (int diff = 2; diff <= m + 1; ++diff) {
            for (int left = 0; left <= (m + 1) - diff; ++left) {
                int right = left + diff;
                int ans = Integer.MAX_VALUE;
                for (int cutIdx = left + 1; cutIdx < right; ++cutIdx) {
                    ans = Math.min(ans, dp[left][cutIdx] + dp[cutIdx][right] + newCuts[right] - newCuts[left]);
                }
                dp[left][right] = ans;
            }
        } 
        
        return dp[0][m + 1];
    }    
}

//Memoized
class Solution {
    private int minCostUtil(int start, int end, int[] cuts, int[][] dp){
        if(start + 1 == end){
            return 0; //No possible cuts further
            //Even if we don't handle here, then below before returning we have to check for minCost == MAX, if yes return 0;
        }
        if(dp[start][end] != -1){
            return dp[start][end];
        }
        int minCost = Integer.MAX_VALUE;
        int rodLen = (cuts[end]-cuts[start]);
        for(int cut = start + 1; cut < end; cut++){
                int cutCost = rodLen + minCostUtil(start, cut, cuts,dp) + minCostUtil(cut, end, cuts, dp);
                minCost = Math.min(minCost, cutCost);
        }
        return dp[start][end] = minCost == Integer.MAX_VALUE ? 0 : minCost;
    }
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] newCuts = new int[m+2];
        System.arraycopy(cuts, 0, newCuts, 1, m); //Src, srcPos, Dst, DstPos, numberOfEl to copy from src to dst
        newCuts[0] = 0; newCuts[m+1] = n; //Added 2 boundary edges, navigate throught cuts, as storing them as states for dp
        Arrays.sort(newCuts); //Sort the array, so that cuts could be explored efficiently
        int[][] dp = new int[m+2][m+2];
        for(int i = 0; i <= m+1; i++){
            Arrays.fill(dp[i],-1);
        }
        return minCostUtil(0,m+1, newCuts, dp);
    }
}