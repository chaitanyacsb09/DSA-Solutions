class Solution {
    public long subarrayXor(int arr[], int k) {
        long subArrWithXorK = 0;
        int currXor = 0;
        Map<Integer, Integer> xorToSubArrCount = new HashMap<>();
        xorToSubArrCount.put(0,1);
        for(int num: arr){
            currXor = currXor ^ num;
            int requiredPrefixXor = currXor ^ k;
            if(xorToSubArrCount.containsKey(requiredPrefixXor)){
                subArrWithXorK += xorToSubArrCount.get(requiredPrefixXor);
            }
            xorToSubArrCount.merge(currXor, 1, Integer::sum);
        }
        return subArrWithXorK;
    }
}
