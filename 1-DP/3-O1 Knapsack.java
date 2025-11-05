//TC: O(N*W) | SC: O(N*W) -> Could be reduced to O(W)
class Solution {
    static int knapsack(int W, int val[], int wt[]) {
        // code here
        int n = val.length;
        int[][] dp = new int[n+1][W+1];
        for(int idx = n - 1; idx >= 0; idx--){
            for(int cap = 0; cap <= W; cap++){
            	int take = 0;
            	int notTake = dp[idx+1][cap];
            	if(wt[idx] <= cap){
            	    take = val[idx] + dp[idx + 1][cap - wt[idx]];
                }
            dp[idx][cap] = Math.max(take, notTake);
        }
                    }
        return dp[0][W];
    }
}

//Memoized Solution: Same TC, SC: O(N) + O(N*W) : Recursive Stack + Memo
class Solution {
    public int knapsack(int W, int val[], int wt[]) {
        // code here
        int n = val.length;
        int[][] memo = new int[n][W + 1];
        for (int row[]: memo) Arrays.fill(row, -1);
        return knapsack(0, W, val, wt, memo);
    }
    private int knapsack(int idx, int capacity, int[] val, int[] wt, int[][] memo) {
        if (idx == val.length) return 0;

        if (memo[idx][capacity] != -1) {
            return memo[idx][capacity];
        }
        int take = 0, notTake = 0;
        notTake = knapsack(idx + 1, capacity, val, wt, memo);
        if (wt[idx] <= capacity) {
            take = val[idx] + knapsack(idx + 1, capacity - wt[idx], val, wt, memo);
        }
        return memo[idx][capacity] = Math.max(take, notTake);
    }

}