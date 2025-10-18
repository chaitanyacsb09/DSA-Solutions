// User function Template for Java
class DisjointSet{
    int[] parent;
    int[] size;
    
    public DisjointSet(int numNodes){
        parent = new int[numNodes];
        size = new int[numNodes];
        for(int i = 0; i < numNodes; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    public int findUParent(int node){
        if(parent[node] == node){
            return node;
        }
        return parent[node] = findUParent(parent[node]);
    }
    
    public void unionBySize(int u, int v){
        int uPar = findUParent(u), vPar = findUParent(v);
        if(uPar == vPar) return;
        if(size[uPar] < size[vPar]){
            parent[uPar] = vPar;
            size[vPar]+= size[uPar];
        }
        else{
            parent[vPar] = uPar;
            size[uPar]+= size[vPar];
        }
    }
}

class Solution {

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        // Your code here
        DisjointSet ds = new DisjointSet(rows * cols);
        boolean[][] isVisited  = new boolean[rows][cols];
        List<Integer> queryResponse = new ArrayList<Integer>();
        
        //L, D, R, U
        int[] delRow = new int[]{0,1,0,-1}; 
        int[] delCol = new int[]{-1,0,1,0};
        
        int numIslands = 0;
        
        for(int[] operation : operators){
            int oprRow = operation[0], oprCol = operation[1];
            if(isVisited[oprRow][oprCol]){
                queryResponse.add(numIslands);
                continue;
            }
            
            isVisited[oprRow][oprCol] = true;
            numIslands++;
            
            //Check if island exists in neighbouring walls
            int numRepresentationOfOprCell = oprRow * cols + oprCol;
            for(int i = 0; i < 4; i++){
                int adjX = oprRow + delRow[i], adjY = oprCol + delCol[i];
                
                //If adj part of an island, then it would have been already marked true
                if(!isIdxValid(adjX, adjY, rows, cols) || !isVisited[adjX][adjY]){
                    continue;
                }
                
                int numRepresentationOfAdjCell = adjX * cols + adjY;
                
                if(ds.findUParent(numRepresentationOfOprCell) != ds.findUParent(numRepresentationOfAdjCell)){
                    numIslands--;
                    ds.unionBySize(numRepresentationOfOprCell, numRepresentationOfAdjCell);
                }
            }
            queryResponse.add(numIslands);
        }
        return queryResponse;
    }
    
    private boolean isIdxValid(int rowIdx, int colIdx, int numRows, int numCols){
        return rowIdx < numRows && rowIdx >= 0 && colIdx < numCols && colIdx >= 0;
    }
}