    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        if (n == 0) return -1;
        if (n == 1) return 0;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // handle boundaries safely
            int leftVal = (mid - 1 >= 0) ? arr[mid - 1] : Integer.MIN_VALUE;
            int rightVal = (mid + 1 < n) ? arr[mid + 1] : Integer.MIN_VALUE;

            // mid is a strict peak
            if (arr[mid] > leftVal && arr[mid] > rightVal) {
                return mid;
            }

            // if strictly increasing towards right, go right, we are on left side of peak
            if (arr[mid] < rightVal) {
                left = mid + 1;
                continue;
            }

            // if strictly decreasing towards left, go left, we are on right side of peak
            if (arr[mid] < leftVal) {
                right = mid - 1;
                continue;
            }

            // At this point arr[mid] is equal to at least one neighbor (plateau scenario).
            // Expand to find the full plateau [lPlateau, rPlateau]
            int lPlateau = mid, rPlateau = mid;
            while (lPlateau - 1 >= left && arr[lPlateau - 1] == arr[mid]) lPlateau--;
            while (rPlateau + 1 <= right && arr[rPlateau + 1] == arr[mid]) rPlateau++;

            // Check neighbors of the plateau
            int leftNeighbor = (lPlateau - 1 >= 0) ? arr[lPlateau - 1] : Integer.MIN_VALUE;
            int rightNeighbor = (rPlateau + 1 < n) ? arr[rPlateau + 1] : Integer.MIN_VALUE;

            // If plateau has higher neighbor on left, there must be a peak on left side
            if (leftNeighbor > arr[mid]) {
                right = lPlateau - 1;
            }
            // If plateau has higher neighbor on right, move search to right side
            else if (rightNeighbor > arr[mid]) {
                left = rPlateau + 1;
            }
            // Plateau is a local maximum region (no higher neighbors); any index in plateau is a peak
            else {
                return lPlateau; // or rPlateau or mid — all are valid peaks
            }
        }

        // Fallback (shouldn't happen for well-formed input) — return -1 for no peak found
        return -1;
    }