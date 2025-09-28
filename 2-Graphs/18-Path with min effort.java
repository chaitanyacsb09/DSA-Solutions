class Solution {
    int[][] adjDel = {{0,-1},{1,0},{0,1},{-1,0}};
    private static boolean isIdxValid(int rowIdx, int colIdx, int numRows, int numCols){
        return rowIdx < numRows && rowIdx >= 0 && colIdx < numCols && colIdx >= 0;
    }

    public int minimumEffortPath(int[][] heights) {
        int numRows = heights.length;
        int numCols = heights[0].length;
        int[][] minEffort = new int[numRows][numCols];

        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                minEffort[i][j] = (int) 1e7;
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> Integer.compare(x[0],y[0]));
        pq.add(new int[]{0,0,0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currMinEffort = curr[0], row = curr[1], col = curr[2];
            if(row == numRows-1 && col == numCols-1){
                return currMinEffort;
            }
            for(int i = 0; i < 4; i++){
                int adjX = row + adjDel[i][0], adjY = col + adjDel[i][1];
                if(!isIdxValid(adjX, adjY, numRows, numCols)){
                    continue;
                }
                int newMinEffort = Math.max(
                    Math.abs(heights[row][col] - heights[adjX][adjY]),
                    currMinEffort
                    );

                if(newMinEffort < minEffort[adjX][adjY]){
                    minEffort[adjX][adjY] = newMinEffort;
                    pq.add(new int[]{newMinEffort, adjX, adjY});
                }
            }
        }
        return -1;
    }
}