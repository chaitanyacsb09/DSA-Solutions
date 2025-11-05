
//dp[i][j] - min path sum for reaching grid[n-1][m-1] from grid[i][j] (This path includes grid[i][j] value)
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
	    int [][] dp = new int[n+1][m+1];
        for(int rowIdx = 0; rowIdx <= n; rowIdx++) //base case for colIdx >= colLen
        {
            dp[rowIdx][m] = Integer.MAX_VALUE;
        }
        for(int colIdx = 0; colIdx <= m; colIdx++) //base case for rowIdx >= rowLen
        {
            dp[n][colIdx] = Integer.MAX_VALUE;
        }

        for(int i  = n - 1; i >= 0; i--){
            for(int j = m -1; j >= 0; j--){
                if(i == n-1 && j == m-1){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]); 
                }   
        }
        return dp[0][0];
    }

}

//Memoized
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i],-1);
        }
        return minPathSumUtil(0,0,grid, dp);
    }

    private int minPathSumUtil(int rowIdx, int colIdx, int[][] grid, int[][] dp) {
        if (rowIdx >= grid.length || colIdx >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if(rowIdx == grid.length - 1 && colIdx == grid[0].length -1){
            return dp[rowIdx][colIdx]=grid[rowIdx][colIdx];
        }

        if (dp[rowIdx][colIdx] != -1) {
            return dp[rowIdx][colIdx];
        }
        int right = minPathSumUtil(rowIdx, colIdx + 1, grid, dp);
        int down = minPathSumUtil(rowIdx + 1, colIdx, grid, dp);
        return dp[rowIdx][colIdx] = grid[rowIdx][colIdx] + Math.min(
                right,
                down);
    }
}
