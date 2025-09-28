class Solution {
    private int[][] adjDel = {{0,-1},{1,0},{0,1},{-1,0}};
    private static boolean isIdxValid(int rowIdx, int colIdx, int numRows, int numCols){
        return rowIdx < numRows && rowIdx >= 0 && colIdx < numCols && colIdx >= 0;
    }
    private void walkLand(int row, int col, boolean[][] isVisited, int[][]grid){
        int numRows = grid.length, numCols = grid[0].length;
        isVisited[row][col] = true;

        for(int[] del: adjDel){
            int adjX = row + del[0], adjY = col + del[1];
            if(!isIdxValid(adjX, adjY, numRows, numCols) || isVisited[adjX][adjY] || grid[adjX][adjY] == 0){
                continue;
            }
            walkLand(adjX, adjY, isVisited, grid);
        }
    }
    public int numEnclaves(int[][] grid) {
        int numRows = grid.length, numCols = grid[0].length;
        boolean[][] isVisited = new boolean[numRows][numCols];

        for(int row = 0; row < numRows; row++){
            if(!isVisited[row][0] && grid[row][0] == 1){
                walkLand(row, 0, isVisited, grid);
            }
            if(!isVisited[row][numCols-1] && grid[row][numCols-1] == 1){
                walkLand(row, numCols-1, isVisited, grid);
            }
        }

        for(int col = 1; col < numCols - 1; col++){
            if(!isVisited[0][col] && grid[0][col] == 1){
                walkLand(0, col, isVisited, grid);
            }
            if(!isVisited[numRows-1][col] && grid[numRows-1][col] == 1){
                walkLand(numRows-1, col, isVisited, grid);
            }
        }

        int unexplorableLandCells = 0;
        for(int row = 1; row < numRows - 1; row++){
            for(int col = 1; col < numCols - 1; col++){
                if(grid[row][col] == 1 && !isVisited[row][col]){
                    unexplorableLandCells++;
                }
            }
        }
        return unexplorableLandCells; 
    }
}   