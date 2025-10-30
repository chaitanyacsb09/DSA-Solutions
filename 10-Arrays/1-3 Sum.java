//Brute force: O(N^3) with three for loops
//O(N^2 log (unique-pairs in set)) - worst case additional log n
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> tripletSet = new HashSet<>();
        for(int i = 0; i < nums.length - 1; i++){
            Set<Integer> prevEls = new HashSet<>();
            for(int j = i + 1; j < nums.length; j++){
                int thirdEl = -(nums[i] + nums[j]);
                if(prevEls.contains(thirdEl)){
                    List<Integer> currTriplet = Arrays.asList(nums[i], nums[j], thirdEl);
                    Collections.sort(currTriplet);
                    tripletSet.add(currTriplet);
                }
                prevEls.add(nums[j]);
            }
        }
        return new ArrayList(tripletSet);
    }
}

//Optimal - O(N^2)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> tripleSet = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                int currSum = nums[i] + nums[j] + nums[k];
                if (currSum == 0) {
                    tripleSet.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                        
                    //Skip Duplicates
                    while (j < k && nums[j - 1] == nums[j]) j++;
                    while (j < k && nums[k + 1] == nums[k]) k--;
                } else if (currSum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return tripleSet;
    }
}
