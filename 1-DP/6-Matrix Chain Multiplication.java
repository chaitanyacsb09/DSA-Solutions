class Solution {
    static int matrixMultiplication(int arr[]) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n][n];
        //dp[i][j], where i == j already 0, hence base condition covered
        
        for(int i = n-1; i >= 1; i--){
            for(int j = i + 1; j < n; j++){
                int minOps = Integer.MAX_VALUE;
            	for(int k = i; k < j; k++){
            	    int currParRes = dp[i][k] + dp[k+1][j] +
            			    (arr[i-1] * arr[k] * arr[j]);
            	    minOps = Math.min(minOps, currParRes);
                }
                dp[i][j] = minOps;
            }
        }
        
        return dp[1][n-1];
    }
}
//Memoized
class Solution {
    static int matrixMultiplication(int arr[]) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        return mmUtil(1,n-1,arr,dp);
    }
    
    static int mmUtil(int i, int j, int[] arr, int[][]dp){
    	if(i == j) return 0;
    	if(dp[i][j] != -1) return dp[i][j];
    	int minOps = Integer.MAX_VALUE;
    	for(int k = i; k < j; k++){
    	    int currParRes = mmUtil(i,k,arr,dp) + mmUtil(k+1,j,arr, dp) +
    			    (arr[i-1] * arr[k] * arr[j]);
    	    minOps = Math.min(minOps, currParRes);
        }
        return dp[i][j] = minOps;
    }

}