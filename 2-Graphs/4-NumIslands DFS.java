class Solution {
    private int[] rowDisplacement = new int[]{0,-1,0,1};
    private int[] colDisplacement = new int[]{-1,0,1,0};
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        int islandCount = 0;
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(grid[r][c] == '1'){
                    islandCount++;
                    dfs(r, c, grid);
                }
            }
        }
        return islandCount;
    }
    private void dfs(int row, int col, char[][] grid){
        if(!checkXYValidity(row, col, grid) || grid[row][col] != '1'){
            return;
        }
        
        grid[row][col] = '*';
        for(int i = 0; i < 4; i++){
            int adjX = row + rowDisplacement[i], adjY = col + colDisplacement[i];
            dfs(adjX, adjY, grid);
        }
    }

    private boolean checkXYValidity(int x, int y, char[][]grid){
        return x < grid.length && y < grid[0].length && x >= 0 &&y >= 0;
    }
}