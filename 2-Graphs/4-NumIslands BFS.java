class Pair{
    int x, y;
    Pair(int _x, int _y){
        x = _x;
        y = _y;
    }
}
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
                    bfs(r, c, grid);
                }
            }
        }
        return islandCount;
    }
    private void bfs(int row, int col, char[][] grid){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        grid[row][col] = '*';
        while(!q.isEmpty()){
            int currX = q.peek().x, currY = q.peek().y;
            q.remove();
            for(int i = 0; i < 4; i++){
                int adjX = currX + rowDisplacement[i], adjY = currY + colDisplacement[i];
                if(!checkXYValidity(adjX, adjY, grid) || grid[adjX][adjY] != '1'){
                    continue;
                }
                grid[adjX][adjY] = '*';
                q.add(new Pair(adjX, adjY));
            }
        }
    }

    private boolean checkXYValidity(int x, int y, char[][]grid){
        return x < grid.length && y < grid[0].length && x >= 0 &&y >= 0;
    }
}