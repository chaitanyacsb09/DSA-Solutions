class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(nums[mid] == target){
                return mid;
            }

            //Find the sorted half
            if(nums[mid] >= nums[left]){
                //Left half is sorted
                if(nums[left] <= target && target < nums[mid]){
                    //Target in left part
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
            else{
                //Right part is sorted
                if(nums[mid] < target && target <= nums[right]){
                    //Target in Right part
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;    
    }
}