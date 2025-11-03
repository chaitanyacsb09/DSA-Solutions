//DP Tabulation: O(N^2) Space: O(N^2) : Can be reduced to O(N)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n+1]; //already set to 0, base case is covered
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) {
                int take = 0, notTake = 0;

                notTake = dp[idx + 1][prevIdx + 1];
                if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
                    take = 1 + dp[idx + 1][idx + 1];//dp[idx + 1][idx] => but for prevIdx(+1)
                }

                dp[idx][prevIdx + 1] = Math.max(take, notTake);
            }
        }
        return dp[0][0];
    }
}

/*
DRY RUN FOR TABULATION:
	   0 1 2 3 	
    nums : [3,7,2,8] => 3  | n =4
        -1 0 1 2 3 (prevIdx)	
        0 1 2 3 4 (prevIdx + 1)
    0:[3,0,0,0,0]
    1:[2,2,0,0,0]
    2:[2,1,1,0,0]
    3:[1,1,1,1,0]
    4:[0,0,0,0,0]

    idx = 0, prevIdx = -1 || nums[idx] = 7, nums[prevIdx] = 0  || dp[idx][prevIdx + 1] = Math.max(take,notTake)
    notTake = dp[idx+1][prevIdx +1] = dp[1][0] =2
    take = 1 + dp[idx + 1][idx + 1] = 1 + dp[1][1] = 1 + 2
*/

//Memoization
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return lengthOfLIS(0, -1, nums, memo);
    }

    private int lengthOfLIS(int currIdx, int prevIdx, int[] nums, int[][] memo) {
        if (currIdx == nums.length) {
            return 0;
        }
        if (memo[currIdx][prevIdx + 1] != -1) {
            return memo[currIdx][prevIdx + 1];
        }
        int take = 0, notTake = 0;
        if (prevIdx == -1 || nums[currIdx] > nums[prevIdx]) {
            take = 1 + lengthOfLIS(currIdx + 1, currIdx, nums, memo);
        }
        notTake = lengthOfLIS(currIdx + 1, prevIdx, nums, memo);

        return memo[currIdx][prevIdx + 1] = Math.max(take, notTake);
    }
}
