class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = IntStream.range(0, numCourses)
                                        .mapToObj(i -> new ArrayList<Integer>())
                                        .collect(Collectors.toList());
        int[] inDegree = new int[numCourses];
            for (int i = 0; i < prerequisites.length; i++) {
                int from = prerequisites[i][0];
                int to = prerequisites[i][1];

                adjList.get(from).add(to);
                inDegree[to]++;
            }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i < numCourses; i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }
    List<Integer> topoSort = new ArrayList<>();
    while(!q.isEmpty()){
        int c = q.poll();
        topoSort.add(c);
        for(int nbr : adjList.get(c)){
            if(--inDegree[nbr] == 0){
                q.add(nbr);
            }
        }
    }
    return topoSort.size() == numCourses;
}
}
