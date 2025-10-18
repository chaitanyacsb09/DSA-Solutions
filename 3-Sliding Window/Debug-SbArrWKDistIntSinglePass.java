//UPDATED WITH DRY RUN, CHECK FOR FAILING TEST CASE WHAT'S GOING WRONG
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int[] numCount = new int[nums.length + 1];
        int numDistinct = 0, left = 0, numDuplicatesFromStartInWindow = 0;
        int subArraysCount = 0;
        
        for(int right = 0; right < nums.length; right++){
            // System.out.println("At Step " + (right+1));
            // System.out.print("\tWindow: [");
            for(int i = left; i <= right; i++) System.out.print(nums[i] + ",");
            // System.out.println("]");
            // System.out.println("\tl=" + left + ", r=" + right + ", numDistincts=" + numDistinct + ", subArraysCount=" + subArraysCount + " WindowDuplicates=" + numDuplicatesFromStartInWindow);

            int numR = nums[right];            
            if(numCount[numR]++ == 0){
                numDistinct++;
                // System.out.println("\tNum Distinct Updated: " + numDistinct);
            }
            if(numCount[numR] > 1){
                numDuplicatesFromStartInWindow++;
            }
            while(numDistinct > k){
                numCount[nums[left]]--;
                if(numCount[nums[left]] == 0)
                    numDistinct--;
                if(numCount[nums[left]] == 1){
                    numDuplicatesFromStartInWindow--;

                }
                ++left;
                // System.out.println("\tPost if block params, numDistinct: " + numDistinct + " left: "+ left);
            }

            // while(left < right && numCount[nums[left]] > 1){
            //     numCount[nums[left]]--;
            //     numDuplicatesFromStartInWindow++;
            //     System.out.println("\tWL Post Processing, WindowDuplicates: " + numDuplicatesFromStartInWindow + " Count of " + nums[left] + ": " + numCount[nums[left]]);
            //     ++left;
            // }
            
            if(numDistinct == k){
                subArraysCount += numDuplicatesFromStartInWindow + 1;
                // System.out.println("\tIF2, SubarrayCount: " + subArraysCount);
            }            
        }

        return subArraysCount;
    }
}

//VERSION 1
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int[] numCount = new int[nums.length + 1];
        int numDistinct = 0, left = 0, numDuplicatesFromStartInWindow = 0;
        int subArraysCount = 0;
        
        for(int right = 0; right < nums.length; right++){
            System.out.println("At Step " + (right+1));
            System.out.print("\tWindow: [");
            for(int i = left; i <= right; i++) System.out.print(nums[i] + ",");
            System.out.println("]");
            System.out.println("\tl=" + left + ", r=" + right + ", numDistincts=" + numDistinct + ", subArraysCount=" + subArraysCount + " WindowDuplicates=" + numDuplicatesFromStartInWindow);

            int numR = nums[right];            
            if(numCount[numR]++ == 0){
                numDistinct++;
                System.out.println("\tNum Distinct Updated: " + numDistinct);
            }

            if(numDistinct > k){
                numCount[nums[left]]--;
                numDistinct--;
                ++left;
                numDuplicatesFromStartInWindow = 0;
                System.out.println("\tPost if block params, numDistinct: " + numDistinct + " left: "+ left);
            }

            while(left < right && numCount[nums[left]] > 1){
                numCount[nums[left]]--;
                numDuplicatesFromStartInWindow++;
                System.out.println("\tWL Post Processing, WindowDuplicates: " + numDuplicatesFromStartInWindow + " Count of " + nums[left] + ": " + numCount[nums[left]]);
                ++left;
            }
            
            if(numDistinct == k){
                subArraysCount += numDuplicatesFromStartInWindow + 1;
                System.out.println("\tIF2, SubarrayCount: " + subArraysCount);
            }            
        }

        return subArraysCount;
    }
}