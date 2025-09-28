class Pair<A, B>{
    A word;
    B steps;
    public Pair(A word, B steps){
        this.word = word;
        this.steps = steps;
    }
}
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)){
            return 0;
        }

        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        while(!q.isEmpty()){
            Pair<String,Integer> front = q.poll();
            String currWord = front.word;
            int stepsRequired = front.steps;

            if(currWord.equals(endWord)){
                return stepsRequired;
            }

            //Check replacing each character
            char[] charArr = currWord.toCharArray();
            for(int i = 0; i < currWord.length(); i++){
                for(char c = 'a'; c <= 'z'; c++){
                    if(c == currWord.charAt(i)) continue;
                    charArr[i] = c;
                    String transformed = new String(charArr);
                    if(wordSet.contains(transformed)){
                        wordSet.remove(transformed); //Remove/Mark Visited the currTransformation
                        q.add(new Pair(transformed, stepsRequired+1));
                    }
                }
                charArr[i] = currWord.charAt(i);
            }
        }
        return 0;
    }
}