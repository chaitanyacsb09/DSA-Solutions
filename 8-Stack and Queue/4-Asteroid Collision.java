//Problem : https://leetcode.com/problems/asteroid-collision/
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<Integer>();
        int n = asteroids.length;
        for(int i = 0; i < n; i++){
            int currAsteroid = asteroids[i];
            int currAsteroidSize = Math.abs(currAsteroid);
            if(currAsteroid > 0){
                st.push(currAsteroid);
                continue;
            }
            boolean isCurrAsteroidExploded = false;
            while(!st.isEmpty() && !isCurrAsteroidExploded && st.peek() > 0){
                if(currAsteroidSize > st.peek()){
                    st.pop();
                }
                else if(currAsteroidSize == st.peek()){
                    st.pop();
                    isCurrAsteroidExploded = true;
                }
                else{
                    isCurrAsteroidExploded = true;
                }
            }
            if(!isCurrAsteroidExploded){
                st.push(currAsteroid);
            }
        }
        int[] remainingAsteroids = new int[st.size()];
        for(int i = st.size() - 1; i >= 0; i--){
            remainingAsteroids[i] = st.pop();
        }
        return remainingAsteroids;
    }
}