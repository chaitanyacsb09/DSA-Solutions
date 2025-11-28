class TrieNode {
    TrieNode[] links;
    boolean isEnd;

    public TrieNode() {
        links = new TrieNode[26];
        isEnd = false;
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

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd() {
        isEnd = true;
    }
}
class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                node.setLink(currChar, new TrieNode());
            }
            node = node.getLink(currChar);
        }

        //Mark then ending node
        node.setEnd();
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                return false;
            }
            node = node.getLink(currChar);
        }

        return node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currChar = prefix.charAt(i);
            if (!node.containsKey(currChar)) {
                return false;
            }
            node = node.getLink(currChar);
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */