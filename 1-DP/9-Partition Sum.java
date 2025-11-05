//dp[i][s] : if its possible to get a subset from nums[i...n-1] s.t its sum is s 
class Solution {
    private int calcSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public boolean canPartition(int[] nums) {
        int sum = calcSum(nums);
        if (sum % 2 != 0)
            return false;

        int n = nums.length;
        boolean[][] dp = new boolean[n][(sum / 2) + 1];
        //Initialization for when idx == n-1
        for (int s = 1; s <= sum / 2; s++) {
            dp[n - 1][s] = nums[n - 1] == s;
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = true; //Sum 0 always possible
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int s = 1; s <= sum / 2; s++) {
                boolean take = false;
                if (nums[i] <= s) {
                    take = dp[i + 1][s - nums[i]];
                }
                boolean notTake = dp[i + 1][s];

                dp[i][s] = take || notTake;
            }
        }
        return dp[0][sum / 2];
    }
}

//Space Optimized
class Solution {
    private int calcSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public boolean canPartition(int[] nums) {
        int sum = calcSum(nums);
        if (sum % 2 != 0)
            return false;

        int n = nums.length;
        // boolean[][] dp = new boolean[n][(sum / 2) + 1];
        boolean[] next = new boolean[sum/2 + 1], curr = new boolean[sum/2 + 1];
        //Initialization for when idx == n-1
        for (int s = 1; s <= sum / 2; s++) {
            next[s] = nums[n - 1] == s;
        }
        next[0] = true; curr[0] = true;

        for (int i = n - 2; i >= 0; i--) {
            for (int s = 1; s <= sum / 2; s++) {
                boolean take = false;
                if (nums[i] <= s) {
                    take = next[s - nums[i]];
                }
                boolean notTake = next[s];

                curr[s] = take || notTake;
            }
            next = curr;
            curr = new boolean[sum/2+1];
        }
        return next[sum / 2];
    }
}