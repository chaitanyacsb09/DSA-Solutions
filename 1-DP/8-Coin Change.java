//dp[coinIdx][amt] : minimum number fo coins requred for making amt with coins[0...coinIdx]
class Solution {
    int ubCoins = (int) 1e4 + 1;

    public int coinChange(int[] coins, int amount) {
        int numCoins = coins.length;
        int[][] dp = new int[numCoins][amount + 1];

        //Init or base cases
        for (int amt = 1; amt <= amount; amt++) {
            dp[0][amt] = amt % coins[0] == 0 ? amt / coins[0] : ubCoins;
        }
        for (int coinIdx = 1; coinIdx <= numCoins - 1; coinIdx++) {
            for (int amt = 1; amt <= amount; amt++) {
                int take = ubCoins;
                if (amt >= coins[coinIdx]) {
                    take = 1 + dp[coinIdx][amt - coins[coinIdx]];
                }
                int notTake = dp[coinIdx - 1][amt];

                dp[coinIdx][amt] = Math.min(take, notTake);
            }
        }

        return dp[numCoins - 1][amount] == (ubCoins) ? -1 : dp[numCoins - 1][amount];
    }
}

//Memoized
class Solution {
    public int coinChange(int[] coins, int amount) {
        int numCoins = coins.length;
        int[][] dp = new int[numCoins][amount + 1];

        for (int i = 0; i < numCoins; i++) {
            Arrays.fill(dp[i], -1);
        }
        int minCoinsRequired = calcMinCoinsRequiredForAmount(numCoins - 1, amount, coins, dp);

        return minCoinsRequired == (1e4+1) ? -1 : minCoinsRequired;
    }

    private int calcMinCoinsRequiredForAmount(int coinIdx, int amount, int[] coins, int[][] dp) {
        if (coinIdx == 0) {
            if (amount % coins[coinIdx] == 0) {
                return amount / coins[coinIdx];
            }
            return (int) (1e4+1);
        }
        if (dp[coinIdx][amount] != -1) {
            return dp[coinIdx][amount];
        }
        int take = (int)(1e4+1), notTake = (int)(1e4+1);

        if (coins[coinIdx] <= amount) {
            take = 1 + calcMinCoinsRequiredForAmount(coinIdx, amount - coins[coinIdx], coins, dp);
        }

        notTake = calcMinCoinsRequiredForAmount(coinIdx - 1, amount, coins, dp);

        return dp[coinIdx][amount] = Math.min(take, notTake);
    }
}