class Solution {
    private boolean canWeSplit(int requiredSum, int[] nums, int k){
        int currSplitSum = 0;
        int numSubArrays = 0;
        for(int num: nums){
            if(currSplitSum + num > requiredSum){
                numSubArrays++;
                currSplitSum = num;
                continue;
            }
            currSplitSum += num;
        }
        return numSubArrays + 1 <= k; //+1 for the last subarray
    }
    public int splitArray(int[] nums, int k) {

        if(k > nums.length){
            return -1;
        }

        int maxEl = 0;
        int sum = 0;
        for(int num: nums){
            maxEl = Math.max(maxEl, num);
            sum += num;
        }
        int left = maxEl, right = sum;
        int minimumLargestSumSplit = right;
        while(left <= right){
            int currLargestSumSplit = left + (right - left) / 2;

            if(canWeSplit(currLargestSumSplit, nums, k)){
                minimumLargestSumSplit = currLargestSumSplit;

                //Look For Smaller Sums
                right = currLargestSumSplit - 1;
            }
            else{
                left = currLargestSumSplit + 1;
            }
        }
        return minimumLargestSumSplit;
    }
}