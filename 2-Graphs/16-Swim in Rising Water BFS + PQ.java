class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] isVisited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        pq.add(new int[]{grid[0][0], 0,0});
        isVisited[0][0] = true;

        //L, D, R, U
        int[][] adjDel = {{0,-1},{1,0},{0,1},{-1,0}}; 

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currMinTimeRequired = curr[0];
            int row  = curr[1], col = curr[2];

            if(row == n-1 && col == n-1) return currMinTimeRequired;

            //Check for nbrs:
            for(int i = 0; i < 4; i++){
                int adjX = row + adjDel[i][0], adjY = col + adjDel[i][1];
                if(!isIdxValid(adjX, adjY, n) || isVisited[adjX][adjY]){
                    continue;
                }
                isVisited[adjX][adjY] = true;
                pq.add(new int[]{
                        Math.max(grid[adjX][adjY], currMinTimeRequired), adjX, adjY
                    });
            }
        }

        return -1;
    }

    private boolean isIdxValid(int rowIdx, int colIdx, int dimension){
        return rowIdx < dimension && rowIdx >= 0 && colIdx < dimension && colIdx >= 0;
    }
}