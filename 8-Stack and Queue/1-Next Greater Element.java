class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextGreater = new int[(int)1e4 + 1];

        //Monotonic Decreasing Stack : bottom to top (Decreasing Order)
        Stack<Integer> st = new Stack<>();
        
        //Eval Greater Element to Right, for Each nums2 El
        for(int i = nums2.length - 1; i >= 0; i--){
            int currEl = nums2[i];
            
            //Remove all the elements less than or equal to currEL, to maintain Decreasing order
            //Also after doing this, at the top of the stack, we'll have the first element to the right greater to currEl 
            while(!st.isEmpty() && st.peek() <= currEl){
                st.pop();
            }

            nextGreater[currEl] = st.isEmpty() ? -1 : st.peek();

            //Push the curr el
            st.push(currEl);
        }         

        int[] nextGreaterElements = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            int currEl = nums1[i];
            nextGreaterElements[i] = nextGreater[currEl];
        }
        return nextGreaterElements;
    }
}