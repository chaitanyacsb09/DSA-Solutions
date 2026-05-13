class Solution {
    public int singleNumber(int[] nums) {
        int numsExor = 0;
        for (int num: nums) {
            numsExor ^= num;
        }

        return numsExor;
    }
}