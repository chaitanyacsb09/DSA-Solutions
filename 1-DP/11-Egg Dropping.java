//Memoization
class Solution {
    private int superEggDropUtil(int numEggs, int numFloors, int[][]dp){
        if(numFloors == 0 || numFloors == 1) return numFloors;
        if(numEggs == 1) return numFloors;

        if(dp[numEggs][numFloors] != -1){
            return dp[numEggs][numFloors];
        }
        int min = 10001, l=1, r=numFloors, temp = 0;

        while(l <= r){
            int mid = l + (r-l)/2;
            int eggBroken = superEggDropUtil(numEggs-1, mid-1, dp);
            int eggNotBroken = superEggDropUtil(numEggs, numFloors-mid, dp);

            if(eggBroken < eggNotBroken){
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
            temp = 1 + Math.max(
                eggBroken,
                eggNotBroken
            );
            min = Math.min(temp, min);
        }

        return dp[numEggs][numFloors] = min;
    }
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k+1][n+1];
        for(int i= 0; i <= k; i++){
            Arrays.fill(dp[i],-1);
        }
        return superEggDropUtil(k,n, dp);
    }
}

//Tabulation
class Solution {
    public int superEggDrop(int e, int f) {
        if(f == 1 || f == 0) return f;
        if(e == 1) return f;

        int[][] dp = new int[e+1][f+1];
        
        for(int i = 0; i <= e; i++){
            dp[i][0] = 0; dp[i][1] = 1; //n ==0 || n == 1 return n
        }
        for(int i = 0; i <= f; i++){
            dp[1][i] = i;
            dp[0][i] = 0;
        }

        for(int numEggs = 2; numEggs <= e; numEggs++){
            for(int numFloors = 2; numFloors <= f; numFloors++){
                int min = 10001, l=1, r=numFloors, temp = 0;
                while(l <= r){
                    int mid = l + (r-l)/2;
                    int eggBroken = dp[numEggs-1][mid-1];
                    int eggNotBroken = dp[numEggs][numFloors-mid];

                    if(eggBroken < eggNotBroken){
                        l = mid + 1;
                    }
                    else{
                        r = mid - 1;
                    }
                    temp = 1 + Math.max(
                        eggBroken,
                        eggNotBroken
                    );
                    min = Math.min(temp, min);
                }
                dp[numEggs][numFloors] = min;
            }
        }
        return dp[e][f];
    }
}