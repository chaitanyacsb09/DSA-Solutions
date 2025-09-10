class Solution {
   private boolean isCyclePresent(int node, int[] visited, List<List<Integer>> adjList){
	visited[node] = 2; //to denote, visited in same path
	int numCourses = adjList.size();
    for(int nbr: adjList.get(node)){
        if(visited[nbr] == 2) return true; //Visited twice in a path hence cycle

        if(visited[nbr] == 0 && isCyclePresent(nbr, visited, adjList)){
            return true;
        }
    }
    visited[node] = 1;
    return false;
}

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = IntStream.range(0, numCourses)
                                        .mapToObj(i -> new ArrayList<Integer>())
                                        .collect(Collectors.toList());

        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];

            adjList.get(from).add(to);
        }
        int[] visited = new int[numCourses];
        for(int course = 0; course < numCourses; course++){
            if(visited[course] == 0 && isCyclePresent(course, visited, adjList)){
                return false;
            }
        }
        return true;
    }
}
