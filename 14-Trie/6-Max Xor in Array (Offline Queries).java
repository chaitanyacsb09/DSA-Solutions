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
                int desired = 1 - currBit;

                if (curr.children[desired] != null) {
                    maxXor |= (1 << i); //Desired bit found, therefore resultant xor, will have 1 at this bit position
                    curr = curr.children[desired]; 
                }
                else {
                    curr = curr.children[currBit];
                }
            }

            return maxXor;
        }
    }
    
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[][] queriesWithIdx = new int[queries.length][3];

        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int m = queries[i][1];

            queriesWithIdx[i][0] = x;
            queriesWithIdx[i][1] = m;
            queriesWithIdx[i][2] = i;
        }
        

        Arrays.sort(queriesWithIdx, (a,b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(nums);

        int idx = 0;
        int[] answers = new int[queries.length];

        BitTrie trie = new BitTrie();

        for (int[] query: queriesWithIdx) {
            int x = query[0];
            int m = query[1];
            int queryIdx = query[2];

            while (idx < nums.length && nums[idx] <= m) {
                trie.insertNum(nums[idx]);
                idx++;
            }

            int currAnswer = -1;

            if (idx != 0) {
                currAnswer = trie.findMaxXor(x);
            }

            answers[queryIdx] = currAnswer;
        }

        return answers;
    }
}

//----------Without Sorting---------
class BitTrie {
    private static class BitTrieNode {
        BitTrieNode[] children = new BitTrieNode[2];
        // The augment: store the smallest number in this subtree
        int minVal = Integer.MAX_VALUE; 
    }

    BitTrieNode root = new BitTrieNode();

    public void insert(int num) {
        BitTrieNode curr = root;
        // Update root's minVal immediately
        curr.minVal = Math.min(curr.minVal, num);
        
        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.children[bit] == null) {
                curr.children[bit] = new BitTrieNode();
            }
            curr = curr.children[bit];
            // Update the child's minVal as we pass through
            curr.minVal = Math.min(curr.minVal, num);
        }
    }

    public int query(int x, int m) {
        BitTrieNode curr = root;
        
        // Fast fail: If the smallest number in the whole Trie is > m, 
        // impossible to find any number <= m.
        if (curr.minVal > m) return -1;

        int resultXor = 0;

        for (int i = 30; i >= 0; i--) {
            int xBit = (x >> i) & 1;
            int desired = 1 - xBit; // We want the opposite bit
            
            // CHECK 1: Can we go to the desired (Max XOR) branch?
            if (curr.children[desired] != null && curr.children[desired].minVal <= m) {
                resultXor |= (1 << i); // We get a 1 in this position
                curr = curr.children[desired];
            } 
            // CHECK 2: Can we fallback to the matching branch?
            else if (curr.children[xBit] != null && curr.children[xBit].minVal <= m) {
                // We get a 0 in this position (xBit ^ xBit = 0)
                curr = curr.children[xBit];
            } 
            // CHECK 3: Dead end
            else {
                return -1; // Should theoretically be caught by root check, but safe to keep
            }
        }
        return resultXor;
    }
}

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        BitTrie trie = new AugmentedBitTrie();
        
        // 1. Build the full Trie upfront
        for (int num : nums) {
            trie.insert(num);
        }

        int[] ans = new int[queries.length];
        
        // 2. Process queries in their original order (Online)
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int m = queries[i][1];
            
            int maxXor = trie.query(x, m);
            
            // If query returned -1, answer is -1. 
            // Else answer is the maxXor (which is x ^ bestCandidate)
            // Note: The trie.query method above returns the actual XOR value directly
            // for simplicity, unlike the previous version which returned the number.
            ans[i] = maxXor;
        }
        
        return ans;
    }
}