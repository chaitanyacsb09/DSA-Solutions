//O(N) Time and Space
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> majEls = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            freq.merge(nums[i], 1, Integer::sum);
        }
        int majThreshold = (int) Math.floor(n / 3);
        for (int num : nums) {
            if (freq.containsKey(num) && freq.get(num) > majThreshold) {
                majEls.add(num);
                freq.remove(num);
            }
            if(majEls.size() == 2){
                break;
            }
        }
        return majEls;
    }
}
//Better way to write, but the above one runs faster on LC
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> majEls = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;
        int majThreshold = (int) Math.floor(n / 3);
        for (int i = 0; i < n; i++) {
            freq.merge(nums[i], 1, Integer::sum);
            if(freq.get(nums[i]) == majThreshold + 1){
                majEls.add(nums[i]);
            }
            if(majEls.size() == 2){
                break;
            }
        }
        
        return majEls;
    }
}

//Hashmap O(N) Space: O(1)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> majEls = new ArrayList<>();
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = nums.length;
        int majThreshold = n / 3;
        for (int i = 0; i < n; i++) {
            cnt.merge(nums[i], 1, Integer::sum);
            if(cnt.size() > 2){
                List<Integer> toRemove = new ArrayList<>();
                for(int key: cnt.keySet()){
                    cnt.merge(key, -1, Integer::sum);
                    if(cnt.get(key) == 0){
                        toRemove.add(key);
                    }
                }
                for(int removeKey : toRemove){
                    cnt.remove(removeKey);
                }
            }
        }
        for(Map.Entry<Integer, Integer> kv: cnt.entrySet()){
            cnt.put(kv.getKey(),0);
        }
        for(int num: nums){
            if(cnt.containsKey(num)){
                cnt.merge(num, 1, Integer::sum);
                if(cnt.get(num) == majThreshold + 1){
                    majEls.add(num);
                }   
            }
        }
        return majEls;
    }
}

//Optimal: Extended Moore's Algo
class Solution {
    public List<Integer> majorityElement(int[] nums) {
    	int n = nums.length;
        int majThreshold = n / 3;
        int cnt1 = 0, cnt2 = 0, el1 = Integer.MIN_VALUE, el2 = Integer.MIN_VALUE;
        List<Integer> majEls = new ArrayList<>();
        for(int currNum: nums){
            if(cnt1 == 0 && currNum != el2){
                cnt1 = 1; el1 = currNum;
            }
            else if(cnt2 == 0 && currNum != el1){
                cnt2 = 1; el2 = currNum;
            }
            else if(currNum == el1) cnt1++;
            else if(currNum == el2) cnt2++;
            else{
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0; cnt2 = 0;
        for(int currNum: nums){
            if(currNum == el1){
                cnt1++;
            }
            else if(currNum == el2){
                cnt2++;
            }
        }
        if(cnt1 > majThreshold) majEls.add(el1);
        if(cnt2 > majThreshold) majEls.add(el2);
        return majEls;
    }
}
