class Solution {
    private boolean canWePlace(int requiredDistance, int[] stalls, int k){
        int prevCowPlacedStallPos = stalls[0];
        int cowsPlaced = 1;
        for(int i = 1; i < stalls.length; i++){
            int distanceBetweenCurrStallAndPrevCowPlacedStall = stalls[i] - prevCowPlacedStallPos; 
            if(distanceBetweenCurrStallAndPrevCowPlacedStall >= requiredDistance){
                prevCowPlacedStallPos = stalls[i];
                cowsPlaced++;
            }
            if(cowsPlaced == k){
                return true;
            }
        }
        return false;
    }
    public int aggressiveCows(int[] stalls, int k) {
        // code here
        Arrays.sort(stalls);
        int left = 1, right = stalls[stalls.length - 1] - stalls[0]; //This is the Max Distance
        int maxSeparationBetweenCows = 1;
        while(left <= right){
            int currDistance = left + (right - left) / 2;
            
            if(canWePlace(currDistance, stalls, k)){
                maxSeparationBetweenCows = currDistance;
                //If with currDistance placing the cows is possible, then with all left distances it will be possible to
                //Skip the left part, to find max min distance
                left = currDistance + 1;
            }
            else{
                right = currDistance - 1;
            }
        }
        
        //return right; works as we are moving the left boundary towards right, so at the end right will have the answer
        return maxSeparationBetweenCows;
    }
}