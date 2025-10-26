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

//Merge Sort With Linked List: O(NLogN) | SC: O(1)
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode prevToMid = getPrevToMid(head);
        
        //Split list to two lists
        ListNode sortedRight = sortList(prevToMid.next);
        prevToMid.next = null;
        ListNode sortedLeft = sortList(head);

        return mergeSortedLists(sortedLeft, sortedRight);
    }
    //Why prevToMid rather than mid, len of 2 list, infinite, no valid breaking of list
    //[1,3] : mid will be evaluated to 3, therfore, (right)3.next : [], (left) : [1,3]
    private ListNode getPrevToMid(ListNode head){
        ListNode slow = head, fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    private ListNode mergeSortedLists(ListNode listPtrA, ListNode listPtrB){
        ListNode head = null, prev = null;
        while(listPtrA != null && listPtrB != null){
            ListNode curr;
            if(listPtrA.val <= listPtrB.val){
                curr = listPtrA;
                listPtrA = listPtrA.next; 
            }
            else{
                curr = listPtrB;
                listPtrB = listPtrB.next;
            }
            if(head == null){
                head = curr;
            }
            else{
                prev.next = curr;
            }
            prev = curr;
        }
        if(listPtrA != null){
            prev.next = listPtrA;
        }
        if(listPtrB != null){
            prev.next = listPtrB;
        }
        return head;
    }

}

//TC: O(NlogN) SC: O(N)
class Solution {
    public ListNode sortList(ListNode head) {
        Map<Integer, List<ListNode>> sortedOrder = new TreeMap<>();
        ListNode curr = head;
        while(curr != null){
            sortedOrder.computeIfAbsent(curr.val, k -> new ArrayList<>()).add(curr);
            curr = curr.next;
        }
        ListNode prev = null;
        head = null;
        for(Map.Entry<Integer, List<ListNode>> e: sortedOrder.entrySet()){
            List<ListNode> currList = e.getValue();
            for(ListNode currNode: currList){
                if(head == null){
                    head = currNode;
                }
                else{
                    prev.next = currNode;
                }
                currNode.next = null;
                prev = currNode;
            }
        }
        return head;
    }
}

//The solution is Nearly linear, but not the most practically efficient, as it depends on the range
//Range: -10Power5 to 10Power5, nearly a billion, which is inefficient memory wise
//TC: O(N + K) SC: O(Max-Min+1) => k = Max-Min+1
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode ptr = head;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //Find Min and Max: O(N)
        while(ptr != null){
            min = Math.min(min, ptr.val);
            max = Math.max(max, ptr.val);
            ptr = ptr.next;
        }

        //Important declaration, need to be typeCasted
        List<ListNode>[] deltaSortedNodes = (List<ListNode>[]) new List[max - min + 1];

        ptr = head;
        while(ptr != null){
            int idx = ptr.val - min;
            if(deltaSortedNodes[idx] == null){
                deltaSortedNodes[idx] = new ArrayList<ListNode>();
            }
            deltaSortedNodes[idx].add(ptr);
            ptr = ptr.next;
        }

        ListNode prev = null;
        head = null;
        for(int i = 0; i < deltaSortedNodes.length; i++){
            if (deltaSortedNodes[i] == null) continue; 
            for(ListNode currNode: deltaSortedNodes[i]){
                if(head == null){
                    head = currNode;
                }
                else{
                    prev.next = currNode;
                }
                currNode.next = null;
                prev = currNode;
            }
        }
        return head;
    }
}
//Similar to above with arrayList

class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode ptr = head;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //Find Min and Max: O(N)
        while(ptr != null){
            min = Math.min(min, ptr.val);
            max = Math.max(max, ptr.val);
            ptr = ptr.next;
        }

        List<List<ListNode>> deltaSortedNodes = new ArrayList<>();
        for(int i = 1; i <= max - min + 1; i++){
            deltaSortedNodes.add(new ArrayList<ListNode>());
        }
        ptr = head;
        while(ptr != null){
            int idx = ptr.val - min;
              // Ensure capacity 
            deltaSortedNodes.get(idx).add(ptr);
            ptr = ptr.next;
        }

        ListNode prev = null;
        head = null;
        for(int i = 0; i < deltaSortedNodes.size(); i++){
            if (deltaSortedNodes.get(i).size() == 0) continue; 
            for(ListNode currNode: deltaSortedNodes.get(i)){
                if(head == null){
                    head = currNode;
                }
                else{
                    prev.next = currNode;
                }
                currNode.next = null;
                prev = currNode;
            }
        }
        return head;
    }
}

