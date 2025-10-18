class Solution {
    private int getMax(int[] nums){
        int maxEl = 0;
        for(int num: nums){
            maxEl = Math.max(maxEl, num);
        }
        return maxEl;
    }
    //Double casting of one of the bananas per hour is necessary, otherwise in division we won't be getting the values like 1.5, 0.6 etc
    private boolean canFinish(double bananasPerHour, int[] piles, int h){
        long requiredHours = 0;
        for(int pile: piles){
            double requiredHoursForCurrPile = Math.ceil(pile / bananasPerHour);
            requiredHours += (long) requiredHoursForCurrPile;
            if(requiredHours > h){
                return false;
            }
        }
        return true;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int maxEl = getMax(piles);
        
        //The optimal value for num banana's per hour will lie between 1 and max(piles[]), because h >= piles.length
        int left = 1, right = maxEl;
        
        int minSpeed = maxEl;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(canFinish(mid, piles, h)){
                minSpeed = mid;
                //Look for a min bananaPerHour Rate
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return minSpeed;
    }
}