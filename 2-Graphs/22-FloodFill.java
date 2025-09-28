class Solution {
    private int[][] adjDel = {{0,-1},{1,0},{0,1},{-1,0}};
    private static boolean isIdxValid(int rowIdx, int colIdx, int numRows, int numCols){
        return rowIdx < numRows && rowIdx >= 0 && colIdx < numCols && colIdx >= 0;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Queue<int[]> q = new LinkedList<>();
        int numRows = image.length, numCols = image[0].length;
        q.add(new int[]{sr,sc});
        int sColor = image[sr][sc];
        image[sr][sc] = color;

        //Edge Case
        if(sColor == color) return image;
        
        while(!q.isEmpty()){
            int[] front = q.poll();
            int row = front[0], col = front[1];

            for(int[] del : adjDel){
                int adjX = row + del[0], adjY = col + del[1];
                if(!isIdxValid(adjX, adjY, numRows, numCols) || image[adjX][adjY] != sColor){
                    continue;
                } 

                image[adjX][adjY] = color;
                q.add(new int[]{adjX, adjY});
            }
        }

        return image;
    }
}