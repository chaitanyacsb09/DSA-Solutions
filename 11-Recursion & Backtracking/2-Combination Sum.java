class Solution {
    public List < List < Integer >> combinationSum(int[] candidates, int target) {
        Set < List < Integer >> allCombinations = new HashSet < > ();
        evalCombinations(0, candidates, target, allCombinations, new ArrayList < > ());
        return new ArrayList < > (allCombinations);
    }

    private void evalCombinations(int idx, int[] candidates, int target, Set < List < Integer >> allCombinations, List < Integer > currCombination) {
         
        if (idx >= candidates.length) {
            if (target == 0) {
                allCombinations.add(new ArrayList < > (currCombination));
            }
            return;
        }
        if (target >= candidates[idx]) {    
            currCombination.add(candidates[idx]);
            evalCombinations(idx, candidates, target - candidates[idx], allCombinations, currCombination);
            currCombination.remove(currCombination.size() - 1);
        }
        evalCombinations(idx + 1, candidates, target, allCombinations, currCombination);
    }
}