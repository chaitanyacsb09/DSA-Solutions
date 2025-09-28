//At most k distinct characters max substring type question, with k = 2
class Solution {
    public int totalFruit(int[] fruits) {
        int[] fruitCountByType = new int[fruits.length];
        int maxFruits = 0;
        int fruitTypesCount = 0;
        
        for (int left = 0, right = 0; right < fruits.length; right++) {
            int currFruitType = fruits[right];
            if(fruitCountByType[currFruitType]++ == 0){
                fruitTypesCount++;
            }

            // Shrink window if fruit types exceeds 2, as we have only 2 bags
            while (fruitTypesCount > 2) {
                int fruitTypeAtL = fruits[left];
                if (--fruitCountByType[fruitTypeAtL] == 0) {
                    fruitTypesCount--;
                }
                left++;
            }

            // Update maxfruits
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }
}