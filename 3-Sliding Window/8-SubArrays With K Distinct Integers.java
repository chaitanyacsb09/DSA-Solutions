class Solution {
    private int atMost(int[] nums, int k) {
        int[] intCount = new int[nums.length + 1];
        int left = 0;
        int numDistincts = 0, numSubArrays = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int rNum = nums[right];
            if(intCount[rNum]++ == 0){
                numDistincts++;
            }

            // Shrink window if distinct chars exceed k
            while (numDistincts > k) {
                int lNum = nums[left];
                if (--intCount[lNum] == 0) {
                    numDistincts--;
                }
                left++;
            }
            numSubArrays += right - left + 1;
        }
        return numSubArrays;
    }
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k-1);
    }
}