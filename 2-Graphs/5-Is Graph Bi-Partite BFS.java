class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        for(int i = 0; i < graph.length; i++){
            if(color[i] == -1 && !bfsStatus(i, 0, color, graph)){
                return false;
            }
        }
        return true;
    }
                        
    private boolean bfsStatus(int node, int col, int[] color, int[][] graph){
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = col;

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int nbr : graph[curr]){
                if(color[nbr] == -1){
                    q.add(nbr);
                    color[nbr] = 1 - color[curr];
                }
                else if(color[nbr] == color[curr]){
                    return false;
                }
            }
        }
        return true;
    }
}