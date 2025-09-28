class Solution {
    private boolean validateInputAndBuildGraph(List<List<Integer>> graph, String[] words){
        int numWords = words.length;
        for(int i = 0; i < 26; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < numWords - 1; i++){
            String curr = words[i], next = words[i+1];
            int minLen = Math.min(curr.length(), next.length());
            
            // Check prefix invalid order: if curr starts with next but is longer, return false
            if (curr.length() > next.length() && curr.startsWith(next)) {
                return false;
            }
            
            for(int idx = 0; idx < minLen; idx++){
                if(curr.charAt(idx) == next.charAt(idx)){
                    continue;
                }
                //Add edge for first not matching character, say 'xCurr' and 'xNext'
                //Therefore, 'xCurr' comes before 'xNext', in the alien dictionary
                graph.get(curr.charAt(idx) - 'a').add(next.charAt(idx) - 'a');
                break;
            }
        }
        return true;
    }
    private List<Integer> topoSort(int V, List<List<Integer>> graph) {
        // code here
        int[] inDegree = new int[V];
        for(List<Integer> nbrs : graph){
            for(int nbr: nbrs){
                inDegree[nbr]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        List<Integer> topoSort = new ArrayList<Integer>();
        for(int i = 0; i < V; i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            topoSort.add(node);
            for(int nbr : graph.get(node)){
                if(--inDegree[nbr] == 0){
                    q.add(nbr);
                }
            }
        }
        return topoSort;
    }    
    public String findOrder(String[] words) {
        //Prep Adjacency Matrix
        int numWords = words.length;
        boolean[] isCharPresent = new boolean[26];
        for(String word: words){
            for(int i= 0; i < word.length(); i++){
                isCharPresent[word.charAt(i) - 'a'] = true;
            }
        }
        List<List<Integer>> graph = new ArrayList<>();
        boolean isInputValid = validateInputAndBuildGraph(graph, words);
        if(isInputValid == false){
            return "";
        }
        List<Integer> topoSort = topoSort(26, graph);
        
        if(topoSort.size() != 26){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for(int numRep : topoSort){
            if(isCharPresent[numRep] == false) continue;
            sb.append((char)(numRep + 'a')); 
        }
        // System.out.println(sb.toString());
        return sb.toString();
    }
}