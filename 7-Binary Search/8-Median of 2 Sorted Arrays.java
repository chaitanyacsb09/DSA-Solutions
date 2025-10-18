//NAIVE: TC: O(M+N) SC: O(M+N)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        int[] mergedArray = new int[m+n];
        int k =0;
        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                mergedArray[k++] = nums1[i++];
            }
            else{
                mergedArray[k++] = nums2[j++];
            }
        }
        while(i < m){
            mergedArray[k++] = nums1[i++];
        }
        while(j < n){
            mergedArray[k++] = nums2[j++];
        }
        int mid = (m + n - 1) / 2; 
        if((m + n) % 2 == 0){
            return (mergedArray[mid] + mergedArray[mid+1]) / 2.0 ;
        }
        return mergedArray[mid];
    }
}
//BETTER SPACE : O(1)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        int totalSize = m + n;
        int count = 0;
        
        int midInd2 = (totalSize/2), midInd1 = midInd2 - 1; 
        int midInd1El = -1, midInd2El = -1;

        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                if(count == midInd1){ 
                    midInd1El = nums1[i];
                }
                if(count == midInd2){
                    midInd2El = nums1[i];
                }
                i++;
            }
            else{
                if(count == midInd1){ 
                    midInd1El = nums2[j];
                }
                if(count == midInd2){
                    midInd2El = nums2[j];
                }
                j++;
            }
            count++;
        }
        while(i < m){
            if(count == midInd1){ 
                midInd1El = nums1[i];
            }
            if(count == midInd2){
                midInd2El = nums1[i];
            };
            i++;
            count++;
        }
        while(j < n){
            if(count == midInd1){ 
                midInd1El = nums2[j];
            }
            if(count == midInd2){
                midInd2El = nums2[j];
            }
            j++;
            count++;
        }
        
        if(totalSize % 2 == 0){
            return (midInd1El + midInd2El) / 2.0 ;
        }
        return midInd2El; //Think in terms of 0 indexing
    }
}

//OPTIMAL: TC: O(LOG(M+N))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if(m > n){
            return findMedianSortedArrays(nums2, nums1);
        }
        int totalSize = m + n;
        int numElToTakeForLeftPartition = totalSize / 2;
        //Range(min numbers to take from n1, and max numbers to take from n1)
        int left = 0, right = Math.min(m, numElToTakeForLeftPartition); 

        while(left <= right){
            int numElToTakeFromN1ForLeftPartition = left + (right - left) / 2;
            int numElToTakeFromN2ForLeftPartition = numElToTakeForLeftPartition - numElToTakeFromN1ForLeftPartition;

            //This will create a partition
            int cut1 = numElToTakeFromN1ForLeftPartition - 1;
            int cut2 = numElToTakeFromN2ForLeftPartition - 1;
            
            //Left Partition will include: nums1[0...cut1] & nums2[0...cut2]
            int rightMostFromNums1InLeftPartition = cut1 < 0 ? Integer.MIN_VALUE : nums1[cut1];
            int rightMostFromNums2InLeftPartition = cut2 < 0 ? Integer.MIN_VALUE : nums2[cut2];

            //Right Partition: nums1[cut1 + 1 ... m-1] & nums2[cut2 + 1 ... n-1]
            int leftMostFromNums1InRightPartition = cut1 >= m - 1 ? Integer.MAX_VALUE : nums1[cut1 + 1];
            int leftMostFromNums2InRightPartition = cut2 >= n - 1 ? Integer.MAX_VALUE : nums2[cut2 + 1];

            //Check If Formed Paritition Valid or Not : All Elements of Left Partition should be less than or equal to all elements of Right Partition
            if(
                rightMostFromNums1InLeftPartition <= leftMostFromNums2InRightPartition &&
                rightMostFromNums2InLeftPartition <= leftMostFromNums1InRightPartition
            ){
                if(totalSize % 2 == 1){
                    return Math.min(leftMostFromNums1InRightPartition, leftMostFromNums2InRightPartition);
                }
                int rightMostFromLeftPartition = Math.max(rightMostFromNums1InLeftPartition, rightMostFromNums2InLeftPartition);
                int leftMostFromRightPartition = Math.min(leftMostFromNums1InRightPartition, leftMostFromNums2InRightPartition);  
                return (rightMostFromLeftPartition + leftMostFromRightPartition) / 2.0;
            }

            if(rightMostFromNums1InLeftPartition > leftMostFromNums2InRightPartition){
                right = numElToTakeFromN1ForLeftPartition - 1; 
            }
            else{
                left = numElToTakeFromN1ForLeftPartition + 1;
            }
        } 
        return 0;
    }
}