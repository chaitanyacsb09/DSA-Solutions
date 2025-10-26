/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

//TC: O(M+N) SC:O(M)
public class Solution {
    /*
    a = 1, 2, 3, 4, 5, 6
    b = 4, 5, 6
    */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode a = headA, b = headB;
        while(a != null){
            nodeSet.add(a);
            a = a.next;
        }
        while(b != null){
            if(nodeSet.contains(b)){
                return b;
            }
            b = b.next;
        }
        return null;
    }
}

//TC: O(M+N) SC: O(1)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode[] alignedPtrs = getAlignedPtrs(headA, headB);
        ListNode ptrA = alignedPtrs[0], ptrB = alignedPtrs[1];
        while(ptrA != null && ptrB != null){
            if(ptrA == ptrB){
                return ptrA;
            }
            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }
        return null;
    }
    private ListNode[] getAlignedPtrs(ListNode headA, ListNode headB){
        int numNodesA = getNodeCount(headA), numNodesB = getNodeCount(headB);
        int absDiff = Math.abs(numNodesA - numNodesB);
        
        ListNode ptrA = headA, ptrB = headB;
        if(numNodesA > numNodesB){
            ptrA = alignList(absDiff, ptrA);
        }
        else if(numNodesA < numNodesB){
            ptrB = alignList(absDiff, ptrB);
        }
        return new ListNode[]{ptrA, ptrB};
    }
    private int getNodeCount(ListNode head){
        ListNode curr = head;
        int nodeCount = 0;
        while(curr != null){
            nodeCount++;
            curr = curr.next;
        }
        return nodeCount;
    }

    private ListNode alignList(int absDiff, ListNode ptr){
        int ptrMoved = 0;
        while(ptrMoved < absDiff){
            ptr = ptr.next;
            ptrMoved++;
        }
        return ptr;
    }
}

//TC: O(M+N) SC: O(1)
//VISUALIZATION IN COMMENTS: https://leetcode.com/problems/intersection-of-two-linked-lists/solutions/49785/java-solution-without-knowing-the-difference-in-len
//Also could check Neetcode's Explanation of intuition
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while(a != b){
            if(a == b){
                return a;
            }
            //Assigning the ptrs to other list, when one is exhausted, helps in aligning the two pointers
            //such that, they start at the same distance from the intersection in the 2nd iteration
            //Or aligned before the end of both the lists if no intersection point is there
            //Terminates in 2 iterations
            a = a != null ? a.next : headB;
            b = b != null ? b.next : headA; 
        }
        return a;
    }
}