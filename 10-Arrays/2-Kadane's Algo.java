class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < n; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}

/*
What if all elements have -ve
The earlier method works, because, we are first comparing, 
if at any point currSum < 0 we reset to 0, but we first compare it with maxSum, so if all elements are negative, then, after each element curr sum == 0, 
therefore the maxSum == max(arr)
*/