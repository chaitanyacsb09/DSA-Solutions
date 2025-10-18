class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int min = 5001;
        while(left <= right){

            if(nums[left] <= nums[right]){
                return Math.min(min, nums[left]);
            }
            
            int mid = left + (right - left) / 2;

            if(nums[left] <= nums[mid]){
                //Left part is sorted
                min = Math.min(min, nums[left]);
                left = mid + 1;
            } 
            else{
                //Right part is sorted
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
        }
        return min;
    }
}