class Solution {
    private int[][] adjDel = {{0,-1},{1,0},{0,1},{-1,0}};
    private static boolean isIdxValid(int rowIdx, int colIdx, int numRows, int numCols){
        return rowIdx < numRows && rowIdx >= 0 && colIdx < numCols && colIdx >= 0;
    }

    public int orangesRotting(int[][] grid) {
        int minutesRequired = 0;
        Queue<int[]> q = new LinkedList<>();

        int numRows = grid.length, numCols = grid[0].length;
        int freshOrangesCount = 0;

        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                if(grid[row][col] == 2){
                    q.add(new int[]{row, col, 0}); //minutes to rot
                }
                if(grid[row][col] == 1){
                    freshOrangesCount++;
                }
            }
        }
        
        if(freshOrangesCount == 0) return 0;

        while(!q.isEmpty()){
            int[] front = q.poll();
            int row = front[0], col = front[1], minutesRequiredToRot = front[2];
            minutesRequired = Math.max(minutesRequired, minutesRequiredToRot);

            //Check for Fresh Oranges in Adjacents
            for(int[] del : adjDel){
                int adjX = row + del[0], adjY = col + del[1];
                if(!isIdxValid(adjX, adjY, numRows, numCols) || grid[adjX][adjY] != 1){
                    continue;
                } 

                //Found an adjacent fresh orange
                grid[adjX][adjY] = 2;
                q.add(new int[]{adjX, adjY, minutesRequiredToRot + 1});
                freshOrangesCount--;
            }
        }
        return freshOrangesCount == 0 ? minutesRequired : -1;
    }
}


//---------------------ALTERNATE OPTION FOR TRAVERSING ALL ROTTEN ORANGES AT SAME TIME--------------------
        while(!q.isEmpty()) {
            time++;
            int size = q.size();

            while(size-- > 0) {
                int[] rotten = q.poll();
                int i = rotten[0];
                int j = rotten[1];

                for(int[] route : routes) {
                    int nextI = i + route[0];
                    int nextJ = j + route[1];

                    if(isInBoundary(m,n,nextI, nextJ) && grid[nextI][nextJ] == 1) {
                        grid[nextI][nextJ] = 2;
                        fresh--;
                        q.add(new int[]{nextI, nextJ});
                    }
                }
                
            }
        }