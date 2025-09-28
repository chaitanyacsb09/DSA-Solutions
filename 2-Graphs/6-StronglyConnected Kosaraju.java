class Solution {
    Stack<Integer> st = new Stack<Integer>();
    boolean[] isVisited;
    private void evalFinishingTimes(int node, ArrayList<ArrayList<Integer>> adj){
        isVisited[node] = true;
        for(int nbr : adj.get(node)){
            if(!isVisited[nbr]){
                evalFinishingTimes(nbr, adj);
            }
        }
        st.push(node);
    }
    
    private void dfs(int node, ArrayList<ArrayList<Integer>> adj){
        isVisited[node] = true;
        for(int nbr : adj.get(node)){
            if(!isVisited[nbr]){
                dfs(nbr, adj);
            }
        }
    }
    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int numNodes = adj.size();
        isVisited = new boolean[numNodes];
        for(int i = 0; i < numNodes; i++){
            if(!isVisited[i]){
                evalFinishingTimes(i,adj);
            }
        }
        
        //Reverse Edges
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();
        for(int i = 0; i < numNodes; i++){
            revAdj.add(new ArrayList<>());
        }
        for(int i = 0; i < numNodes; i++){
            isVisited[i] = false;
            for(int nbr: adj.get(i)){
                revAdj.get(nbr).add(i);
            }
        }
        
        int numScc = 0;
        while(!st.isEmpty()){
            int node = st.peek();
            st.pop();
            if(!isVisited[node]){
                dfs(node, revAdj);
                numScc++;
            }
        }
        return numScc;
    }
}