//-------Approach 1: Storing Character Count-----------------------
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        int sLen = s.length();
        int longestSubstringLengthWithoutRepeatingCharacters = 0;
        
        for(int left = 0, right = 0; right < sLen; right++){
            char currChar = s.charAt(right);
            charCount.put(currChar, charCount.getOrDefault(currChar, 0) + 1);
            while(left < right && charCount.get(currChar) > 1){
                charCount.put(s.charAt(left), charCount.get(s.charAt(left)) - 1);
                left++;
            }
            longestSubstringLengthWithoutRepeatingCharacters = Math.max(
                longestSubstringLengthWithoutRepeatingCharacters,
                right - left + 1
            );
        }
        return longestSubstringLengthWithoutRepeatingCharacters;
    }
}

//-------Approach 2: Storing Last Idx of Character Occurence-----------------------
//Skips the inner while loop iterations, and directly points to left to the idx,
//Such that all prev occurences of curr char are removed
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIdx = new HashMap<>();
        int sLen = s.length();
        int maxLen = 0;
        
        for(int left = 0, right = 0; right < sLen; right++){
            char currChar = s.charAt(right);
            if(lastIdx.containsKey(currChar)){
                left = Math.max(left, lastIdx.get(currChar) + 1); //Removing all prev occurences of currChar
            }
            lastIdx.put(currChar, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}

//-------Approach 2: Using Integer Array-----------------------
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastIdx = new int[128];
        Arrays.fill(lastIdx, -1);
        int sLen = s.length();
        int maxLen = 0;
        
        for(int left = 0, right = 0; right < sLen; right++){
            char currChar = s.charAt(right);
            if(lastIdx[currChar] >= left){ //Prev Occurence of currChar is in same window
                left = lastIdx[currChar] + 1; //Removing all prev occurences of currChar
            }
            lastIdx[currChar] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
