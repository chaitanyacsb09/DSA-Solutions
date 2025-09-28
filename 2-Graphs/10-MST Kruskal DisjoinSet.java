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
    public int spanningTree(int V, int[][] edges) {
        // code here
        DisjointSet ds = new DisjointSet(V);
        //Sort Edges by weight
        Arrays.sort(edges, (a,b) -> a[2] - b[2]);
        
        int mstSum = 0;
        
        for(int[] edge: edges){
            int u = edge[0],  v = edge[1], edgWt = edge[2];
            if(ds.findUParent(u) == ds.findUParent(v)){
                continue;
            }
            mstSum += edgWt;
            ds.unionBySize(u, v);
        }
        
        return mstSum;
    }
}
