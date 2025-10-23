class Node{
    Node prev, next;
    int key, val;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}
class LRUCache {
    boolean debugEnabled = false;
    Node head, tail;
    int capacity;
    Map<Integer, Node> cacheEntries;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail = new Node(-1,-1);
        head = new Node(-1,-1);
        tail.prev = head;
        head.next = tail;
        cacheEntries = new HashMap<>();    
    }
    
    public int get(int key) {
        if(!cacheEntries.containsKey(key)){
            return -1;
        }

        Node currNode = cacheEntries.get(key);
        markRecent(currNode);
        return currNode.val;
    }
    
    public void put(int key, int value) {
        if(cacheEntries.containsKey(key)){
            updateAndMarkRecent(key, value);
            return;
        }
        if(cacheEntries.size() == capacity){
            removeLRU();

        }
        insertAndMarkRecent(key, value);
    }

    private void markRecent(Node node){
        //Remove from currSequence/Queue
        removeFromQueue(node);

        //Insert at front, hence marking recent
        insertAtFront(node);
    }

    private void updateAndMarkRecent(int key, int value){
        Node currNode = cacheEntries.get(key);
        currNode.val = value;
        markRecent(currNode);
    }

    private void removeLRU(){
        if(debugEnabled){
            System.out.println("Removing the LRU with Key: " + tail.prev.key);
        }
        //The last in the sequence/queue will the be least recently used and will be pointed by tail.prev
        cacheEntries.remove(tail.prev.key);
        removeFromQueue(tail.prev);
    }

    private void insertAndMarkRecent(int key, int value){
        Node newEntry = new Node(key, value);
        cacheEntries.put(key, newEntry);
        if(debugEnabled){
            System.out.println("Inserted Key: " + newEntry.key + " Value: " + newEntry.val);
        }
        //Insert at the front and hence marking recent
        insertAtFront(newEntry); 
    }

    private void insertAtFront(Node node){
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;

        if(debugEnabled){
            System.out.println("Current Queue State, After Inserting the Node with Key: " + node.key);
            Node temp = head.next;
            while(temp != tail){
                System.out.print(temp.key + ",");
                temp = temp.next;
            }
            System.out.println();
        }

    }

    private void removeFromQueue(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //For Garbage Collection
        node.prev = null;
        node.next = null;
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */