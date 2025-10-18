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
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        for(ListNode head: lists){
            if(head == null) continue;
            minHeap.offer(head);
        }
        if(minHeap.isEmpty()) return null;
        
        ListNode head = null;
        ListNode prev = null;
        
        while(!minHeap.isEmpty()){
            ListNode curr = minHeap.poll();
            if(prev == null){
                head = curr;
            }else{
                prev.next = curr;
            }
            if(curr.next != null){
                minHeap.offer(curr.next);
            }
            prev = curr;
        }
        return head;
    }
}