//Return Max Xor Directly
class Solution {
    private static class BitTrieNode {
        BitTrieNode[] children;

        public BitTrieNode() {
            children = new BitTrieNode[2];
        }
    }

    private static class BitTrie {
        BitTrieNode root;

        public BitTrie() {
            root = new BitTrieNode();
        }

        public void insertNum(int num) {
            BitTrieNode curr = root;

            for (int i = 31; i >= 0; i--) {
                int currBit = (num >> i) & 1; 

                if (curr.children[currBit] == null) {
                    curr.children[currBit] = new BitTrieNode();
                }

                curr = curr.children[currBit];
            }
        }

        public int findMaxXor(int num) {
            BitTrieNode curr = root;

            int maxXor = 0;
            for (int i = 31; i >= 0; i--) {
                int currBit = (num >> i) & 1; 
                int toggleBit = 1 - currBit;

                if (curr.children[toggleBit] != null) {
                    maxXor |= (1 << i);
                    curr = curr.children[toggleBit]; 
                }
                else {
                    curr = curr.children[currBit];
                }
            }

            return maxXor;
        }
    }

    //--------------------The Function---------------------
    public int findMaximumXOR(int[] nums) {
        BitTrie trie = new BitTrie();

        //Init Trie with all Nums
        for (int num: nums) {
            trie.insertNum(num);
        }

        int maxXor = 0;

        for (int num: nums) {
            int maxXorForNum = trie.findMaxXor(num);

            maxXor = Math.max(maxXor, maxXorForNum);
        }

        return maxXor;
    }
}

//Find Max Xor Partner
//Best Xor Partner/Max Xor Partner
class Solution {
    private static class BitTrieNode {
        BitTrieNode[] children;

        public BitTrieNode() {
            children = new BitTrieNode[2];
        }
    }

    private static class BitTrie {
        BitTrieNode root;

        public BitTrie() {
            root = new BitTrieNode();
        }

        public void insertNum(int num) {
            BitTrieNode curr = root;

            for (int i = 31; i >= 0; i--) {
                int currBit = (num >> i) & 1; 

                if (curr.children[currBit] == null) {
                    curr.children[currBit] = new BitTrieNode();
                }

                curr = curr.children[currBit];
            }
        }

        public int findMaxXorPartner(int num) {
            BitTrieNode curr = root;

            int mask = 1;
            int bestXorPartner = 0;
            for (int i = 31; i >= 0; i--) {
                int currBit = (num >> i) & 1; 
                bestXorPartner <<= 1;
                int toggleBit = 1 - currBit;

                if (curr.children[toggleBit] != null) {
                    bestXorPartner = bestXorPartner | toggleBit;
                    curr = curr.children[toggleBit]; 
                }
                else {
                    bestXorPartner = bestXorPartner | currBit;
                    curr = curr.children[currBit];
                }
            }

            return bestXorPartner;
        }
    }

    public int findMaximumXOR(int[] nums) {
        BitTrie trie = new BitTrie();

        //Init Trie with all Nums
        for (int num: nums) {
            trie.insertNum(num);
        }

        int maxXor = 0;

        for (int num: nums) {
            int maxXorPartner = trie.findMaxXorPartner(num);

            maxXor = Math.max(maxXor, num ^ maxXorPartner);
        }

        return maxXor;
    }
}

//Best Performing
class BitTrieNode {
    BitTrieNode zero;
    BitTrieNode one;

    public BitTrieNode() {
        zero = null;
        one = null;
    }
}

class BitTrie {
    BitTrieNode root;

    public BitTrie() {
        root = new BitTrieNode();
    }

    public void insertNum(int num) {
        BitTrieNode curr = root;

        int mask = 1;
        for (int i = 31; i >= 0; i--) {
            int currBit = ((mask << i) & num) > 0 ? 1 : 0; 

            if (currBit == 1 && curr.one == null) {
                curr.one = new BitTrieNode();
            }

            if (currBit == 0 && curr.zero == null) {
                curr.zero = new BitTrieNode();
            }

            curr = currBit == 0 ? curr.zero : curr.one;
        }
    }

    public int findBestXorPartner(int num) {
        BitTrieNode curr = root;

        int mask = 1;
        int bestXorPartner = 0;
        for (int i = 31; i >= 0; i--) {
            int currBit = ((mask << i) & num) > 0 ? 1 : 0; 
            bestXorPartner <<= 1;
            if (currBit == 0 && curr.one != null) {
                bestXorPartner = bestXorPartner | 1;
                curr = curr.one; 
            }
            else if (currBit == 1 && curr.zero != null) {
                bestXorPartner = bestXorPartner | 0;
                curr = curr.zero;
            }
            else {
                bestXorPartner = bestXorPartner | currBit;
                curr = currBit == 0 ? curr.zero : curr.one;
            }
        }

        return bestXorPartner;
    }
}
class Solution {
    public int findMaximumXOR(int[] nums) {
        BitTrie trie = new BitTrie();

        //Init Trie with all Nums
        for (int num: nums) {
            trie.insertNum(num);
        }

        int maxXor = 0;

        for (int num: nums) {
            int bestXorPartner = trie.findBestXorPartner(num);

            maxXor = Math.max(maxXor, num ^ bestXorPartner);
        }

        return maxXor;
    }
}