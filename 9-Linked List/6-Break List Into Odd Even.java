
// TC: O(N) SC: O(1)
class Solution {
    ListNode oddHead = null, prevOdd = null;
    ListNode evenHead = null, prevEven = null;
    private void breakIntoEvenOdd(int idx, ListNode curr){
        if(curr == null){
            return;
        }
        if(idx % 2 == 0){
            if(evenHead == null){
                evenHead = curr;
            }
            else{
                prevEven.next = curr;
            }
            prevEven = curr;
        }
        else{
            if(oddHead == null){
                oddHead = curr;
            }
            else{
                prevOdd.next = curr;
            }
            prevOdd = curr;
        }
        breakIntoEvenOdd(idx + 1, curr.next);
    }

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            //List with len: 0, 1, 2 are already in the order required
            return head;
        }
        breakIntoEvenOdd(1, head);

        //Important to do this at end, only, while breaking recursively if this is done, then traversing the entire list won't be possible
        prevEven.next = null;
        //Join Odd list with Even
        prevOdd.next = evenHead;
        return oddHead;
    }
}

//Elegant Iterative
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            //List with len: 0, 1, 2 are already in the order required
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while(even != null && even.next != null){
            odd.next = even.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}