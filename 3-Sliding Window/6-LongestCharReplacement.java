class Solution {
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26];
        int maxCount = 0, maxLen = 0;
        
        for(int left = 0, right = 0; right < s.length(); right++){
            char rChar = s.charAt(right);
            maxCount = Math.max(maxCount, ++charCount[rChar - 'A']);
                //All Characters in window - maxFreqChar > k : i.e number of chars to be changed is greater than k, invalid window
            if((right - left + 1) - maxCount > k){
                char lChar = s.charAt(left);
                charCount[lChar - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
//Decrement MAX Count As well
//This is incorrect in termsof max count track,
//suppose we had,3 and 3 count for 2 chars, when shrinking, we just decremented maxCount
//But maxCount in the window is still 3
class Solution {
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26];
        int maxCount = 0, maxLen = 0;
        char maxCountChar = '.';
        for(int left = 0, right = 0; right < s.length(); right++){
            char rChar = s.charAt(right);
            if(++charCount[rChar - 'A'] > maxCount){
                maxCount = charCount[rChar - 'A'];
                maxCountChar = rChar;
            }
                //All Characters in window - maxFreqChar > k : i.e number of chars to be changed is greater than k, invalid window
            if((right - left + 1) - maxCount > k){
                char lChar = s.charAt(left);
                charCount[lChar - 'A']--;
                if(lChar == maxCountChar){
                    maxCount--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}