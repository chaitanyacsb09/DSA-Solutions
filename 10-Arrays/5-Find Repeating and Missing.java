//TC: O(2N) SC: O(N)
class Solution {
    ArrayList < Integer > findTwoElement(int nums[]) {
        int repeating = -1, missing = -1;
        int n = nums.length;
        //int array of length n+1 would have been better, for java runtime performance, around n elements will there
        HashMap < Integer, Integer > numToFreq = new HashMap < > ();
        for (int num: nums) {
            numToFreq.merge(num, 1, Integer::sum);
        }
        for (int num = 1; num <= n; num++) {
            if (!numToFreq.containsKey(num)) {
                missing = num;
            } else if (numToFreq.get(num) == 2) {
                repeating = num;
            }
            if(missing != -1 && repeating != -1){
                break;
            }
        }
        return new ArrayList<>(Arrays.asList(repeating, missing));
    }
}

/**
    xor of 2 same el is o
    xor(arr) -x1 xor of all elements excluding r and m number, r & r will make 0 
    xor(1..n) - x2 - xor of all including r and m
    x1 ^ x2 =  xor of r and m
    figure out the first bit where they differ, and divide the els, into two groups, g1 - differentiating bit set to 1, g2 - 0
    xor, all el of arr, and nums:1..n
    z = xor of unset bit numbers from given arr and ideal arr, therefore, all existing elements in given will be cancelled out , either, missing or repeated el will remain, missing because not included in given arr hence no cancellation, and repeated because in given arr its repeated hence cancels, and also we xorring the num from 1..n so will remain from that
    o = including (r or m)
    xor, z with rmXor and o with rmXor, then we get the two numbers we are looking for now we need to traverse and count occurrences to figure out which is what
*/
//XOR Solution: SC: O(1) TC: O(3N)
class Solution {
    ArrayList < Integer > findTwoElement(int nums[]) {
        int n = nums.length;
        int x1 = 0, x2 = 0;
        for (int i = 0; i < n; i++) {
            x1 = x1 ^ nums[i];
            x2 = x2 ^ (i + 1);
        }
        int rmXor = x1 ^ x2;
        int diffNum = (rmXor & ~(rmXor - 1));
        // int diffNum = 1;
        while ((rmXor & diffNum) == 0) {
            diffNum <<= 1;
        }
        int zeroBitXor = 0, oneBitXor = 0;

        for (int i = 0; i < n; i++) {
            int num = i + 1;
            if ((num & diffNum) == 0) {
                zeroBitXor ^= num;
            } else {
                oneBitXor ^= num;
            }
            if ((nums[i] & diffNum) == 0) {
                zeroBitXor ^= nums[i];
            } else {
                oneBitXor ^= nums[i];
            }

        }
        int zeroBitNum = oneBitXor ^ rmXor, oneBitNum = zeroBitXor ^ rmXor;
        int zeroBitNumCount = 0;
        for (int num: nums) {
            if (num == zeroBitNum && ++zeroBitNumCount == 2) {
                return new ArrayList < > (Arrays.asList(zeroBitNum, oneBitNum));
            }
        }
        return new ArrayList < > (Arrays.asList(oneBitNum, zeroBitNum));
    }
}

//TC: O(N) SC: O(1) - In java if answer is stored in long, then make sure in expression to convert long
/**
 * Sum of arr : a1 + ... r + r + ..aN - S
 * Sum of 1..n : 1 + 2 + r + m + ...N - Sn
 * S-Sn = r - m
 */
class Solution {
    ArrayList < Integer > findTwoElement(int nums[]) {
        int n = nums.length;

        long arrSum = 0, originalSum = ((long)n * (n + 1)) / 2;
        long arrSqSum = 0, originalSqSum = ((long)n * (n + 1) * (2 * n + 1)) / 6;
        for (int num: nums) {
            arrSum += num;
            arrSqSum += (long) num * num;
        }
        long r_minus_m = arrSum - originalSum;
        long r_plus_m = (int)((arrSqSum - originalSqSum) / r_minus_m);

        long r = (r_minus_m + r_plus_m) / 2;
        long m = r_plus_m - r;
        
        return new ArrayList < > (Arrays.asList((int)r, (int)m));
    }
}