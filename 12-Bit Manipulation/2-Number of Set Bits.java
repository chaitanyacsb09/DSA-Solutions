//Time complexity log n
class Solution {
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
        int numSetBits = 0;
        while (n > 0) {
            numSetBits += (n & 1) != 0 ? 1 : 0;
            n >>= 1;
        }
        return numSetBits;
    }
}

//OPTIMAL
class Solution {
    public int hammingWeight(int n) {
        int numSetBits = 0;
        while (n != 0) {
            n = n & (n-1);
            numSetBits++;
        }
        return numSetBits;
    }
}

//BUILT IN
class Solution {
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}