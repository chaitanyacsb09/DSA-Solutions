class Solution {
    public int longestKSubstr(String s, int k) {
        int[] charCount = new int[26];
        int maxLen = -1;
        int left = 0;
        int numDistincts = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            if(charCount[rChar - 'a']++ == 0){
                numDistincts++;
            }

            // Shrink window if distinct chars exceed k
            while (numDistincts > k) {
                char lChar = s.charAt(left);
                if (--charCount[lChar - 'a'] == 0) {
                    numDistincts--;
                }
                left++;
            }

            // Update maxLen if window has exactly k distinct chars
            if (numDistincts == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
}

//WITH MAP
class Solution {
    public int longestKSubstr(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLen = -1;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            charCount.put(rChar, charCount.getOrDefault(rChar, 0) + 1);

            // Shrink window if distinct chars exceed k
            while (charCount.size() > k) {
                char lChar = s.charAt(left);
                charCount.put(lChar, charCount.get(lChar) - 1);
                if (charCount.get(lChar) == 0) {
                    charCount.remove(lChar);
                }
                left++;
            }

            // Update maxLen if window has exactly k distinct chars
            if (charCount.size() == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
}
