//Initital
class Solution {
    private int[] getPiArray(String pat) {
        int patternLength = pat.length();
        //pi[i] - length of longest suffix ending at i, which is also a proper prefix 
        int[] pi = new int[patternLength];
        
        for (int i = 1; i < patternLength; i++) {
            int len = pi[i - 1];
            
            while (len > 0 && pat.charAt(len) != pat.charAt(i)) {
                len = pi[len - 1];
            }
            
            if (pat.charAt(len) == pat.charAt(i)) {
                len++;
            }
            
            pi[i] = len;
        }
        
        return pi;
    }
    ArrayList<Integer> search(String pat, String txt) {
        // code here
        int[] pi = getPiArray(pat);
        
        ArrayList<Integer> indexes = new ArrayList<>();
        
        int i = 0; //ptr for txt
        int j = 0; //ptr for pattern
        
        while (i < txt.length()) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }
            else if (j == 0) {
                i++;
            }
            else {
                j = pi[j - 1];
            }
            
            if (j == pat.length()) {
                indexes.add(i - j);
                j = pi[j - 1];
            }
        }
        
        return indexes;
    }
}