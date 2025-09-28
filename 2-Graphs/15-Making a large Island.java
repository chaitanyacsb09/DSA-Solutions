class Pair{
    int first, second;
    public Pair(int _first, int _second){
        first = _first;
        second = _second;
    }
}
class DisjointSet{
    int[] parent;
    int[] size;
    int biggestComponentSize = 0;
    public DisjointSet(int numNodes){
        parent = new int[numNodes];
        size = new int[numNodes];
        biggestComponentSize = 1;
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
            biggestComponentSize = Math.max(biggestComponentSize, size[vPar]);
        }
        else{
            parent[vPar] = uPar;
            size[uPar]+= size[vPar];
            biggestComponentSize = Math.max(biggestComponentSize, size[uPar]);
        }
    }

    public int getComponentSizeForMember(int memberPosition){
        int ultimateParent = findUParent(memberPosition);
        return size[ultimateParent];
    }

    public int getSizeOfBiggestComponent(){
        return biggestComponentSize;
    }
}

class Solution {
    int[] delRow = new int[]{0,1,0,-1}; 
    int[] delCol = new int[]{-1,0,1,0};
        
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        evalGridAndFillDS(grid, ds);

        int largestIslandSize = ds.getSizeOfBiggestComponent();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    continue;
                }
                int neighbouringIslandsSizeSum = 0;
                Set<Integer> uniqueParentsFromNeighbour = new HashSet<>();                
                //Check for nbrs
                for(int delPos =0; delPos < 4; delPos++){
                    int adjX = i + delRow[delPos], adjY = j + delCol[delPos];
                    if(!isIdxValid(adjX, adjY, n) || grid[adjX][adjY] == 0){
                        continue;
                    }
                    
                    int adjNodeDsPos = adjX * n + adjY;
                    int parent = ds.findUParent(adjNodeDsPos);
                    if(!uniqueParentsFromNeighbour.contains(parent)){
                        neighbouringIslandsSizeSum += ds.getComponentSizeForMember(adjNodeDsPos);
                        uniqueParentsFromNeighbour.add(parent);
                    }
                }

                largestIslandSize = Math.max(largestIslandSize, 1 + neighbouringIslandsSizeSum);                
            }
        }
        return largestIslandSize;
    }
    private void evalGridAndFillDS(int[][] grid, DisjointSet ds){
        int n = grid.length;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                int currNodeDsPos = i * n + j;
                //Check for nbrs
                for(int idx = 0; idx < 4; idx++){
                    int adjX = i + delRow[idx], adjY = j + delCol[idx];
                    if(!isIdxValid(adjX, adjY, n) || grid[adjX][adjY] == 0){
                        continue;
                    }
                    int adjNodeDsPos = adjX * n + adjY;
                    ds.unionBySize(currNodeDsPos, adjNodeDsPos);
                }
            }
        }
    }

    private boolean isIdxValid(int rowIdx, int colIdx, int dimension){
        return rowIdx < dimension && rowIdx >= 0 && colIdx < dimension && colIdx >= 0;
    }
}