class TrieNode {
    TrieNode[] links;
    int prefixCount;
    int endCount;

    public TrieNode() {
        links = new TrieNode[26];
        prefixCount = 0;
        endCount = 0;
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

}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    

    public void insert(String word) {
        TrieNode node = root;
        node.prefixCount++; //Considering "" is prefix of all, therefore it contains totalWords info too

        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                node.setLink(currChar, new TrieNode());
            }
            node = node.getLink(currChar);
            node.prefixCount++;
        }

        //Mark then ending node
        node.endCount++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                return 0;
            }
            node = node.getLink(currChar);
        }

        return node.endCount;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currChar = prefix.charAt(i);
            if (!node.containsKey(currChar)) {
                return 0;
            }
            node = node.getLink(currChar);
        }

        return node.prefixCount;
    }

    public void erase(String word) {
        if (this.countWordsEqualTo(word) == 0) {
            return;
        }

        TrieNode node = root;
        node.prefixCount--;

        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            node = node.getLink(currChar);
            node.prefixCount--;
        }

        node.endCount--;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */