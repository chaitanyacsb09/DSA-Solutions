//TC: O(n^2) SC: O(1)
class Solution {
    static int inversionCount(int arr[]) {
        int n = arr.length;
        int numInversions = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) numInversions++;
            }
        }
        return numInversions;

    }
}
//TC: O(NlogN) SC: O(N)
class MergeSort {
    public int sortAndReturnInversions(int[] arr, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int numInversions = 0;
        numInversions += sortAndReturnInversions(arr, left, mid);
        numInversions += sortAndReturnInversions(arr, mid + 1, right);
        numInversions += mergeAndReturnInversions(arr, left, mid, right);
        return numInversions;
    }

    private int mergeAndReturnInversions(int[] arr, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];
        int lPtr = left, rPtr = mid + 1;
        int tempIdx = 0;
        int numInversions = 0;
        while (lPtr <= mid && rPtr <= right) {
            if (arr[lPtr] <= arr[rPtr]) {
                tempArr[tempIdx] = arr[lPtr];
                lPtr++;
            } else {
                //Inversion part, left is greater than right
                numInversions += mid - (lPtr - 1); //had used mid - (left - 1) : be careful small mistake can ruin all
                tempArr[tempIdx] = arr[rPtr];
                rPtr++;
            }
            tempIdx++;
        }
        while (lPtr <= mid) {
            tempArr[tempIdx] = arr[lPtr];
            lPtr++;
            tempIdx++;
        }
        while (rPtr <= right) {
            tempArr[tempIdx] = arr[rPtr];
            rPtr++;
            tempIdx++;
        }
        for (int i = 0, arrIdx = left; i < tempArr.length; i++, arrIdx++) {
            arr[arrIdx] = tempArr[i];
        }
        return numInversions;
    }
}
class Solution {
    static int inversionCount(int arr[]) {
        int n = arr.length;
        int[] arrCopy = (int[]) arr.clone();
        MergeSort mergeSort = new MergeSort();
        return mergeSort.sortAndReturnInversions(arrCopy, 0, n - 1);
    }
}