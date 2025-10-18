
class Solution {
    //8-Directional : 4 Edges + 4 Corners
    int[][] adjDel = {{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1}};

    private boolean isIdxValid(int rowIdx, int colIdx, int dimension){
        return rowIdx < dimension && rowIdx >= 0 && colIdx < dimension && colIdx >= 0;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        //Edge Case
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        Queue<int[]> pq = new LinkedList<>();
        boolean[][] isVisited = new boolean[n][n];
        pq.add(new int[]{1,0,0}); //dist, row, col
        isVisited[0][0] = true;

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int dist = curr[0], row = curr[1], col = curr[2];
            if(row == n-1 && col == n-1){
                return dist;
            }

            //Check for nbrs
            for(int i = 0; i < 8; i++){
                int adjX = row + adjDel[i][0], adjY = col + adjDel[i][1];
                if(!isIdxValid(adjX, adjY, n) || isVisited[adjX][adjY] || grid[adjX][adjY] != 0){
                    continue;
                }
                pq.add(new int[]{1 + dist, adjX, adjY});
                isVisited[adjX][adjY] = true;
            }
        }

        return -1;
    }
}