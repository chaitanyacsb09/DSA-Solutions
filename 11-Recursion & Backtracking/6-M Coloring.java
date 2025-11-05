class Solution {
    boolean graphColoring(int v, int[][] edges, int m) {
        // code here
        //Try coloring each vertex from 1 .. m;
        //while coloring, check with its neighbours, if they have some different color
        List<List < Integer >> adjG = new ArrayList<>();
        for (int i = 0; i < v; i++) adjG.add(new ArrayList < > ());

        for (int[] edge: edges) {
            int vertexA = edge[0], vertexB = edge[1];

            adjG.get(vertexA).add(vertexB);
            adjG.get(vertexB).add(vertexA);
        }
        int[] vertexColor = new int[v]; // all inits to 0
        return colorVertex(0, adjG, vertexColor, m);
    }
    private boolean colorVertex(int vertexIdx, List<List < Integer >> adjG, int[] vertexColor, int m) {
        if (vertexIdx == adjG.size()){
            return true;
        }

        for (int color = 1; color <= m; color++) {
            //TODO: ColorValid Implementation
            if (!isColorValidForVertex(vertexIdx, adjG, color, vertexColor)) {
                continue;
            }
            vertexColor[vertexIdx] = color;
            boolean canVertexBeColoredWithCurrColor = colorVertex(vertexIdx + 1, adjG, vertexColor, m);
            if (canVertexBeColoredWithCurrColor) {
                return true;
            }
            vertexColor[vertexIdx] = 0;
        }
        return false;
    }
    private boolean isColorValidForVertex(int vertexIdx, List<List < Integer >> adjG, int color, int[] vertexColor) {
        for (int adjVertex: adjG.get(vertexIdx)) {
            if (vertexColor[adjVertex] == color) {
                return false;
            }
        }
        return true;
    }
}

//Greedy for 4 Coloring with at most 3 adj
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        int gardenColor[] = new int[n];
        List < List < Integer >> adjG = new ArrayList < > ();
    for (int i = 0; i < n; i++) adjG.add(new ArrayList < > ());

    for (int[] path: paths) {
        int gardenA = path[0] - 1, gardenB = path[1] - 1;
        adjG.get(gardenA).add(gardenB);
        adjG.get(gardenB).add(gardenA);
    }

    for(int i = 0; i < n; i++){
        boolean[] usedColors = new boolean[5];
        for(int nbr: adjG.get(i)){
            usedColors[gardenColor[nbr]] = true;
        }
        for(int color = 1; color <= 4; color++){
            if(usedColors[color]){
                continue;
            }
            gardenColor[i] = color;
            break;
        }
    }
    return gardenColor;
}


}