//TC: O(N)
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        
        if(nums[0] > nums[1]){
            return 0;
        }
        else if(nums[nums.length - 1] > nums[nums.length - 2]){
            return nums.length - 1;
        }

        int peakIdx = -1;
        for(int i = 1; i < nums.length - 1; i++){
            if(nums[i-1] < nums[i] && nums[i] > nums[i+1]){
                peakIdx = i;
            }
        }
        return peakIdx;
    }
}

//O(Log N)
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        
        if(nums[0] > nums[1]){
            return 0;
        }
        else if(nums[nums.length - 1] > nums[nums.length - 2]){
            return nums.length - 1;
        }

        int left = 1, right = nums.length - 2;
        while(left <= right){
            int mid = left + (right - left) / 2;

            //Peak Element found
            if(nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]){
                return mid;
            }

            if(nums[mid - 1] < nums[mid]){
                //In the left side of peak, hence move right towards peak
                left = mid + 1;
            }
            else{
                //In the right side of peak, hence move left towards peak
                right = mid - 1;
            }

        }
        return -1;
    }
}