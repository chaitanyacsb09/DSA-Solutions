class Solution {
    public int longestOnes(int[] nums, int k) {
        int maxLen = 0;
        for(int left = 0, right = 0; right < nums.length; right++){
            //Flip the number to 1
            if(nums[right] == 0) k--;
            
            //If Num 0's are more than k in current window shrink the window
            while(k < 0){
                if(nums[left] == 0) k++;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}