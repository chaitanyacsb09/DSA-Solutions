/*6 5 4 1 8 3 
	can i replace 3, yes but with only el greater, 4 here, but swapping with 4 will make this permutation smaller
	8, yes with 9 and greater, do not have access to that
	1, yes with 2 and greater, 3, 4, 5, replacing with 4 and 5 will make the permutation smaller than curr, 
		3 and 8 to its right won't, therefore can replace with either 3 or 8 but need to keep it smaller, hence pick min
obs1 : so longer prefix, and the replaced element, should have some greater el to its right
obs2 :  pick min from right
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int swapIdx = -1;
        int n = nums.length;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                swapIdx = i;
                break;
            }
        }
        if (swapIdx == -1) {
            //Curr Perm is Last one, hence reverse to get the first one
            reverse(nums, 0, n - 1);
            return;
        }
        int nxtGreaterIdx = -1;
        for (int i = n - 1; i > swapIdx; i--) {
            if (nums[i] > nums[swapIdx]) {
                nxtGreaterIdx = i;
                break;
            }
        }
        int temp = nums[nxtGreaterIdx];
        nums[nxtGreaterIdx] = nums[swapIdx];
        nums[swapIdx] = temp;

        reverse(nums, swapIdx + 1, n - 1);
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}