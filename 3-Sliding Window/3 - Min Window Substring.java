class Solution {
    private void incrementCharCount(char C, Map<Character, Integer> mp){
        mp.put(C, mp.getOrDefault(C, 0) + 1);
    }
    private void decrementCharCount(char C, Map<Character, Integer> mp){
        mp.put(C, mp.getOrDefault(C, 0) - 1);
    }
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<>();
        
        if(s.length() < t.length()){
            return "";
        }

        for(int i = 0; i < t.length(); i++){
            incrementCharCount(t.charAt(i), charCount);
        }

        int minL = 0, minR = Integer.MAX_VALUE;
        int remaining = charCount.size(); //Unique chars in t
        for(int left = 0, right = 0; right < s.length(); right++){
            char rChar = s.charAt(right);
            decrementCharCount(rChar, charCount);
            if(charCount.get(rChar) == 0) remaining--;

            while(remaining == 0){
                //new min window found
                if(minR - minL > right - left){
                    minR = right;
                    minL = left;
                }
                char lChar = s.charAt(left);
                incrementCharCount(s.charAt(left), charCount);
                
                //Only the characters in t, the charCount will go beyond 0, while shrinking the window 
                if(charCount.get(lChar) > 0){
                    remaining++;
                }
                left++;
            }
        }
        if(minR == Integer.MAX_VALUE){
            return "";
        }
        return s.substring(minL, minR+1);
    }
}

//----------WITH ARRAY
class Solution {
    private void incrementCharCount(char C, Map<Character, Integer> mp){
        mp.put(C, mp.getOrDefault(C, 0) + 1);
    }
    private void decrementCharCount(char C, Map<Character, Integer> mp){
        mp.put(C, mp.getOrDefault(C, 0) - 1);
    }
    public String minWindow(String s, String t) {
        int[] charCount = new int[128];
        
        if(s.length() < t.length()){
            return "";
        }

        int remaining = 0; //Unique chars in t
        for(int i = 0; i < t.length(); i++){
            if(charCount[t.charAt(i)]++ == 0){
                remaining++;
            }

        }

        int minL = 0, minR = Integer.MAX_VALUE;
        for(int left = 0, right = 0; right < s.length(); right++){
            char rChar = s.charAt(right);
            if(--charCount[rChar] == 0) remaining--;

            while(remaining == 0){
                //new min window found
                if(minR - minL > right - left){
                    minR = right;
                    minL = left;
                }
                char lChar = s.charAt(left);

                //Only the characters in t, the charCount will go beyond 0, while shrinking the window 
                if(++charCount[lChar] > 0){
                    remaining++;
                }
                left++;
            }
        }
        if(minR == Integer.MAX_VALUE){
            return "";
        }
        return s.substring(minL, minR+1);
    }
}