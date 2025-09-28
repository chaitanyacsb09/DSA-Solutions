class Solution {
    private int atMost(int[] nums, int k){
        int[] freqCount = new int[2];
        int subArrCount = 0, left = 0;

        for(int right = 0; right < nums.length; right++){
            int key = nums[right] % 2;
            freqCount[key]++;

            while(freqCount[1] > k){
                freqCount[nums[left++] % 2]--;
            }

            subArrCount += right - left + 1; 
        }
        return subArrCount;
    }
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k-1);        
    }
}