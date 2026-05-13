/*You are required to complete this method */
class TrieNode {
    TrieNode[] links;
    boolean isSubstring;

    public TrieNode() {
        links = new TrieNode[26];
        isSubstring = false;
    }

    public boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    public void setLink(char c, TrieNode node) {
        links[c - 'a'] = node;
    }

    public TrieNode getLink(char c) {
        return links[c-'a'];
    }
    
    public void setIsSubstring() {
        isSubstring = true;
    }

}
class Trie {
    TrieNode root;
    int totalUniqueBranches = 0;
    public Trie() {
        root = new TrieNode();
        totalUniqueBranches = 0;
    }
    
    public void addAllSubstrings(String word) {
        for (int i = 0; i < word.length(); i++) {
            TrieNode currNode = root;
            
            for (int j = i; j < word.length(); j++) {
                char currChar = word.charAt(j);
                if (currNode.getLink(currChar) == null) {
                    totalUniqueBranches++;
                    currNode.setIsSubstring();
                    currNode.setLink(currChar, new TrieNode());
                }
                
                currNode = currNode.getLink(currChar);
            }
        }
    }
    
    public int getTotalUniqueBranches() {
        return totalUniqueBranches;
    }
}
class GfG {
    public static int countDistinctSubstring(String st) {
        // your code here
        Trie trie = new Trie();
        
        trie.addAllSubstrings(st);
        return trie.getTotalUniqueBranches() + 1;
    }
}