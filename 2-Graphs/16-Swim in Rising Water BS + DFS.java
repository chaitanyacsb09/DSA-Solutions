class Solution {
    //L, D, R, U
    int[][] adjDel = {{0,-1},{1,0},{0,1},{-1,0}}; 
    private int getMaxElevation(int[][] grid){
        int n = grid.length;
        int maxElevation = Integer.MIN_VALUE;
        for(int i = 0; i< n; i++){
            for(int j = 0; j < n; j++){
                maxElevation = Math.max(maxElevation, grid[i][j]);
            }
        }
        return maxElevation;
    }
    private boolean isSwimPossible(int row, int col, int time, int[][] grid, boolean[][] isVisited){
        int n = grid.length; 
        if(!isIdxValid(row, col, n) || isVisited[row][col] || grid[row][col] > time){
                return false;
        }
        if(row == n-1 && col == n-1) return true;
        isVisited[row][col] = true;
        for(int i = 0; i < 4; i++){
            int adjX = row + adjDel[i][0], adjY = col + adjDel[i][1];
            if(isSwimPossible(adjX, adjY, time, grid, isVisited)){
                return true;
            }
        }
        return false;
    }
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int maxElevation = getMaxElevation(grid);
        int l = 0, r = maxElevation;

        while(l < r){
            int mid = l + (r-l)/2;
            if(isSwimPossible(0,0, mid, grid, new boolean[n][n])){ //If possible within mid, then will also be possible with (mid+1 .. r)
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean isIdxValid(int rowIdx, int colIdx, int dimension){
        return rowIdx < dimension && rowIdx >= 0 && colIdx < dimension && colIdx >= 0;
    }
}