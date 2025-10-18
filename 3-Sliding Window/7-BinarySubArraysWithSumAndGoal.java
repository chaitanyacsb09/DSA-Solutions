class Solution {
    private int atMost(int[] nums, int goal){
        if(goal < 0) return 0;

        int left = 0;
        int numSubArrays = 0;
        int currSum = 0;
        
        for (int right = 0; right < nums.length; right++) {
            currSum += nums[right];
            // <= required < not required : [0,0,0,0,0,0,1,0,0,0] and goal = 0, when right at idx with val 1, then with
            // left < right : does not go into while block : now left == right : right - left + 1 : +1 : incorrect addition to the answer
            while (left <= right && currSum > goal) {
                currSum -= nums[left];
                left++;
            }
            numSubArrays += right - left + 1;
        }
        return numSubArrays;
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }
}


//PREFIX SUM APPROACH EXISTS