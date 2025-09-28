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
    public int makeConnected(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n);
        int extraCables = 0;        
        for(int[] connection: connections){
            int u = connection[0],  v = connection[1];
            if(ds.findUParent(u) == ds.findUParent(v)){
                extraCables++;
                continue;
            }
            ds.unionBySize(u, v);
        }

        int numComponents = 0;
        for(int i = 0; i < n; i++){
            if(ds.parent[i] == i) numComponents++;
        }
        int requiredCables = numComponents - 1;
        if(requiredCables > extraCables) return - 1;
        return requiredCables; 
    }
}