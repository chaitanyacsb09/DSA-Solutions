//TC: O(N * 2^N): N-To Copy List | SC: O(N * 2^N) //2^N including the output
class Solution {
    public List < List < Integer >> subsets(int[] nums) {
        Set < List < Integer >> allSubsets = new HashSet < > ();
        generateSubsets(nums, 0, allSubsets, new ArrayList < > ());
        return new ArrayList < > (allSubsets);
    }

    private void generateSubsets(int[] nums, int idx, Set < List < Integer >> allSubsets, List < Integer > currList) {
        if (idx >= nums.length) {
            allSubsets.add(new ArrayList < > (currList));
            return;
        }

        currList.add(nums[idx]);
        generateSubsets(nums, idx + 1, allSubsets, currList);
        currList.remove(currList.size() - 1);
        generateSubsets(nums, idx + 1, allSubsets, currList);
    }
}

//Iterative
class Solution {
    public List < List < Integer >> subsets(int[] nums) {
        int n = nums.length;
        int total = 1 << n;

        List < List < Integer >> allSubsets = new ArrayList < > ();
        for (int mask = 0; mask < total; mask++) {
            List < Integer > currList = new ArrayList < > ();
            for (int i = 0; i < n; i++) {
                boolean isMaskBitSet = (mask & (1 << i)) != 0;
                if (isMaskBitSet) {
                    currList.add(nums[i]);
                }
            }
            allSubsets.add(currList);
        }
        return allSubsets;
    }
}

/*
    Iterative 2 : [1,2,3]
    step0: [[]]
    step1: [[]] (extend each by adding 1) ->[[][1]]
    step2: [[][1]] (extend each by adding 2) -> [[][1][2][1,2]]
    step3: [[][1][2][1,2]] (extend each by adding 3) -> [[][1][2][1,2][3][1,3][2,3][1,2,3]]
    Therefore simulating two choices for each number
    TC: O(n * 2 ^ n) SC: O(n * 2^n)
*/
class Solution {
    public List < List < Integer >> subsets(int[] nums) {
        int n = nums.length;
        List < List < Integer >> allSubsets = new ArrayList < > ();
        allSubsets.add(new ArrayList < > ()); //The Empty Set
        for (int num: nums) {
            int currSubsets = allSubsets.size();
            for (int i = 0; i < currSubsets; i++) {
                List < Integer > extendedSubset = new ArrayList < > (allSubsets.get(i));
                extendedSubset.add(num);
                allSubsets.add(extendedSubset);
            }
        }
        return allSubsets;
    }
}