//TC: O(N) SC: O(N)
class Solution {
    public int minAddToMakeValid(String s) {
        //either empty or ')' then push, if top '(' and currChar ')'. pop stack, otherwise insert
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (st.isEmpty() || st.peek() == ')') {
                st.push(s.charAt(i));
            } else {
                if (s.charAt(i) == ')') {
                    st.pop();
                } else {
                    st.push(s.charAt(i));
                }
            }
        }
        return st.size();
    }
}

//TC: O(N) SC:O(1)
//What does we get from stack, the info regarding:
    //numOpen andn numClose, and also we match each open with close
class Solution {
    public int minAddToMakeValid(String s) {
        //numOpen and numClose, and each open should be matched with close
        int numOpen = 0, numClosed = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                numOpen++;
            } else if (numOpen > 0) {
                numOpen--; //Matching closed with open paranthesis
            } else {
                numClosed++;
            }
        }

        //Add close for each open, and vice versa
        return numOpen + numClosed;
    }
}

