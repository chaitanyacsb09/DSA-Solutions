//Naive Approach
class Solution {
    public String longestValidWord(String[] words) {
        // code here
        Set<String> wordSet = new HashSet<>();
        
        Comparator<String> cmp = Comparator
        .comparing(String::length)
        .thenComparing(Comparator.reverseOrder());
            
        //O(N log N * M) | M for character comparision
        Arrays.sort(words, cmp);
        
        //O(N * M) : N - Number of Words
        for (String word: words) {
            // System.out.print(word + ", ");
            wordSet.add(word);
        }
        // System.out.println();

        
        //O(N * (M * M)) : M : Avg Length of Word
        for (int i = words.length - 1; i >= 0; i--) {
            String currWord = words[i];
            boolean allPrefixPresent = true;
            
            for (int idx = 0; idx < currWord.length(); idx++) {
                if (wordSet.contains(currWord.substring(0, idx + 1)) == false) {
                    allPrefixPresent = false;
                    break;
                }
            }
            
            if (allPrefixPresent) {
                return currWord;
            }
        }
        
        return "";
    }
}

//Trie
class TrieNode {
    TrieNode[] next;
    boolean isWord;
    
    TrieNode() {
        next = new TrieNode[26];
        isWord = false;
    }
    
    private int getIndex(char c) {
        return c - 'a';
    }
    
    public boolean hasChild(char c) {
        int idx = getIndex(c);
        return next[idx] != null;
    }
    
    public void setChild(char c, TrieNode node) {
        int idx = getIndex(c);
        next[idx] = node;
    }
    
    public TrieNode getChild(char c) {
        int idx = getIndex(c);
        return next[idx];
    }
    
    public void markWord() {
        isWord = true;
    }
    
    public boolean isWord() {
        return isWord;
    }
}

class Trie {
    TrieNode root;
    
    Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode currNode = root;
        
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!currNode.hasChild(currChar)) {
                currNode.setChild(currChar, new TrieNode());
            }
            currNode = currNode.getChild(currChar);
        }
        
        currNode.markWord();
    }
    
    public boolean allPrefixesExist(String word) {
        TrieNode currNode = root;
        
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            currNode = currNode.getChild(currChar);
            
            if (currNode == null || !currNode.isWord()) {
                return false;
            }
        }
        
        return true;
    }
}

class Solution {
    public String longestValidWord(String[] words) {
        Trie trie = new Trie();
        
        // Insert all words
        for (String word : words) {
            trie.insert(word);
        }
        
        String best = "";
        
        for (String word : words) {
            if (trie.allPrefixesExist(word)) {
                if (best.length() < word.length() ||
                   (best.length() == word.length() && best.compareTo(word) > 0)) {
                    best = word;
                }
            }
        }
        
        return best;
    }
}
