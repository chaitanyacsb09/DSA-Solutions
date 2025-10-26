/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
//TC: O(N) : 2 Pass O(2N) | SC: O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int numNodes = countNodes(head);
        if(numNodes == 1){
            return null;
        }
        if(numNodes == n){
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        }
        removeNthFromEnd(numNodes, head, n);
        return head;
    }
    private int countNodes(ListNode head){
        ListNode temp = head;
        int nodeCount = 0;
        while(temp != null){
            nodeCount++;
            temp = temp.next;
        }
        return nodeCount;
    }
    private void removeNthFromEnd(int numNodes, ListNode head, int n){
        int stopAtNode = numNodes - n; //n+1 node from the back
        ListNode temp = head;
        int currNodePos = 1;
        while(currNodePos < stopAtNode){
            temp = temp.next;
            currNodePos++;
        }
        ListNode nodeToBeDeleted = temp.next; //nth node from back
        temp.next = nodeToBeDeleted.next;
        nodeToBeDeleted.next = null; 
    }
    // 1 2 3 5 6 8 9, n = 3 -> 1 2 3 5 6 8 9
}

//TC: O(N) | SC: O(N)
//With Array: Faster in JAVA
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] posToNode = new ListNode[31];
        ListNode curr = head;
        int currPos = 1;
        while(curr != null){
            posToNode[currPos] = curr;
            curr = curr.next;
            currPos++;
        }
        int numNodes = currPos - 1;
        if(numNodes == 1){
            return null;
        }
        if(numNodes == n){
            head.next = null;
            return posToNode[2];
        }
        ListNode nodeBeforeTheOneToBeDeleted = posToNode[numNodes - n];
        ListNode nodeToBeDeleted = posToNode[numNodes - n + 1];
        nodeBeforeTheOneToBeDeleted.next = nodeToBeDeleted.next;
        nodeToBeDeleted.next = null;
        return head;
    }
}
//WITH HASHMAP
class Solution {
    private Map<Integer, ListNode> getPosToNodeMapping(ListNode head){
        Map<Integer, ListNode> posToNode = new HashMap<>();
        ListNode curr = head;
        int currPos = 1;
        while(curr != null){
            posToNode.put(currPos, curr);
            curr = curr.next;
            currPos++;
        }
        return posToNode;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> posToNode = getPosToNodeMapping(head);
        int numNodes = posToNode.size();
        if(numNodes == 1){
            return null;
        }
        if(numNodes == n){
            head.next = null;
            return posToNode.get(2);
        }
        ListNode nodeBeforeTheOneToBeDeleted = posToNode.get(numNodes - n);
        ListNode nodeToBeDeleted = posToNode.get(numNodes - n + 1);
        nodeBeforeTheOneToBeDeleted.next = nodeToBeDeleted.next;
        nodeToBeDeleted.next = null;
        return head;
    }
}

//TC: O(N) | SC: O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head, right = head;
        int nodeCount = 1;
        //Place the right ptr, to point at n+1th node from start
        while(nodeCount < n + 1){ //nodeCount != n+1
            right = right.next;
            nodeCount++;
        }
        //If n == numNodes, therefore the first node is asked to be removed
        if(right == null) return head.next;

        //Move one node at a time, maintaining a distance of n+1 between left and right
        while(right.next != null){
            left = left.next;
            right = right.next;
        }
        //When right pts to last node, left is at n+1(th) node, a node before the node to be deleted, as we are mainting a distance of n+1 between the two pointers
        left.next = left.next.next;
        return head;

    }
}