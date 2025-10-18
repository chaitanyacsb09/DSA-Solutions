
class Solution {
    int[] delRow = new int[]{0,1,0,-1}; 
    int[] delCol = new int[]{-1,0,1,0};
    
    private int exploreIsland(int rowIdx, int colIdx, int islandId, int[][] grid){
        int n = grid.length;
        //Mark it with islandId, therefore visited tracking also not required
        grid[rowIdx][colIdx] = islandId;
        int islandSize = 1; //The current node
        
        for(int i = 0; i < 4; i++){
            int adjX = rowIdx + delRow[i], adjY = colIdx + delCol[i];
            if(!isIdxValid(adjX, adjY, n) || grid[adjX][adjY] != 1){
                continue;
            }
            islandSize += exploreIsland(adjX, adjY, islandId, grid); 
        }
        return islandSize;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[] islandSize = new int[n*n+2];
        int islandId = 2;
        for(int i = 0;i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] != 1){
                    continue;
                }
                int size = exploreIsland(i,j, islandId, grid);
                islandSize[islandId] = size;
                islandId++;
            }
        }
        
        if(islandId == 2){ //Only possible if there is no land in matrix, there one cell we could change
            return 1;
        }
        if(islandId == 3){ //If entire matrix is an island // or only one island was found
            return islandSize[2] == n * n ? islandSize[2] : islandSize[2] + 1;
        }
        int largestIslandSize = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] != 0){
                    continue;
                }
                int neighbouringIslandsSizeSum = 0;
                Set<Integer> uniqueNeighbourIslands = new HashSet<>();                
                //Check for nbrs
                for(int delPos =0; delPos < 4; delPos++){
                    int adjX = i + delRow[delPos], adjY = j + delCol[delPos];
                    if(!isIdxValid(adjX, adjY, n) || grid[adjX][adjY] == 0){
                        continue;
                    }
                    int neighbourIslandId = grid[adjX][adjY];
                    if(!uniqueNeighbourIslands.contains(neighbourIslandId)){
                        neighbouringIslandsSizeSum += islandSize[neighbourIslandId];
                        uniqueNeighbourIslands.add(neighbourIslandId);
                    }
                }
                largestIslandSize = Math.max(largestIslandSize, 1 + neighbouringIslandsSizeSum);                
            }
        }
        return largestIslandSize;
    }
    
    private boolean isIdxValid(int rowIdx, int colIdx, int dimension){
        return rowIdx < dimension && rowIdx >= 0 && colIdx < dimension && colIdx >= 0;
    }
}