// https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1

class Solution {
    public int maxSumIS(int arr[]) {
        int n = arr.length;
    	int dp[][] = new int[n+1][n+1]; //already all inits to zero
    	for(int idx = n -1; idx >= 0; idx--){
        	for(int prevIdx = idx - 1; prevIdx >= -1; prevIdx--){
            	int take = 0;
            	if(prevIdx == -1 || arr[prevIdx] < arr[idx]){
            	    take = arr[idx] + dp[idx+1][idx+1];
                }
                int notTake = dp[idx+1][prevIdx+1];
                dp[idx][prevIdx+1] = Math.max(take, notTake);
            }
        }
        return dp[0][0];
    }

}

//Space Optimized
class Solution {
    public int maxSumIS(int arr[]) {
        int n = arr.length;
    	int curr[] = new int[n+1]; //already all inits to zero
    	int next[] = new int[n+1];
    	for(int idx = n -1; idx >= 0; idx--){
        	for(int prevIdx = idx - 1; prevIdx >= -1; prevIdx--){
            	int take = 0;
            	if(prevIdx == -1 || arr[prevIdx] < arr[idx]){
            	    take = arr[idx] + next[idx+1];
                }
                int notTake = next[prevIdx+1];
                curr[prevIdx+1] = Math.max(take, notTake);
            }
            next = (int[]) (curr.clone());
        }
        return curr[0];
    }

}