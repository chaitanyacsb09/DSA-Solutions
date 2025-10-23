//TC: (NSL,NSR) -> 2 * O(N+N) + (AreaCalc) O(N) | SC: O(3N)
class Solution {
    private int[] getLeftSmallerIdxArr(int[] heights){
        int n = heights.length;
        int[] leftSmallerIdx = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            int currHeight = heights[i];
            while(!st.empty() && heights[st.peek()] >= currHeight){
                st.pop();
            }
            leftSmallerIdx[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return leftSmallerIdx;
    }
    private int[] getRightSmallerIdxArr(int[] heights){
        int n = heights.length;
        int[] rightSmallerIdx = new int[n];
        Stack<Integer> st = new Stack<>();
        
        for(int i = n - 1; i >= 0; i--){
            int currHeight = heights[i];
            while(!st.empty() && heights[st.peek()] >= currHeight){
                st.pop();
            }
            rightSmallerIdx[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return rightSmallerIdx;
    }
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] leftSmallerIdx = getLeftSmallerIdxArr(heights);
        int[] rightSmallerIdx = getRightSmallerIdxArr(heights);

        int maxArea = 0;

        for(int i = 0; i < n; i++){
            int currHeight = heights[i];
            if(currHeight == 0) continue;

            int smallerElementToLeftIdx = leftSmallerIdx[i];
            int smallerElementToRightIdx = rightSmallerIdx[i];

            int extendableLeftBlocks = (i - 1) - smallerElementToLeftIdx;
            int extendableRightBlocks = (smallerElementToRightIdx - 1) - i;

            int totalBlocksInRectangle = 1 + extendableLeftBlocks + extendableRightBlocks;
            int totalAreaOfRectangle = totalBlocksInRectangle * currHeight;
            maxArea = Math.max(maxArea, totalAreaOfRectangle);
        }

        return maxArea;
    }
}

//TC: O(N) | SC:O(1)
class Solution {
public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    Deque<Integer> stack = new ArrayDeque<>();
    int maxArea = 0;
    for (int i = 0; i <= n; i++) {
        int currHeight = (i == n) ? 0 : heights[i];
        while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
            int h = heights[stack.pop()];
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, h * width);
        }
        stack.push(i);
    }
    return maxArea;
}
}