//TC: O(2N) SC: O(2N) | PrefixMax and SuffixMax Approach
class Solution {
    public int trap(int[] height) {
        boolean debugEnabled = false;
        int n = height.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        prefixMax[0] = -1;
        suffixMax[n-1] = -1;
        for(int i = 1; i < n; i++){
            prefixMax[i] = Math.max(prefixMax[i-1], height[i-1]);
            suffixMax[n-1-i] = Math.max(suffixMax[n-i], height[n-i]);
        }

        if(debugEnabled){
            System.out.print("PrefixMax: [");
            for(int num: prefixMax){
                System.out.print(num + ",");
            }
            System.out.println("]");

            System.out.print("SuffixMax: [");
            for(int num: suffixMax){
                System.out.print(num + ",");
            }
            System.out.println("]");
        }

        int trappedWater = 0;
        for(int i = 1; i <= n - 2; i++){
            int maxTrapElevation = Math.min(prefixMax[i], suffixMax[i]);
            if(debugEnabled){
                System.out.println("At Pos :" + (i + 1));
                System.out.println("\tPrefixMax is: " + prefixMax[i] + " | SuffixMax is: "+ suffixMax[i]);  
                System.out.println("\tMaxTrapElevation - height[i] = " + (maxTrapElevation - height[i]));       
            }
            if(maxTrapElevation - height[i] <= 0){
                continue;
            }
            trappedWater += (maxTrapElevation - height[i]);
        }
        return trappedWater;
    }
}