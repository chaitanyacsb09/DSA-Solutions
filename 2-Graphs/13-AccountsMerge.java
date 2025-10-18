class DisjointSet{
    int[] parent;
    int[] size;
    
    public DisjointSet(int numNodes){
        parent = new int[numNodes];
        size = new int[numNodes];
        for(int i = 0; i < numNodes; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    public int findUParent(int node){
        if(parent[node] == node){
            return node;
        }
        return parent[node] = findUParent(parent[node]);
    }
    
    public void unionBySize(int u, int v){
        int uPar = findUParent(u), vPar = findUParent(v);
        if(uPar == vPar) return;
        if(size[uPar] < size[vPar]){
            parent[uPar] = vPar;
            size[vPar]+= size[uPar];
        }
        else{
            parent[vPar] = uPar;
            size[uPar]+= size[vPar];
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> mailMap = new HashMap<>();
        int numAccounts = accounts.size();
        DisjointSet ds = new DisjointSet(numAccounts);
        //Think of each account as node, which we could join/union basis on common mail
        for(int i = 0; i < numAccounts; i++){
            for(int mailIdx = 1; mailIdx < accounts.get(i).size(); mailIdx++){
                String mail = accounts.get(i).get(mailIdx);
                if(mailMap.containsKey(mail) == false){
                    mailMap.put(mail, i); //Map this mail to account idx
                }
                else{
                    //Mail already exists, therefore the current account could be merged with some previous account
                    ds.unionBySize(i, mailMap.get(mail));
                }
            }
        }
        //Now, all the accounts which could be merged will be under the same ultimate parent
        
        //Merge all the mails, for an ultimate parents
        List<String>[] mergedMails =  new ArrayList[numAccounts];
        for(int i = 0; i < numAccounts; i++) mergedMails[i] = new ArrayList<>();
        
        for(Map.Entry<String, Integer> entry: mailMap.entrySet()){
            String mail = entry.getKey();
            Integer accIdx = entry.getValue();
            int node = ds.findUParent(accIdx);
            mergedMails[node].add(mail); 
        }

        List<List<String>> mergedAccounts = new ArrayList<>();

        for(int i = 0; i < numAccounts; i++){
            if(mergedMails[i].size() == 0) continue;

            String name = accounts.get(i).get(0);
            //As Emails are asked in sorted order
            Collections.sort(mergedMails[i]);
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(name);
            for(String email: mergedMails[i]){
                mergedAccount.add(email);
            }
            mergedAccounts.add(mergedAccount);
        }
        return mergedAccounts;
    }
}