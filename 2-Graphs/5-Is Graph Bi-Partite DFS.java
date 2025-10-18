class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        for(int i = 0; i < graph.length; i++){
            if(color[i] == -1 && !isBipartiteDfsUtil(i, 0, color, graph)){
                return false;
            }
        }
        return true;
    }
                        
    private boolean isBipartiteDfsUtil(int node, int col, int[] color, int[][] graph){
        color[node] = col;

        for(int nbr : graph[node] ){
            if(color[nbr] == -1 && !isBipartiteDfsUtil(nbr, 1-col, color, graph)){
                return false;
            }
            else if(color[nbr] == col){
                return false;
            }
        }
        return true;
    }
}